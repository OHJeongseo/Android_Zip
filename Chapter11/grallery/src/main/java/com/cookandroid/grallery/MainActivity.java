package com.cookandroid.grallery;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

@SuppressWarnings("deprecation")  //경고(줄표시)가 나오는것을 막아주는 코드
public class MainActivity extends AppCompatActivity {
/* 어뎁터뷰.갤러리: 사진이나 이미지를 배치하고 좌우로 스크롤하여 볼수있게 한다 */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* 실습 11-2 영화 포스터 보기2 */
        setTitle("갤러리 영화 포스터");
        Gallery gallery= findViewById(R.id.gallery01);
       
        //BaseAdapter 상속받아서 커스텀 어뎁터를 생성
        MyGalleryAdapter gAdapter= new MyGalleryAdapter(this);
        //생성한 커스텀 어뎁터를 갤러리와 연결
        gallery.setAdapter(gAdapter);
    }

    //BaseAdapter 상속, 어뎁터 정의, @Override 메서드(추상메서드)
    public class MyGalleryAdapter extends BaseAdapter{
        Context context; //첫번째 파라미터 사용(엑티비티) 컨텍스트 변수 선언

        //갤러리와 이미지뷰에 사용할 영화포스터 그림를 저장하는 배열 선언
        Integer[] posterID= {
                R.drawable.mov01,R.drawable.mov02,R.drawable.mov03,R.drawable.mov04,
                R.drawable.mov05,R.drawable.mov06,R.drawable.mov07,R.drawable.mov08,
                R.drawable.mov09,R.drawable.mov10,R.drawable.mov11,R.drawable.mov12,
                R.drawable.mov13,R.drawable.mov14,R.drawable.mov15,R.drawable.mov16,
                R.drawable.mov17,R.drawable.mov18,R.drawable.mov19,R.drawable.mov20,
                R.drawable.mov21,R.drawable.mov22,R.drawable.mov23,R.drawable.mov24
        };

        //커스텀 토스트메세지에 사용(toast01.xml 의 textView )할 문자를 저장하는 배열 선언
        String[] posterTitle={
                "써니","완득이","괴물","라디오스타","비열한거리","왕의남자","아일랜드",
                "웰컴투동막골","헬보이","백투더퓨처","여인의향기","쥬라기공원","포레스트검프","Groundhog Day",
                "혹성탈출","아름다운비행","내이름은 칸","해리포터","마더","킹콩을들다","쿵후팬더",
                "짱구는 못말려","아저씨","아바타"
        };

        //어뎁터 첫번째 파라미터 정의(엑티비티=컨텍스트)
        public MyGalleryAdapter(Context c){
            context= c;
        }

        //어뎁터 세번째 파라미터 정의(데이터 갯수-> 포스터 id 의 개수를 사용)
        @Override
        public int getCount() {
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

        //어뎁터 두번째 파라미터 정의(1줄 디자인-> 커스텀 디자인)
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //이미지 뷰(갤러리설정 81~ 88)생성
            ImageView imageView= new ImageView(context);
            //갤러리 이미지 크기 설정
            imageView.setLayoutParams(new Gallery.LayoutParams(300,400));
            //이미지뷰 칸의 중앙에 배치
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            //이미지뷰 간격 설정
            imageView.setPadding(5, 5,5,5);
            
            //선택된(현재)포스터를 이미지뷰에 출력
            imageView.setImageResource(posterID[position]);

            final int pos= position; //현재위치 값을 pos 변수에 저장
            //이미지뷰 터치 이벤트 정의
            imageView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    //엑티비티의 이미지뷰 바인딩
                    ImageView iPoster= findViewById(R.id.iPoster);
                    //이미지 중앙 배치
                    iPoster.setScaleType(ImageView.ScaleType.FIT_CENTER);
                    //선택된 갤러리의 영화 포스터-> 이미지 뷰에 출력
                    iPoster.setImageResource(posterID[pos]);
//                    Toast.makeText(getApplicationContext(), posterTitle[pos],
//                            Toast.LENGTH_SHORT).show();

                    //토스트(커스텀 toast01.xml 로 만든)메세지 생성
                    Toast toast= new Toast(MainActivity.this); //토스트 생성
                    TextView toastText; //토스트 메세지 사용 TextView 변수선언(toast01.xml TextView 바인딩)
                    View toast01; //toast01.xml 을 저장할 View 변수선언
                    //toast01.xml 파일을 toast01 변수에 대입(인플레이트)한다
                    toast01= View.inflate(MainActivity.this, R.layout.toast01, null);
                    //toast01 안에 있는 텍스트뷰를 바인딩하고 값을 설정한다
                    toastText= (TextView) toast01.findViewById(R.id.toastTextView);
                    toastText.setText(posterTitle[pos]);
                    //인플레이트한 뷰(toast01)를 토스트로 사용한다-> xml 를 토스트로 사용한다
                    toast.setView(toast01);
                    //화면에 토스를 출력한다
                    toast.show();
                    return false;
                }
            });

            return imageView;
        }
    }
}