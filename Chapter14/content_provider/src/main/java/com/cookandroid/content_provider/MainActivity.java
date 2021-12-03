package com.cookandroid.content_provider;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.CallLog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
/*콘텐트 프로바이저 */
    Button bCall;
    EditText eCall;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*통화기록 가져오기 */
        //권한허용 여부 표시
        ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_CALL_LOG}, MODE_PRIVATE);

        bCall= findViewById(R.id.bCall);
        eCall= findViewById(R.id.eCall);

        bCall.setOnClickListener(new View.OnClickListener() { //통화기록 가져오기 버튼 이벤트
            @Override
            public void onClick(View v) {
                eCall.setText(getCallHistory());
            } //에디터 텍스트의 통화기록(getCallHistory()의 반환값)을 넣는다(결과 보여주기)
        });
    }

    //에디터 텍스트에 넣을 값을 메서드를 정의하여 결과를 반환한다
    public String getCallHistory(){
        //OS 에서 가져온 결과를 저장할 문자열 배열 선언
        String[] callSet= new String[]{
                CallLog.Calls.DATE, //통화날짜
                CallLog.Calls.TYPE, //발신,착신
                CallLog.Calls.NUMBER, //전화번호
                CallLog.Calls.DURATION //통화기록
            };

        //커서를 생성하여 통화기록을 가져와서 위의 선언한 문자열배열에 저장하고 커서에 넣는다
        Cursor cursor= getContentResolver().query(CallLog.Calls.CONTENT_URI,callSet, null,null,null);
        if(cursor== null)
            return "통화기록 없음";

        //결과를 저장할 가변성 문자열 버퍼 선언 
        StringBuffer callBuff= new StringBuffer();
        callBuff.append("\n 날짜 : 구분 : 전화번호 : 통화시간\n\n");
        cursor.moveToFirst(); //커서를 움직여서 첫번째(저장한 문자열배열의)줄 읽는다
        do{
            //저장한 문자열 배열 0번째 값(통화기록 날짜)을 가져와서 가공
            long callDate= cursor.getLong(0); 
            SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd"); 
            String data_str= dateFormat.format(new Date(callDate));
            callBuff.append(data_str+":"); //버퍼에 값을 추가 ':' 데이터 구분을 위해 작성
            //저장한 문자열 배열 1번째 값(착신,발신여부확인)을 가져와서 
            if(cursor.getInt(1)== CallLog.Calls.INCOMING_TYPE) 
                callBuff.append("착신 :"); //버퍼에 값을 추가 ':'로  데이터 구분
            else
                callBuff.append("발신 :");
            //저장한 문자열 배열 2번째 값(전화번호)을 가져와서 버퍼에 추가 ':'로 구분
            callBuff.append(cursor.getString(2)+":");
            //저장한 문자열 배열 3번째 값(통화시간:초단위)을 가져와서 버퍼에 추가 ':'로구분
            callBuff.append(cursor.getString(3)+"초\n");
        }while (cursor.moveToNext()); //커서 움직여서 다음줄 읽는다, 커서의 값이 있을때까지 반복하여 버퍼에 넣는다

        cursor.close(); //커서 종료

        return callBuff.toString(); //버퍼를 문자열로 변환하여 반환한다
    }
}