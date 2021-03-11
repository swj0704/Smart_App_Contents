package org.software.meister.gsm.class_kotlin

import android.app.Activity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var isRunning = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun btnMethod(v: View){
        val now = System.currentTimeMillis()
        textView1.text = "버튼 클릭에 의한 내용 변경 $now"
        isRunning = true
        ThreadClass(isRunning).start()
    }

    inner class ThreadClass(private val isRunning : Boolean) : Thread(){
        override fun run() {
            while (isRunning) {
                SystemClock.sleep(100) //100ms 동안 대기
                val now = System.currentTimeMillis()
                // Log.d("test", "쓰레드에 의한 값 갱신$now")
                textView2.text = "쓰레드에 의한 값 갱신 : $now"
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        isRunning = false
    }
}