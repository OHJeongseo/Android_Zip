package com.cookandroid.free;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.utils.widget.MockView;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.GregorianCalendar;

@SuppressWarnings("deprecation")  //경고(줄표시)가 나오는것을 막아주는 코드
public class MainActivity extends AppCompatActivity {
/*자유 어플 만들기 */
    Gallery gallery;
    Button btnChek;
    View dialogView;
    DatePicker dPicker;
    int dYear, dMonth, dDay;
    String title;

    String[] posterTitle={
            "써니","완득이","괴물","라디오스타","비열한거리","왕의남자","아일랜드",
            "웰컴투동막골","헬보이","백투더퓨처","여인의향기","쥬라기공원","포레스트검프","Groundhog Day",
            "혹성탈출","아름다운비행","내이름은 칸","해리포터","마더","킹콩을들다","쿵후팬더",
            "짱구는 못말려","아저씨","아바타"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* 영화 극장 어플 */
        setTitle("부산 극장");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_movie_foreground);


        Calendar calendar = new GregorianCalendar();
        dYear = calendar.get(Calendar.YEAR);
        dMonth = calendar.get(Calendar.MONTH);
        dDay = calendar.get(Calendar.DAY_OF_MONTH);
        dialogView= View.inflate(MainActivity.this,
                R.layout.dialog01, null);
        dPicker= dialogView.findViewById(R.id.dataPicker);
        DatePicker.OnDateChangedListener mOnDateChangedListener = new DatePicker.OnDateChangedListener(){
            @Override
            public void onDateChanged(DatePicker datePicker, int yy, int mm, int dd) {
                dYear = yy;
                dMonth = mm;
                dDay = dd;
            }
        };

        btnChek= findViewById(R.id.btnCheck);
        btnChek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder dlg= new AlertDialog.Builder(MainActivity.this);
                dlg.setTitle(title.toString());
                dlg.setIcon(R.mipmap.ic_movie_foreground);
                dlg.setView(dialogView);
                dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(),
                                dYear+"년"+(dMonth+1)+"월"+dDay+"일에 영화"+title+" 예약이 완료되었습니다",
                                Toast.LENGTH_LONG).show();
                    }
                });
                dlg.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(),
                                "예약이 취소되었습니다",
                                Toast.LENGTH_LONG).show();
                    }
                });
                dlg.show();
            }
        });


        gallery= findViewById(R.id.gallery01);
        MovieGalleryAdapter MovieAdapter= new MovieGalleryAdapter(this);
        gallery.setAdapter(MovieAdapter);
    }

    public class MovieGalleryAdapter extends BaseAdapter{
        Context context;

        public MovieGalleryAdapter(Context context){ //엑티비티
            this.context= context;
        }


        Integer[] posterID= {
                R.drawable.mov01,R.drawable.mov02,R.drawable.mov03,R.drawable.mov04,
                R.drawable.mov05,R.drawable.mov06,R.drawable.mov07,R.drawable.mov08,
                R.drawable.mov09,R.drawable.mov10,R.drawable.mov11,R.drawable.mov12,
                R.drawable.mov13,R.drawable.mov14,R.drawable.mov15,R.drawable.mov16,
                R.drawable.mov17,R.drawable.mov18,R.drawable.mov19,R.drawable.mov20,
                R.drawable.mov21,R.drawable.mov22,R.drawable.mov23,R.drawable.mov24
        };

        @Override
        public int getCount() { //데이터
            return posterID.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) { //디자인
            //이미지 뷰(갤러리설정 81~ 88)생성
            ImageView imageView= new ImageView(context);
            //갤러리 이미지 크기 설정
            imageView.setLayoutParams(new Gallery.LayoutParams(1100,1400));
            //이미지뷰 칸의 중앙에 배치
            imageView.setScaleType(ImageView.ScaleType.FIT_END);
            //이미지뷰 간격 설정
            imageView.setPadding(5, 5,5,5);

            //선택된(현재)포스터를 이미지뷰에 출력
            imageView.setImageResource(posterID[position]);

            imageView.setOnTouchListener(new View.OnTouchListener() { //선택된 포스터의 제목을 가져와서 대화상자의 제목으로 사용한다
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    title= posterTitle[position].toString();
                    return false;
                }
            });

            return imageView;
        }
    }
}