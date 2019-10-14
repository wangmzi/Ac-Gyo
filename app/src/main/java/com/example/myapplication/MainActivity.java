package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private static final String TAG = "MainActivity";
    private SensorManager sensorManager;
    //SensorManager lets you access the device's
    private Sensor accelerometer, gyroscopeSensor;

    //what is TextView
    TextView xValue, yValue, zValue;
    TextView xGyroValue, yGyroValue,zGyroValue;
    //TODO where is accelerometer's listener
    //TODO add the temporary event listen gyroscope

    TextView acTextView, gyTextView;

//in your OnCreate() method

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //common
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //xValue is from the xml file,
        //xValue, yValue snd zValue are all predefined in a class
        //however, you've got to define another value when you are not using the 3 predefined values.
        xValue = (TextView)findViewById(R.id.xValue);
        yValue = (TextView)findViewById(R.id.yValue);
        zValue = (TextView)findViewById(R.id.zValue);

        xGyroValue = (TextView)findViewById(R.id.xGyroValue);
        yGyroValue = (TextView)findViewById(R.id.yGyroValue);
        zGyroValue = (TextView)findViewById(R.id.zGyroValue);

        acTextView= (TextView)findViewById(R.id.acTextView);
        gyTextView = (TextView)findViewById(R.id.gyTextView);

        Log.d(TAG,"OnCreate: Initializing Sensor Services");
        this.sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        this.accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        if(accelerometer == null){
            //TODO what is toast
            Toast.makeText(this,"This device has no Gyroscope.", Toast.LENGTH_SHORT).show();
            finish();
        }
        this.gyroscopeSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        if(this.gyroscopeSensor== null){
            //TODO what is toast
            Toast.makeText(this,"This device has no Gyroscope.", Toast.LENGTH_SHORT).show();
            finish();
        }


       // SensorManager.SENSOR_DELAY_NORMAL : no delay
        sensorManager.registerListener(MainActivity.this,accelerometer,SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(MainActivity.this,gyroscopeSensor,SensorManager.SENSOR_DELAY_NORMAL);
    }


    @Override public void onSensorChanged(SensorEvent event) {
        Sensor sensor = event.sensor;
        if(sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            Log.d(TAG,"onSensorChanged: X: " + event.values[0] +"Y: " + event.values[1]+ "Z: " +event.values[2]);
            acTextView.setText("ACCELEROMETER");
            xValue.setText("xValue: "+event.values[0]);
            yValue.setText("yValue: "+event.values[1]);
            zValue.setText("zValue: "+event.values[2]);
        }
        else if(sensor.getType() == Sensor.TYPE_GYROSCOPE){
            gyTextView.setText("GYROSCOPE");
            xGyroValue.setText("xValue: "+event.values[0]);
            yGyroValue.setText("yValue: "+event.values[1]);
            zGyroValue.setText("zValue: "+event.values[2]);

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {


    }

}
