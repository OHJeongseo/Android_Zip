package com.cookandroid.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*안드로이드 <-> 스프링 <-> MySqlDB */
//db에 있는 데이터를 가져와서 화면에 보여준다
public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    PhoneAdapter phoneAdapter;
    PhoneService phoneService;
    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //recyclerView 를 바인딩하고 recyclerView 에 넣을 xml 설정하고 추가한다
        recyclerView= findViewById(R.id.recyclerview);
        floatingActionButton= findViewById(R.id.floatingActionButton); //추가버튼
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addConcat();
            }
        });
        
        //레이아웃 설정
        LinearLayoutManager manager=
                new LinearLayoutManager(MainActivity.this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(manager);

        //db 서버와 연결하고 전체보기 메소드를 불러온다
        phoneService= Retrofit2Client.getInstance().getPhoneService();
        Call<List<Phone>> call= phoneService.findAll();

        //불러온데이터를 리스트에넣고 어뎁터에 불러온데이터를 넘겨주고 recyclerView 와 어뎁터를 연결한다
        call.enqueue(new Callback<List<Phone>>() {
            @Override
            public void onResponse(Call<List<Phone>> call, Response<List<Phone>> response) {
                List<Phone> phoneList= response.body();
                phoneAdapter= new PhoneAdapter(phoneList);
                recyclerView.setAdapter(phoneAdapter);
                Log.d("data", "응답 받은데이터: "+phoneList);
            }

            @Override
            public void onFailure(Call<List<Phone>> call, Throwable t) {

            }
        });
    }

    //추가버튼 클릭 메소드 정의
    private void addConcat(){
        View dialogView= LayoutInflater.from(getApplicationContext())
                .inflate(R.layout.layout_add_concat, null);

        final EditText etName= dialogView.findViewById(R.id.etname);
        final EditText etTel= dialogView.findViewById(R.id.ettal);

        AlertDialog.Builder dlg= new AlertDialog.Builder(MainActivity.this);
        dlg.setTitle("연락처 등록");
        dlg.setView(dialogView);
        dlg.setPositiveButton("등록", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Phone phoneDto= new Phone();
                phoneDto.setName(etName.getText().toString());
                phoneDto.setTel(etTel.getText().toString());

                Log.d("insert", "onClick:등록 클릭시 값 확인"+phoneDto);

                phoneService= Retrofit2Client.getInstance().getPhoneService();
                Call<Phone>call= phoneService.save(phoneDto);

                call.enqueue(new Callback<Phone>() {
                    @Override
                    public void onResponse(Call<Phone> call, Response<Phone> response) {
                        Log.d("insert response", "onResponse: phoneDto"+ phoneDto);
                        phoneAdapter.addItem(response.body());
                    }

                    @Override
                    public void onFailure(Call<Phone> call, Throwable t) {

                    }
                });
            }
        });
        dlg.setNegativeButton("닫기",null);
        dlg.show();
    }
}