package com.agasmochfauzan.shakeshake;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.Random;

public class MainActivity extends AppCompatActivity{
    private ShakeDetector mDetector;
    private Sensor mAccelerometer;
    private SensorManager mManager;
    private View mBackground;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBackground = findViewById(R.id.background);
        mManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer= mManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mDetector = new ShakeDetector(new ShakeDetector.OnShakeListener() {
            @Override
            public void onShake(int count) {
                Random random = new Random();
                int color = Color.rgb(random.nextInt(256),random.nextInt(256),random.nextInt(256));
                mBackground.setBackgroundColor(color);
           Log.d("SHAKE","Berhasil digoyang"+count+"x");
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mManager.registerListener(mDetector,mAccelerometer,SensorManager.SENSOR_DELAY_UI);

    }

    @Override
    protected void onPause (){
        mManager.unregisterListener(mDetector);
        super.onPause();
    }
}
