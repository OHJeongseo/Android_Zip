package com.cookandroid.myapplication;

import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//activity_main.xml 의 recyclerview 에 넣을 데이터를 어뎁터로 설정
//MainActivity 에서 받아온 데이터를 가공하여 넘겨준다
public class PhoneAdapter extends RecyclerView.Adapter<PhoneAdapter.MyViewHolder> {
    private List<Phone> phoneList;

    //받아온 데이터(리스트)를 멤버변수리스트에 넣는다(저장)
    public PhoneAdapter(List<Phone> phoneList) {
        this.phoneList = phoneList;
    }

    //상속받은(커스텀)MyViewHolder 를 설정한다
    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView txId,txName, txTel; //데이터를 추가할 id 를 선언하고

        //바인딩
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.txId= itemView.findViewById(R.id.txId);
            this.txName= itemView.findViewById(R.id.txName);
            this.txTel= itemView.findViewById(R.id.txTel);
        }
    }
    //insert
    public void addItem(Phone phone){
        phoneList.add(phone);
        notifyDataSetChanged(); //변경시 새로 경신
    }

    //list

    //update
    public void updateItem(Phone phone, int Position){
        Phone p=phoneList.get(Position);
        p.setName(phone.getName());
        p.setTel(phone.getTel());
        notifyDataSetChanged(); //변경시 새로 경신
    }

    //remove
    public  void removeItem(int position){
        phoneList.remove(position);
        notifyDataSetChanged(); //변경시 새로 경신
    }

    //뷰로 사용할 xml 를 설정하고 MyViewHolder 변수에 넣고 변수를 반환한다
    @NonNull
    @Override
    public PhoneAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.phone_list,parent,false);

        MyViewHolder viewHolder= new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PhoneAdapter.MyViewHolder holder, int position) {
        //넘겨받은 데이터(리스트)의 현재 값을 객체로 받아서
        Phone phone= phoneList.get(position);

        //받은 데이터의 값을 id 값에 맞게 넣는다
        holder.txId.setText(Long.toString(phone.getId()));
        holder.txName.setText(phone.getName());
        holder.txTel.setText(phone.getTel());

        //연락처가 클릭되었을때
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View dialogView= v.inflate(v.getContext(), R.layout.layout_add_concat, null);

                final EditText etName= dialogView.findViewById(R.id.etname);
                final EditText etTel= dialogView.findViewById(R.id.ettal);

                etName.setText(phone.getName());
                etTel.setText(phone.getTel());

                AlertDialog.Builder dlg= new AlertDialog.Builder(v.getContext());
                dlg.setTitle("연락처 등록");
                dlg.setView(dialogView);
                dlg.setPositiveButton("Update", new DialogInterface.OnClickListener() { //수정
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Phone phoneDto= new Phone();
                        //phoneDto.setId(phone.getId())
                        phoneDto.setName(etName.getText().toString());
                        phoneDto.setTel(etTel.getText().toString());

                        Log.d("Update", "onClick:등록 클릭시 값 확인"+phoneDto);

                        //서버와 연관되는 메서드 호출
                        PhoneService phoneService= Retrofit2Client.getInstance().getPhoneService();
                        Call<Phone>call= phoneService.update(phone.getId(),phoneDto);
                        call.enqueue(new Callback<Phone>() {
                            @Override
                            public void onResponse(Call<Phone> call, Response<Phone> response) {
                                updateItem(response.body(), position);
                            }

                            @Override
                            public void onFailure(Call<Phone> call, Throwable t) {

                            }
                        });
                    }
                });
                dlg.setNegativeButton("Delete", new DialogInterface.OnClickListener() { //삭제버튼 클릭
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //서버와 연관되는 메서드 호출
                        PhoneService phoneService= Retrofit2Client.getInstance().getPhoneService();
                        Call<Void> call= phoneService.deleteById(phone.getId()); //스프링 서버와 연관된 메서드를 가져온다
                        call.enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) { //연결
                                removeItem(position); //선택된 연락처 정보를 지운다
                                if(!response.isSuccessful()) return; //삭제되지 않으면
                            }

                            @Override
                            public void onFailure(Call<Void> call, Throwable t) {

                            }
                        });
                    }
                });
                dlg.setNeutralButton("Close", null);
                dlg.show();
            }
        });
    }

    //데이터가 있으면 데이터가 있는 만큼의 개수를 반환하고 없으면 0를 반환한다
    @Override
    public int getItemCount() {
        return phoneList== null? 0:phoneList.size();
    }
}
