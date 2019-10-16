package com.leecode.lifecycles;

import android.os.SystemClock;
import android.widget.Chronometer;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

/**
 * 感知生命周期LifeCycles
 */
public class MainActivity extends AppCompatActivity {
  MyChronometer chronometer;
  // private long elapsedTime;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    chronometer = findViewById(R.id.meter);
    getLifecycle().addObserver(chronometer);

    //与系统时区有关,不可靠
    // chronometer.setBase(System.currentTimeMillis()); //UNIX 时间 1970 1-1
    // chronometer.setBase(SystemClock.elapsedRealtime());  //上次开机到现在的毫秒数
    // chronometer.start();
  }

  // @Override protected void onPause() {
  //   super.onPause();
  //   //保存下来毫秒数
  //   elapsedTime = SystemClock.elapsedRealtime() - chronometer.getBase();
  //   //只是视图不会更新，实际上还在继续
  //   chronometer.stop();
  // }
  //
  // @Override protected void onResume() {
  //   super.onResume();
  //   chronometer.setBase(SystemClock.elapsedRealtime() - elapsedTime);
  //   chronometer.start();
  // }
}
