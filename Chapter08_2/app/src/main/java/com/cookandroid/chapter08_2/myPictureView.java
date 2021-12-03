package com.cookandroid.chapter08_2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class myPictureView extends View { //View 를 상속받아서 onDraw 를 오버라이딩
    /*실습 8-2 간단 이미지 뷰어 만들기 */
    String imagePath= null; //보여줄 이미지 파일의 경로 및 파일이름을 저정할 변수 선언
    //Bitmap 사용 생성자 설정
    public myPictureView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) { //그림을 그리는 오버라이딩 메서드
        super.onDraw(canvas);
        //imagePath 값이 있을경우(이미지파일의 경로및 파일이름이 지정될경우) 화면에 그림파일을 출력한다
        if(imagePath!= null){
            Bitmap bitmap= BitmapFactory.decodeFile(imagePath); //그림파일을 가져와서
            canvas.scale(3, 3, 0, 0); //크기를 설정하고
            canvas.drawBitmap(bitmap,0,0,null); //canvas 에 가져온 그림파일을 넣는다
            bitmap.recycle();
        }
    }
}
