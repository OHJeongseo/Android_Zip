package com.cookandroid.body_check;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

@SuppressWarnings("deprecation")  //경고(줄표시)가 나오는것을 막아주는 코드
public class MainActivity extends AppCompatActivity {
/* 신체 질량 지수 앱 만들기  */
    EditText height, width;
    RadioButton woman, man;
    Spinner spinner;
    CheckBox Alcohol, Tobacco, Exercise;
    Button bodyCheck;
    TextView bloodRes,heightRes;
    View dialogView;
    Gallery gallery;

    String gender= "여성"; //선택된 성별의 값을 문자열로 저장할 변수 선언
    String sBlood= "A"; //스피너에서 선택된 혈액형의 값을 문자열로 저장할 변수 선언
    Double bodyBmi= 0.0; //신체질량지수의 결과값을 저장할 더블형 변수 선언
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* 신체 질량 지수 앱 만들기  */
        setTitle("신체 질량 측정");
        height= findViewById(R.id.editHeight);
        width= findViewById(R.id.editWidth);

        woman= findViewById(R.id.bWoman);
        woman.setChecked(true);
        man= findViewById(R.id.bMan);

        spinner= findViewById(R.id.spinner01);
        final String[] blood= {"A","B","C","O","AB"};
        ArrayAdapter<String> adapter= new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, blood);
        spinner.setAdapter(adapter);

        Alcohol= findViewById(R.id.cAlcohol);
        Tobacco= findViewById(R.id.cTobacco);
        Exercise= findViewById(R.id.cExercise);

        bodyCheck= findViewById(R.id.bodyCheck);

        bloodRes= findViewById(R.id.bodyBlood); //혈액형+성별을 결과를 보여주는 텍스트뷰
        heightRes= findViewById(R.id.bodyHeight); //키와 몸무게를 확인해서 신체질량지수 결과를 보여주는 텍스트뷰

        gallery= findViewById(R.id.galley01);
        ArrayList<Integer> cImage= new ArrayList<Integer>();

        //라디오버튼에 선택된 성별 확인
        woman.setOnClickListener(new View.OnClickListener() { //여성 라디오버튼 클릭
            @Override
            public void onClick(View v) {
                RadioButton rb= (RadioButton)v;
                gender= rb.getText().toString();
                man.setChecked(false);
            }
        });

        man.setOnClickListener(new View.OnClickListener() { //남성 라디오 버튼 클릭
            @Override
            public void onClick(View v) {
                RadioButton rb= (RadioButton)v;
                gender= rb.getText().toString();
                woman.setChecked(false);
            }
        });

        //스키너 이벤트 처리(선택된 혈액형 확인)
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sBlood= parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //몸 상태 측정 버튼 클릭
        bodyCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //키와 체중 입력여부확인
                if(height.getText().toString().equals("")|| width.toString().equals("")){ //미작성
                    bloodRes.setText("1. "+sBlood+"형 "+gender+"입니다");
                    heightRes.setText("2. 신체질량지수는 ???입니다 ");

                    //대화상자
                    dialogView= View.inflate(MainActivity.this, R.layout.dlalog01, null); //커스텀 다이얼로그 인플레이트
                    AlertDialog.Builder dlg= new AlertDialog.Builder(MainActivity.this);
                    dlg.setTitle("키와 체중");
                    dlg.setView(dialogView);
                    dlg.show();

                }else{
                    bodyBmi= Double.parseDouble(width.getText().toString())/
                            ((Double.parseDouble(height.getText().toString())/100)*
                            (Double.parseDouble(height.getText().toString())/100));
                    bloodRes.setText("1. "+sBlood+"형 "+gender+"입니다");
                    heightRes.setText("2. 신체질량지수는 "+Math.round(bodyBmi*10)/10f+ "입니다 ");
                }

                //습관에서 선택된 체크박스 이벤트, 갤러리에 보여줄 이미지를 어레이배열에 추가
                if(Alcohol.isChecked()){
                    cImage.add(R.drawable.lollipop);
                }
                if(Tobacco.isChecked()){
                    cImage.add(R.drawable.nougat);
                }
                if(Exercise.isChecked()){
                    cImage.add(R.drawable.pie);
                }

                if(Alcohol.isChecked()||Tobacco.isChecked()||!Exercise.isChecked()){
                    gallery.setAdapter(new ImageAdapter(MainActivity.this, cImage));
                }else{
                    gallery.removeAllViewsInLayout();
                }
            }
        });
    }

    public class ImageAdapter extends BaseAdapter{
        Context context;
        ArrayList<Integer> image= null;

        public ImageAdapter(Context context, ArrayList<Integer> image){ //엑티비티, 체크박스의 선택에 따라 설정된 어레이리스트
            this.context= context;
            this.image= image;
        }


        @Override //데이터
        public int getCount() {
            return image.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override //한줄 디지인(커스텀-이미지뷰생성하여 이미지를 보여주도록 설정)
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView= new ImageView(context);
            imageView.setLayoutParams(new Gallery.LayoutParams(400,400));
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageView.setPadding(5,5,5,5);

            imageView.setImageResource(image.get(position));
            return imageView;
        }
    }
}