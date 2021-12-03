package com.cookandroid.chapter11;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
/*어뎁터 뷰 */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*어뎁터 뷰.리스트뷰:데이터를 리스트로 보여주고 하나를 선택하는 용도로 사용(ch11-1~ 11-3
        setTitle("리스트뷰 테스트");
        //리스트뷰에 사용할 문자열 배열(어레이어뎁터 생성시 사용할 데이터)
        final String[] mid= {"히어로즈", "24시", "로스트", "로스트롬", "스몰빌", "탐정몽크", "백뱅이론",
                             "프렌즈", "덱스터", "글리", "가쉽걸", "테이큰", "슈퍼내추얼", "브이"};
        //바인딩(리스트뷰)
        ListView list= findViewById(R.id.listView01);


        //기본사용 어레이어뎁터 생성(위치, 1줄디자인, 데이터(문자열 배열)하고
        //ArrayAdapter<String> adapter=  new ArrayAdapter<String>(this,
        //        android.R.layout.simple_list_item_1, mid);

        //생성된 어레이어뎁터를 리스트뷰에 연결
        list.setAdapter(adapter);
        
        //체그박스(simple_list_item_multiple_choice)모양 사용 어레이어뎁터 생성
        ArrayAdapter<String> adapter=  new ArrayAdapter<String>(this,
                        android.R.layout.simple_list_item_multiple_choice, mid);
        list.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE); //setChoiceMode-> 다중선택 설정
        

        //리스트뷰 항목 클릭 이벤트 
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override //파라미터(어뎁터뷰, 뷰, 클릭한 항목의 순번(현재위치), 항목 id)
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //리스트뷰 항목에서 선택한 문자열을 가져와서(mid[현재항목]) 토스트 메세지로 출력한다
                Toast.makeText(getApplicationContext(), mid[position], Toast.LENGTH_SHORT).show();
            }
        });*/


        /* 리스트뷰 동적추가 삭제 사용(ch11-4 ~ ch11-5)*/
        //리스트뷰에 사용할 빈 문자열 배열 선언(어레이어뎁터에 데이터 값으로 설정)
        final ArrayList<String> midList= new ArrayList<String>();
        //어뎁터.리스트뷰 바인딩
        ListView list= findViewById(R.id.listView01);

        //어레어 어뎁터 생성하고 설정(엑티비티(this->main), 한줄디자인, 데이터값)한다
        final ArrayAdapter<String> adapter= new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, midList); 
        //생성한 어레이 어뎁터와 리스트뷰를 연결한다(어뎁터 연결)
        list.setAdapter(adapter); 

        //문자열 배열 'midList' 에 값을 넣를 에디터 텍스트를 선언(final 선언(리스너에서 값이 변경되지않도록 설정)
        final EditText edit01= findViewById(R.id.edit01);
        Button bAdd= findViewById(R.id.btnAdd);
        
        bAdd.setOnClickListener(new View.OnClickListener() { //항목 추가 버튼 이벤트
            @Override
            public void onClick(View v) {
                //에디터 텍스트에 입력된 값을 가져와서 문자열 배열에 넣고
                midList.add(edit01.getText().toString());
                //어뎁터 객체에 변경된 내용 반영
                adapter.notifyDataSetChanged();
            }
        });

        
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {  //항목 롱 클릭 이벤트
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                //선택한 리스트항목을 문자열 배열에서 삭제하고
                midList.remove(position);
                //어뎁터 객체에 변경된 내용 반영
                adapter.notifyDataSetChanged();
                return false;
            }
        });
    }
}