package com.agasmochfauzan.shakeshake;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

public class TiltedDetector implements SensorEventListener {

    private OnTiltedListener mListener;

    interface OnTiltedListener {
        void leftTilted();
        void rightTiled();
    }


    public TiltedDetector(OnTiltedListener listener) {
        this.mListener = listener;
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (Math.abs(sensorEvent.values[0])<=2f) return;
        if (sensorEvent.values[0] < 0)
            mListener.leftTilted();
        else
            mListener.rightTiled();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
