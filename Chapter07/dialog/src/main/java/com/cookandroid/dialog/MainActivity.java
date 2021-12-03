package com.cookandroid.dialog;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {   
    /*대화상자: 화면에 메세지를 나타낸후 확인 또는 취소 같은 사용자의 선택을 받아들이는 경우에 사용 */
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ch07_14);

        /*ch07-15~ ch07-17(ch07_14.xml 연결) */
        final Button btn01= findViewById(R.id.btn001);

        boolean[] check= new boolean[] {false,false,false}; //선택된것을 초기화하지않고 표시되도록 리스너에서 제외하여 선언

        btn01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* 대화상자 생성
                AlertDialog.Builder dlg= new AlertDialog.Builder(MainActivity.this);
                //제목, 메세지내용, 아이콘 설정
                dlg.setTitle("제목입니다");
                dlg.setMessage("이곳은 내용입니다");
                dlg.setIcon(R.mipmap.ic_launcher);
                //버튼이 1개(확인버튼) 사용
                dlg.setPositiveButton("확인",null); //문자열, 리스너(동작설정)
                dlg.setNegativeButton("취소",null); //+ 추가로 취소 버튼을 생성
                //버튼(확인버튼) 이벤트 설정
                dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "확인버튼 클릭", Toast.LENGTH_SHORT).show();
                    }
                });
                //화면에 출력
                dlg.show(); */

                /*ch07-18 목록 대화상자
                final String[] version= new String[]{"파이","Q(10)","R(11)"}; //출력할 항목의 문자열 배열 생성
                //대화상자 생성하고 제목, 아이콘, 아이템 메소드를 사용
                AlertDialog.Builder dlg= new AlertDialog.Builder(MainActivity.this) 
                    .setTitle("좋아하는 버전은 ?")
                    .setIcon(R.mipmap.ic_launcher)
                        //아이템을 선택하면 선택된 문자열을 버튼의 text 값으로 설정한다
                    .setItems(version, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) { 
                            btn01.setText(version[which]); //which-> 선택된 항목을 알려준다
                        }
                    });
                    dlg.setPositiveButton("닫기",null);
                    dlg.show();
                    //setItems 선택과 동시에 대화상자가 닫힌다
                 */

                /*ch07-19 라디오버튼 목록 대화상자
                final String[] version= new String[]{"파이","Q(10)","R(11)"}; //출력할 항목의 문자열 배열 생성
                //대화상자 생성하고 제목, 아이콘, 아이템 메소드를 사용
                AlertDialog.Builder dlg= new AlertDialog.Builder(MainActivity.this)
                        .setTitle("좋아하는 버전은 ?")
                        .setIcon(R.mipmap.ic_launcher)
                        //아이템을 선택하면 선택된 문자열을 버튼의 text 값으로 설정한다 (문자열 배열, 초기 선택인덱스(버튼클릭표시), 리스너)
                        .setSingleChoiceItems(version, 0, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                btn01.setText(version[which]); //which-> 선택된 항목을 알려준다
                            }
                        });
                dlg.setPositiveButton("닫기",null);
                dlg.show();
                //setSingleChoiceItems 선택해도 대화상자가 닫히지 않느다
                 */


                /*ch07-20 체크박스 목록 대화상자 */
                final String[] version= new String[] {"파이","Q(10)","R(11)"};
                //final boolean[] check= new boolean[] {false,false,false}; //체크 확인하는 boolean 배열 생성, 체크상태 표시
                AlertDialog.Builder dlg= new AlertDialog.Builder(MainActivity.this);
                dlg.setTitle("좋아하는 버전은?");
                dlg.setIcon(R.mipmap.ic_launcher);
                 //(문자열 배열, boolean 배열(두번째파라미터: 첫번째 파라미터와 개수가 같아야된다)
                dlg.setMultiChoiceItems(version, check, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        String str= ""; //선택된것을 저장
                        int cnt= 0;
                        //version 의 길이만큼 반복해서 여러개를 선택할때 version 의 인덱스 값에 따라 출력되도록 설정한다
                        for(int i=0; i< version.length; i++){
                            if(check[i]) { //check-> true, 선택된것이 있을때
                                if(cnt==0) str= version[i]; //1)첫번째 선택시 str= version[0] 파이 넣기
                                //3)두번째 선택 str(파이)+version[1]-'Q(10)' 넣고, 5)세번재 선택시 str(파이,Q(10))+version[2]를 넣는다
                                else str= str+","+version[i];
                                cnt++;  //2)cnt 증감 4)cnt 증감
                             }
                        }
                        //btn01.setText(version[which]);
                        btn01.setText(str); //선택된것을 표시
                    }
                });
                dlg.setPositiveButton("닫기", null);
                dlg.show();
                //setMultiChoiceItems 여러 개를 동시에 선택할때 사용, 체크박스 형태로 표시
            }
        });


    }
}