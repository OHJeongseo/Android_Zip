package com.cookandroid.gridview;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
/* 어뎁터뷰.그리드뷰: 사진이나 그림을 격자모양으로 배치 */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* 실습 11-1 영화 포스터보기1(ch11-6~ ch11-11)*/
        setTitle("그리드뷰 영화 포스터");
        final GridView gridView= findViewById(R.id.gridView);
        //BaseAdapter 를 상속받아서 어뎁터(커스텀)를 생성한다
        MyGridAdapter gAdapter= new MyGridAdapter(this);
        //생성한 어뎁터를 그리드뷰와 연결
        gridView.setAdapter(gAdapter);
    }

    public class MyGridAdapter extends BaseAdapter{
        Context context; //생성자에서 사용할 첫번째 파라미터를 정의(엑티비티)

        //어뎁터 첫번째 파라미터 정의
        public MyGridAdapter(Context c){ //생성자(액티비티)
            context= c;
        }
        
        //어뎁터 세번째 파라미터 정의(데이터 갯수)
        @Override
        public int getCount() { //데이터(갯수)
            return posterID.length;
        } //영화포스터 id의 개수로 정의

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        //영화포스터 id 배열(대화상자.xml 이미지뷰에서 사용할 그림파일을 저장)
        Integer[] posterID= {
                R.drawable.mov01,R.drawable.mov02,R.drawable.mov03,R.drawable.mov04,
                R.drawable.mov05,R.drawable.mov06,R.drawable.mov07,R.drawable.mov08,
                R.drawable.mov09,R.drawable.mov10,R.drawable.mov11,R.drawable.mov12,
                R.drawable.mov13,R.drawable.mov14,R.drawable.mov15,R.drawable.mov16,
                R.drawable.mov17,R.drawable.mov18,R.drawable.mov19,R.drawable.mov20,
                R.drawable.mov21,R.drawable.mov22,R.drawable.mov23,R.drawable.mov24
        };

        //영화포스터 제목 배열(대화상자.xml 타이틀값으로 사용할 문자를 저장)
        String[] posterTitle={
                "써니","완득이","괴물","라디오스타","비열한거리","왕의남자","아일랜드",
                "웰컴투동막골","헬보이","백투더퓨처","여인의향기","쥬라기공원","포레스트검프","Groundhog Day",
                "혹성탈출","아름다운비행","내이름은 칸","해리포터","마더","킹콩을들다","쿵후팬더",
                "짱구는 못말려","아저씨","아바타"
        };

        //어뎁터 두번째 파라미터를 정의(1줄 디자인-> 커스텀 디자인(사용자 정의)
        @Override
        public View getView(int position, View convertView, ViewGroup parent) { //어뎁터 1줄(한칸)커스텀 디자인
            //이미지 엑티비티에 넣고(위치,크기,패딩) 클릭이벤트(대화상자.xml) 설정
             //이미지뷰 변수 선언
            ImageView imageView= new ImageView(context); 
             //이미지뷰의 크기 설정
            imageView.setLayoutParams(new ViewGroup.LayoutParams(300,400));
             //이미지뷰를 각 그리드뷰 칸의 중앙에 배치
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
             //간격 설정
            imageView.setPadding(5, 5,5,5);
             //이미지뷰에 영화포스터 1개 적용-> 이미지뷰 클릭 이벤트를 정의하기 위해 사용
            imageView.setImageResource(posterID[position]);
            final int pos= position; //현재 위치값을 pos 변수에 저장
            
            //이미지뷰가 선택되면 실행할 이벤트 정의
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //dialog.xml 인플라이트하여 View 변수에 저장
                    View dialogView= View.inflate(MainActivity.this,
                            R.layout.dialog, null);

                    //대화상자 생성
                    AlertDialog.Builder dlg= new AlertDialog.Builder(MainActivity.this);
                    //인플라이트한 dialog.xml 안의 이미지뷰 바인딩(대화상자의 이미지출력 사용)
                    ImageView iPoster= dialogView.findViewById(R.id.imagePoster);
                    //선택된 포스터를 이미지뷰에 넣고
                    iPoster.setImageResource(posterID[pos]);
                    //선택된 포스터의 제목를 타이틀에 넣고 (다이얼로그:대화상자)
                    dlg.setTitle(posterTitle[pos]);
                    //원하는 icon 선택
                    dlg.setIcon(R.drawable.eclair);
                    //다이얼로그 뷰를 커스텀한(인플라이트 한 변수)뷰로 설정
                    dlg.setView(dialogView);
                     //닫기 버튼 생성
                    dlg.setNegativeButton("닫기", null);
                     //다이얼로그(대화상자) 실행
                    dlg.show();
                }
            });

            return imageView;
        }
    }
}