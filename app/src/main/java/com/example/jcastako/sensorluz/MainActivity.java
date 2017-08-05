package com.example.jcastako.sensorluz;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener{

    SensorManager sManager;
    Sensor sLuz;
    RelativeLayout fondo;
    TextView texto1;
    TextView texto2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fondo = (RelativeLayout) findViewById(R.id.fondo);
        texto1 = (TextView) findViewById(R.id.text1);
        texto2= (TextView) findViewById(R.id.text2);


        sManager = (SensorManager) this.getSystemService(SENSOR_SERVICE);
        sLuz = sManager.getDefaultSensor(Sensor.TYPE_LIGHT);

        if( sLuz != null) {
            texto1.setText("Sensor de luz disponible");
        }else {
            texto1.setText("Sensor de luz no disponible");
        }

    }

    protected void onResume(){
        super.onResume();
        sManager.registerListener(this, sLuz, sManager.SENSOR_DELAY_NORMAL);
    }

    protected void onPause(){
        super.onPause();
        sManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType() == Sensor.TYPE_LIGHT){
            texto2.setText("Luz :" + String.valueOf(event.values[0]));
            if(event.values[0]>=0 && event.values[0]<=10000){
                fondo.setBackgroundResource(R.color.verde1);
            }else if(event.values[0]>10000 && event.values[0]<=20000){
                fondo.setBackgroundResource(R.color.verde2);
            }else if (event.values[0]>20000 && event.values[0]<=30000) {
                fondo.setBackgroundResource(R.color.verde3);
            }else if (event.values[0]>30000 && event.values[0]<=40000) {
                fondo.setBackgroundResource(R.color.verde4);
            }
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
