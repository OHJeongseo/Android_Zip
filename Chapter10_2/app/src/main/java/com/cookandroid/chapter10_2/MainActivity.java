package com.cookandroid.chapter10_2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

@SuppressWarnings("deprecation")  //경고(줄표시)가 나오는것을 막아주는 코드
public class MainActivity extends AppCompatActivity {
    /* 양방향 엑티비티_ 직접 풀어보기 10-3 */
    int Result; //선택한 라디오 버튼에 대한 값을 저장

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ch10_03);

        /* 양방향 엑티비티_ 직접 풀어보기 10-3 */
        setTitle("메인 엑티비티");
        //바인딩
        Button bResult= findViewById(R.id.btnResult);
        RadioGroup rGroup= findViewById(R.id.radioGroup);

        //라디오그룹 이벤트
        rGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                //라디오 그룹 안의 라디오버튼(더하기, 빼기, 곱하기, 나누기)에 따라 넘길 Result 에 값을 다르게 설정한다
                switch (checkedId){
                    case R.id.rPlus:
                        Result= 0;
                        break;
                    case R.id.rMinus:
                        Result= 1;
                        break;
                    case R.id.rMultiply:
                        Result= 2;
                        break;
                    case R.id.rDivision:
                        Result= 3;
                        break;    
                }
            }
        });


        bResult.setOnClickListener(new View.OnClickListener() { //계산하기 버튼 클릭
            @Override
            public void onClick(View v) {
                //바인딩
                EditText edit01= findViewById(R.id.edit01);
                EditText edit02= findViewById(R.id.edit02);
                //인덴트 생성, SecondActivity 엑티비티 로 보낼 데이터(입력한 에디터의 값과 선택한 라디오버튼 상태값)를 넣는다
                Intent intent= new Intent(getApplicationContext(), SecondActivity.class);
                intent.putExtra("num1", Integer.parseInt(edit01.getText().toString()));
                intent.putExtra("num2", Integer.parseInt(edit02.getText().toString()));
                intent.putExtra("Result", Result);
                //값을 돌려받는 메서드(startActivityForResult())를 사용, 돌려받을 값이 있는 경우 '0'을 쓴다
                startActivityForResult(intent, 0);
            }
        });

        
        /*양방향 엑티비티 ch10-15.xml 연결
        setTitle("메인 엑티비티");
        Button bMain= findViewById(R.id.btnMain);
        bMain.setOnClickListener(new View.OnClickListener() { //더하기 버튼 클릭
            @Override
            public void onClick(View v) {
                //바인딩
                EditText edit01= findViewById(R.id.edit01);
                EditText edit02= findViewById(R.id.edit02);
                //인덴트 생성, ResultActivity 엑티비티 로 보낼 데이터(에디터1,2)를 넣느다
                Intent intent= new Intent(getApplicationContext(), ResultActivity.class);
                intent.putExtra("num1", Integer.parseInt(edit01.getText().toString()));
                intent.putExtra("num2", Integer.parseInt(edit02.getText().toString()));
                //값을 돌려받는 메서드(startActivityForResult())를 사용, 돌려받을 값이 있는 경우 '0'을 쓴다 
                startActivityForResult(intent, 0);
            }
        }); */



        /*직접풀어보기 10-2 
        setTitle("명화 선호도 투표");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_image_01);

        //그림의 투표수를 저장할 변수 선언, 초기화(0)
        final int voteCount[]= new int[9];
        for(int i=0; i<9;i++)
            voteCount[i]=0;

        //이미지뷰 위젯변수 배열,이미지뷰 위젯 id 배열, 그림제목 배열 선언
        ImageView image[]= new ImageView[9];
        Integer imageId[]= {
                R.id.image01,R.id.image02,R.id.image03,R.id.image04,R.id.image05,
                R.id.image06,R.id.image07,R.id.image08,R.id.image09
        };
        final String imageName[]= {
                "독서하는 소녀","꽃장식 모자 소녀","부채를 든 소녀","이레느깡 단 베르망","잠자는 소녀",
                "테라스의 두 자매","피아노 레슨","피아노 앞의 소녀들","해변에서",
        };

        //이미지뷰의 개수만큼 반복
        for(int i=0;i<imageId.length;i++){
            final int index;
            index= i;
            image[index]= findViewById(imageId[index]); //이미지뷰 배열에 이미지뷰 id 배열을 넣는다, 바인딩
            //이미지뷰에 대한 클릭 이벤트를 정의
            image[index].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) { //해당 이미지뷰 클릭할때마다 해당그림의 투표수(voteCount)배열 값을 1개씩 증가
                    voteCount[index]++;
                    //클릭한 이미지뷰의 그림제목, 투표수를 토스트 메세지로 출력
                    Toast.makeText(getApplicationContext(), imageName[index]+": 총"+
                            voteCount[index]+"표", Toast.LENGTH_SHORT).show();
                }
            });
        }

        Button bFinsh= findViewById(R.id.btnResult);
        bFinsh.setOnClickListener(new View.OnClickListener() { //투표종료 버튼 이벤트
            @Override
            public void onClick(View v) {
                //인텐트 생성
                Intent intent= new Intent(MainActivity.this, ResultActivity.class);
                //ResultActivity(넘길 엑티비티) 에 투표수 배열과 그림제목 배열의 데이터를 넘겨준다
                intent.putExtra("VoteCount", voteCount); //넘겨줄 데이터(키, 투표수 배열변수)
                intent.putExtra("ImageName",imageName); //넘겨줄 데이터(키, 그림제목 배열변수)
                startActivity(intent);
            }
        }); */

        /* 실습 10-2 명화 선호드 투표 앱 만들기
        setTitle("명화 선호도 투표");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_image_01);

        //그림의 투표수를 저장할 변수 선언, 초기화(0)
        final int voteCount[]= new int[9];
        for(int i=0; i<9;i++)
            voteCount[i]=0;

        //이미지뷰 위젯변수 배열,이미지뷰 위젯 id 배열, 그림제목 배열 선언
        ImageView image[]= new ImageView[9];
        Integer imageId[]= {
                R.id.image01,R.id.image02,R.id.image03,R.id.image04,R.id.image05,
                R.id.image06,R.id.image07,R.id.image08,R.id.image09
        };
        final String imageName[]= {
                "독서하는 소녀","꽃장식 모자 소녀","부채를 든 소녀","이레느깡 단 베르망","잠자는 소녀",
                "테라스의 두 자매","피아노 레슨","피아노 앞의 소녀들","해변에서",
        };

        //이미지뷰의 개수만큼 반복
        for(int i=0;i<imageId.length;i++){
            final int index;
            index= i;
            image[index]= findViewById(imageId[index]); //이미지뷰 배열에 이미지뷰 id 배열을 넣는다, 바인딩
            //이미지뷰에 대한 클릭 이벤트를 정의
            image[index].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) { //해당 이미지뷰 클릭할때마다 해당그림의 투표수(voteCount)배열 값을 1개씩 증가
                    voteCount[index]++;
                    //클릭한 이미지뷰의 그림제목, 투표수를 토스트 메세지로 출력
                    Toast.makeText(MainActivity.this, imageName[index]+": 총"+
                            voteCount[index]+"표", Toast.LENGTH_SHORT).show();
                }
            });
        }
        
        Button bFinsh= findViewById(R.id.btnResult);
        bFinsh.setOnClickListener(new View.OnClickListener() { //투표종료 버튼 이벤트
            @Override
            public void onClick(View v) {
                //인텐트 생성
                Intent intent= new Intent(MainActivity.this, ResultActivity.class);
                //ResultActivity(넘길 엑티비티) 에 투표수 배열과 그림제목 배열의 데이터를 넘겨준다
                intent.putExtra("VoteCount", voteCount); //넘겨줄 데이터(키, 투표수 배열변수)
                intent.putExtra("ImageName",imageName); //넘겨줄 데이터(키, 그림제목 배열변수)
                startActivity(intent);
            }
        }); */
    }

    /* 양방향 엑티비티 ch10-15.xml 연결
    ResultActivity 에서 setResult()로 값을 돌려주면 메서드 실행
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode== RESULT_OK){ //보낸 값(RESULT_OK'-1')-> 인덱트에서 돌려받는 값을 토스트메세지로 출력
            int hap= data.getIntExtra("Hap",0); //보낸 인텍트값을 받아서 저장, 0 디폴트 값(데이터가 오지않을 경우)
            Toast.makeText(getApplicationContext(), "합계 :"+
                    hap, Toast.LENGTH_SHORT).show();
        }
    } */

    /* 양방향 엑티비티_ 직접 풀어보기 10-3 */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode== RESULT_OK){ //보낸 값(RESULT_OK)-> 인덱트에서 돌려받는 값을 토스트메세지로 출력
            int hap= data.getIntExtra("Hap",0); //보낸 인텍트값을 받아서 저장
            Toast.makeText(getApplicationContext(), "결과 :"+
                    hap, Toast.LENGTH_SHORT).show();
        }
    }
}