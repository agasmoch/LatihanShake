package com.agasmochfauzan.shakeshake;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

public class TiltedActivity extends AppCompatActivity {

    private TiltedDetector mDetector;
    private Sensor mAccelerometer;
    private SensorManager mManager;
    private ImageView mView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tilted);

        mView = findViewById(R.layout.imageView);
        mManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer= mManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mDetector = new TiltedDetector(new TiltedDetector.OnTiltedListener() {
            @Override
            public void leftTilted() {
                Log.d("TILT","Left tilted");
                if (mView.getRight() +5 <0)
                    mView.setRight(0);
                else
                    mView.setRight(mView.getRight()+5);
            }

            @Override
            public void rightTiled() {
                Log.d("TILT","Right tilted");

                if (mView.getLeft() -5 <0)
                    mView.setLeft(0);
                else
                    mView.setLeft(mView.getLeft()-5);
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
