package com.agasmochfauzan.shakeshake;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;

public class TiltedActivity extends AppCompatActivity {

    private TiltedDetector mDetector;
    private Sensor mAccelerometer;
    private SensorManager mManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tilted);


        mManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer= mManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mDetector = new TiltedDetector(new TiltedDetector.OnTiltedListener() {
            @Override
            public void leftTilted() {
                Log.d("TILT","Left tilted");
            }

            @Override
            public void rightTiled() {
                Log.d("TILT","Right tilted");
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
