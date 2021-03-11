package org.software.meister.gsm.aclass;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

/*
 스레드 - 비동기 (병렬) 처리를 위해 사용
 비쥬열 버신 방식 App - 스레드가 오류가ㅑ 날때
 프로그램 자체를 중지시키지 않고 스레드만 중지시킴.

 ANR(Application Not Respond)
 안드로이드 Activity 코드 처리를 위해 스레드를 발생시킴. - Main Thread

 - Main Thread = UI Thread

 메인 스레드가 화면을 구성해야 하는데 완료되지 못했을 때 빈 화면이 나옴
 - 그 상태에서 터치나 작업이 발생할 경우 ANR 이 발생하게 됨

 */

public class MainActivity extends AppCompatActivity {

    TextView textView, textView2;

    boolean isRunning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);


    }

    public void btnMethod(View v){
        // 현재 시간을 ms로 가져옴
        long now = System.currentTimeMillis();
        textView.setText("버튼 클릭에 의한 내용 갱신:" + now);
        isRunning = true;

        ThreadClass threadClass = new ThreadClass();

        threadClass.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        isRunning = false;
    }

    class ThreadClass extends Thread {
        @Override
        public void run() {
            while(isRunning){
                SystemClock.sleep(100); //100ms 동안 대기
                long now = System.currentTimeMillis();
                // Log.d("test", "쓰레드에 의한 값 갱신" + now);
                textView2.setText("무한 루프: " + now);
            }
        }
    }
}