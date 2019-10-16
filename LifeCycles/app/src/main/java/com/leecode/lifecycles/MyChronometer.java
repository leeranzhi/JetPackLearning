package com.leecode.lifecycles;

import android.content.Context;
import android.os.SystemClock;
import android.widget.Chronometer;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

public class MyChronometer extends Chronometer implements LifecycleObserver {
  private long elapsedTime;
  public MyChronometer(Context context) {
    super(context);
  }
  @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
  private void pasueMeter(){
      elapsedTime= SystemClock.elapsedRealtime()-getBase();
      stop();
  }
  @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
  private void resumeMeter(){
    setBase(SystemClock.elapsedRealtime()-elapsedTime);
    start();
  }
}
