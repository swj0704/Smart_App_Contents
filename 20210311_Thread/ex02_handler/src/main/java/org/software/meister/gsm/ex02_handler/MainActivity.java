package org.software.meister.gsm.ex02_handler;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.view.View;
import android.widget.TextView;

    /*
Handler는 개발자가 안드로이드 OS에 작업 수행을 요청하는 역할을 함
무한루프 형태로 사용하게 되면 화면 처리가 불가능한 경우가 발생
Handler를 사용하여 원하는 코드를 반복해서 작업하는 것이 가능

개발자가 (Handler를 통해) 작업을 안드로이드 OS에 요청하면
안드로이드 OS는 작업을 하지 않을 때 그 작업을 처리하게 됨
그 처리는 Main Thread에서 함.

전제 조건 - 작업 한번이 오래 걸리지 않아야 함 (5초 이내)
 */

public class MainActivity extends AppCompatActivity {

    TextView text1, text2;

    //Handler 만들기 (android.os에 있는)
    Handler handler = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text1 = findViewById(R.id.textView);
        text2 = findViewById(R.id.textView2);

        //handler는 oncreate 내에서 생성해야함

        handler = new Handler();

        ThreadClass threadClass = new ThreadClass();

        handler.post(threadClass);
    }

    public void btnMethod(View v){
        // 현재 시간을 ms로 가져옴
        long now = System.currentTimeMillis();
        text1.setText("버튼 클릭에 의한 내용 갱신:" + now);

    }

    class ThreadClass extends Thread {
        @Override
        public void run() {
            while(true){
                SystemClock.sleep(100); //100ms 동안 대기
                long now = System.currentTimeMillis();
                // Log.d("test", "쓰레드에 의한 값 갱신" + now);
                text2.setText("무한 루프: " + now);

                //핸들러 안에서 현재 작업을 다시 요청
                handler.postDelayed(this, 100);
            }
        }
    }
}