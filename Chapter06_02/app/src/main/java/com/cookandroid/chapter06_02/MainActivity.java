package com.cookandroid.chapter06_02;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.app.TabActivity;
import android.database.DatabaseErrorHandler;
import android.os.Bundle;
import android.widget.TabHost;


@SuppressWarnings("deprecation") //TabActivity 사용시 경고를 막는 코드, 없어도 문제되지않음
public class MainActivity extends TabActivity { //탭호스트 사용시 상속받아서 사용한다

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ch06_03);

        
        /* ch06-18(ch06-17.xml 연결)
        TabHost tHost= getTabHost(); //탭호스트변수에 ch06_03.xml 에서 생성한 탭호스트를 가져온다

         //탭스팩 생성-> 탭을 구성하는 요소들의 집합
        TabHost.TabSpec tSpecS= tHost.newTabSpec("SUNG").setIndicator("음악별"); //탭에 출력되는 글자
        tSpecS.setContent(R.id.tabSung); //탭스택을 탭과 연결
        tHost.addTab(tSpecS); //탭을 호스트에 부착

        TabHost.TabSpec tSpecA= tHost.newTabSpec("ARTIST").setIndicator("가수별");
        tSpecA.setContent(R.id.tabArtist);
        tHost.addTab(tSpecA);

        TabHost.TabSpec tSpecAl= tHost.newTabSpec("ALBUM").setIndicator("앨범별");
        tSpecAl.setContent(R.id.tabAlbum);
        tHost.addTab(tSpecAl);

        tHost.setCurrentTab(0);
         */

        /*직접 풀어보기 6-3 */
        TabHost tHost= getTabHost(); //탭호스트변수에 ch06_03.xml 에서 생성한 탭호스트를 가져온다

        TabHost.TabSpec tSpecD= tHost.newTabSpec("DOG").setIndicator("강아지");
        tSpecD.setContent(R.id.tabDog);
        tHost.addTab(tSpecD);

        TabHost.TabSpec tSpecC= tHost.newTabSpec("CAT").setIndicator("고양이");
        tSpecC.setContent(R.id.tabCat);
        tHost.addTab(tSpecC);

        TabHost.TabSpec tSpecR= tHost.newTabSpec("RABBIT").setIndicator("토끼");
        tSpecR.setContent(R.id.tabRabbit);
        tHost.addTab(tSpecR);

        TabHost.TabSpec tSpecH= tHost.newTabSpec("HORSE").setIndicator("말");
        tSpecH.setContent(R.id.tabHorse);
        tHost.addTab(tSpecH);

        tHost.setCurrentTab(0);

        
        /*ch06_19 액션바 java 코드(ch06_17.xml 과 유사한 코드) */


        

    }

}