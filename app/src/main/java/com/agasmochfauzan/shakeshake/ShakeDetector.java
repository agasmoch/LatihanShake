package com.agasmochfauzan.shakeshake;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class ShakeDetector implements SensorEventListener {

    private static final float SHAKE_TRESHOLD = 2.7f; //Konstanta untuk shake (Jangan pakai nilai 1/0)
    private static final int SHAKE_TIME = 500; //Batas Minimal Shake Time Arah Kanan-Kiri Harus Lebih dari 500
    private static final int SHAKE_COUNT_RESET = 3000 ; //Reset SHAKE dalam 3 Detik

    private ShakeDetector.OnShakeListener mListiner;
    private long mShakeTimestamp;
    private int mShakeCount;

    interface OnShakeListener {
        void onShake (int count);
    }

    public ShakeDetector(ShakeDetector.OnShakeListener listener) {
        this.mListiner = listener;
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (mListiner==null) return;

        float x = sensorEvent.values[0] / SensorManager.GRAVITY_EARTH; //Ini nilai goyangannya
        float y = sensorEvent.values[1] / SensorManager.GRAVITY_EARTH;
        float z = sensorEvent.values[2] / SensorManager.GRAVITY_EARTH;
        float gForce = (float) Math.sqrt(x*x +y*y +z*z); //Gforce
        if (gForce > SHAKE_TRESHOLD){
            long now = System.currentTimeMillis();
            if (mShakeTimestamp+SHAKE_TIME > now) return;

            if (mShakeTimestamp + SHAKE_COUNT_RESET < now){
                mShakeTimestamp=0;
            }
            mShakeTimestamp=now;
            mShakeCount++;
            mListiner.onShake(mShakeCount);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}

