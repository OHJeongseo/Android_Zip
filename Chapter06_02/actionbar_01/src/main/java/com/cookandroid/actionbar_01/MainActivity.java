package com.cookandroid.actionbar_01;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/*액션바(다양한크기의 화면을 디자인하는데 사용, 메뉴를 대체하여 사용할수있다)와
 프래그먼트(액티비티보다 작은 단위의 화면, 분할하여 표현할수도 있으며, 실행중에 화면을 동적으로 추가,제거하는데 많이 활용된다)
 사용하여 탭호스트(ch06_17)와 동일한기능을 java 만 사용하여 작성  */

                                   //앱 호환성 지원               //TabListener 인터페이스 사용
@SuppressWarnings("deprecation")  //경고(줄표시)가 나오는것을 막아주는 코드
public class MainActivity extends AppCompatActivity implements ActionBar.TabListener { 
     //탭 전역변수 선언-> 프래그먼트와 연결하여 사용을 위한
    ActionBar.Tab tabSong, tabArtist, tabAlbum;
     //MyTabFragment 배열 선언, 3개의 프래그먼트(화면)을 저장하는데 사용한다
    MyTabFragment myFrags[]= new MyTabFragment[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main); 프레그먼트를 java 로 만들기때문에 xml 을 사용하지않는다


        /*ch06_19 액션바 java 코드 */

         //1."액션바" 만들기
        ActionBar bar= getSupportActionBar();
        bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS); //액션바 모드 탭호스트 모양(안의 설정된)으로 설정

        //2. "탭 만들기" - 액션바에 들어갈 탭
        tabSong= bar.newTab();  //탭 만들기
        tabSong.setText("음악별"); //탭 옵션정하기
        tabSong.setTabListener(this); //탭에 리스너를 넣는다(이벤트 정의) -> ActionBar.TabListener 를 상속받은 오버라이딩 메서드
        bar.addTab(tabSong); //액션바에 탭을 넣는다
        
        tabArtist= bar.newTab();
        tabArtist.setText("가수별");
        tabArtist.setTabListener(this);
        bar.addTab(tabArtist);
        
        tabAlbum= bar.newTab();
        tabAlbum.setText("앨범별");
        tabAlbum.setTabListener(this);
        bar.addTab(tabAlbum);

    }

    //4. 탭과 프래그먼트 연결하기
     //탭을 선택하면 동작하는 코드, 탭리스너를 상속하고 오버라이딩한다
    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        MyTabFragment myTabFrag= null; //프래그먼트를 생성하기 위한 멤버변수(프래그먼트 객체)를 선언

         //클릭한 탭 인덱스가 배열에 있는지 확인한다, myFrags 는 전역변수로 선언하여 사용
        if(myFrags[tab.getPosition()]== null){
             //새로운 프래그먼트를 생성
            myTabFrag= new MyTabFragment();
             //옵션: 프래그먼트 안에 리니어레이아웃의 색상결정
              //-> OS로 보낼 Bundle 을 생성, Bundle 안에 Key 를 tabName 으로 설정, tabName 안에 실제('음악별,'가수별','앨범별') 값을 보낸다
            Bundle data= new Bundle();
            data.putString("tabName", tab.getText().toString());
            myTabFrag.setArguments(data);
             //새로만든 프래그먼트를 프래그먼트배열에 넣는다, 배열안에 프래그먼트가 있는지 확인하기 위한, 재사용을 위한 저장
            myFrags[tab.getPosition()]= myTabFrag;
        }else //배열안에 있을경우
             //기존에 해당 탭을 선택한 적이 있으면 재사용한다(프래그먼트배열에 생성되어있기때문에)
            myTabFrag= myFrags[tab.getPosition()];

        //프래그먼트와 탭 연결(content:프래그먼트, myTabFrag:탭)
        ft.replace(android.R.id.content, myTabFrag); //새로생성한 프래그먼트 또는 기존의 프래그먼트를 액션바 아래쪽에 출력

    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    //3. "프래그먼트" 만들기 (1.멤버변수 , 2.생성자,  3.메서드, 4.내부 클랫스)
     //Fragment 클래스를 상속받는 MyTabFragment 클래스를 만든다
    public static class MyTabFragment extends androidx.fragment.app.Fragment{
        //멤버 변수, onCreateView 에서 사용하기 위한
        String tabName;

        //1)onCreate() 와 onCreateView() 메서드 2개를 오버라이딩한다
         //1)탭과 프래그먼트를 연결하기위한 탭이름과 연동 설정
        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
             //탭을 클릭할때 지정한 데이터로 각 프래그먼트를 지정한다('음악별', '가수별', '앨범별')
            Bundle data= getArguments(); //OS와 통신(연결)
            tabName= data.getString("tabName"); //OS의 Bundle 의 키를 가져온다
        }

         //2)프래그먼트(내부)안에 들어갈 디자인 만들기, 코드에서는 리니어레이아웃을 만듬
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
             //프래그먼트 뷰에 리니어레이아웃을 사용하기위해 빈 리니어레이아웃을 만든다
            LinearLayout.LayoutParams params= new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, //layout_width,
                    LinearLayout.LayoutParams.MATCH_PARENT //layout_height
            );
            LinearLayout baseLayout= new LinearLayout(super.getActivity()); //프래그먼트 사용
            baseLayout.setOrientation(LinearLayout.VERTICAL); // orientation
            baseLayout.setLayoutParams(params);

             //탭(키의 값) 별로 배경색을 다르게 설정
            if(tabName=="음악별") baseLayout.setBackgroundColor(Color.RED);
            if(tabName=="가수별") baseLayout.setBackgroundColor(Color.GREEN);
            if(tabName=="앨범별") baseLayout.setBackgroundColor(Color.BLUE);

             //반환값 리니어 레이아웃
            return baseLayout;
        }

    }
}