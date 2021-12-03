package com.cookandroid.chapter14;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

public class MusicService extends Service { //음악재생서비스 자바파일(화면이 종료되어도 실행된다)
/* 서비스: 화면(엑티비티)없이 동작하는 프로그램, 데몬 또는 백그라운드 프로세스라고도 한다 */

    MediaPlayer mp; //MediaPlayer(음악/동영상 재생하는 기능)멤버변수 선언

    public MusicService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //로그 남겨서 해당메서드가 실행되는것을 확인한다
        android.util.Log.i("서비스 테스트", "onCreate()");
    }

    @Override //메인엑티비티에서 중지명령(stopService)하면 실행
    public void onDestroy() {
        super.onDestroy();
        //로그 남겨서 해당메서드가 실행되는것을 확인한다
        android.util.Log.i("서비스 테스트", "onDestroy()");
        mp.stop(); //음악 중지
    }

    @Override //메인액티비티에서 시작명령(startService)하면 메서드 실행
    public int onStartCommand(Intent intent, int flags, int startId) {
        //로그 남겨서 해당메서드가 실행되는것을 확인한다
        android.util.Log.i("서비스 테스트", "onStartCommand()");
        mp= MediaPlayer.create(getApplicationContext(), R.raw.song1); //MediaPlayer 생성하여 음악파일을 가져온다
        mp.setLooping(true); //반복 설정
        mp.start(); //음악시작
        return super.onStartCommand(intent, flags, startId);
    }


}