package com.ustc.wsn.mydataapp.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.os.Handler;
import android.view.Gravity;
import android.widget.Button;
import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.ServiceConnection;
import android.location.LocationManager;
import android.os.IBinder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

import com.ustc.wsn.mydataapp.service.DetectorService;

import java.util.Timer;
import java.util.TimerTask;

import detector.wsn.ustc.com.mydataapp.R;

public class LabelActivity extends Activity implements OnClickListener {

    protected final String TAG = LabelActivity.this.toString();
    private DetectorService.MyBinder service;
    private MyConnection conn;
    private Intent DetectorserviceIntent;
    private Toast t;
    private Timer timer;

    private RadioButton Static;
    private RadioButton Walk;
    private RadioButton Run;
    private RadioButton Elevator;
    private RadioButton Bike;
    private RadioButton Car;
    private RadioButton Upstairs;
    private RadioButton Downstairs;
    private RadioButton StopLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_label);
        conn = new MyConnection();
        DetectorserviceIntent = new Intent(this, DetectorService.class);
        bindService(DetectorserviceIntent, conn, Context.BIND_AUTO_CREATE);

        Button cofirmLabel = (Button) findViewById(R.id.btncofirmLabel);
        cofirmLabel.setOnClickListener(this);

        RadioGroup signlabel = (RadioGroup) findViewById(R.id.signLabel);

        Static = (RadioButton) findViewById(R.id.btnStatic);
        Walk = (RadioButton) findViewById(R.id.btnWalk);
        Run = (RadioButton) findViewById(R.id.btnRun);
        Elevator = (RadioButton) findViewById(R.id.btnElevator);
        Bike = (RadioButton) findViewById(R.id.btnBike);
        Car = (RadioButton) findViewById(R.id.btnCar);
        Upstairs = (RadioButton) findViewById(R.id.btnUpstairs);
        Downstairs = (RadioButton) findViewById(R.id.btnDownstairs);
        StopLabel = (RadioButton) findViewById(R.id.btnStopLabel);

        StopLabel.setChecked(true);
/*
        switch (service.getLabel()){
            case 1:
                Static.setChecked(true);
                break;
            case 2:
                Walk.setChecked(true);
                break;
            case 3:
                Run.setChecked(true);
                break;
            case 4:
                Elevator.setChecked(true);
                break;
            case 5:
                Bike.setChecked(true);
                break;
            case 6:
                Car.setChecked(true);
                break;
            case 7:
                Upstairs.setChecked(true);
                break;
            case 8:
                Downstairs.setChecked(true);
                break;
            case 0:
                StopLabel.setChecked(true);
                break;
        }
*/
        signlabel.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup signlabel, int checkedId) {
                RadioButton label = (RadioButton) findViewById(checkedId);
                switch (checkedId) {
                    case R.id.btnStatic:
                        service.setLabel(1);
                        break;
                    case R.id.btnWalk:
                        service.setLabel(2);
                        break;
                    case R.id.btnRun:
                        service.setLabel(3);
                        break;
                    case R.id.btnElevator:
                        service.setLabel(4);
                        break;
                    case R.id.btnBike:
                        service.setLabel(5);
                        break;
                    case R.id.btnCar:
                        service.setLabel(6);
                        break;
                    case R.id.btnUpstairs:
                        service.setLabel(7);
                        break;
                    case R.id.btnDownstairs:
                        service.setLabel(8);
                        break;
                    case R.id.btnStopLabel:
                        service.setLabel(0);
                        break;
                }
                t = Toast.makeText(getApplicationContext(), "当前标签是:" + label.getText(), Toast.LENGTH_LONG);
                t.setGravity(Gravity.CENTER, 0, 0);
                t.show();
            }
        });

    }

    public void binderClick(View v) {
        bindService(DetectorserviceIntent, conn, Context.BIND_AUTO_CREATE);
    }

    private class MyConnection implements ServiceConnection {
        @Override
        public void onServiceConnected(ComponentName name, IBinder binder) {
            service = (DetectorService.MyBinder) binder;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }

    @SuppressLint("ShowToast")
    public void onClick(View view) {
        // TODO Auto-generated method stub
        if (view.getId() == R.id.btncofirmLabel) {
            finish();
        }
    }
/*
    @SuppressLint("ShowToast")
    public void onClick(View view) {
        // TODO Auto-generated method stub
        //t = Toast.makeText(this, "Start", Toast.LENGTH_LONG);
        switch (view.getId()) {
            case R.id.btnStatic:
                service.setLabel(1);
                t = Toast.makeText(this, "<1>当前状态：静止！", Toast.LENGTH_SHORT);
                t.setGravity(Gravity.CENTER, 0, 0);
                t.show();
                break;
            case R.id.btnWalk:
                t = Toast.makeText(this, "<2> 当前状态：步行", Toast.LENGTH_SHORT);
                t.setGravity(Gravity.CENTER, 0, 0);
                t.show();
                ;
                service.setLabel(2);
                break;
            case R.id.btnRun:
                t = Toast.makeText(this, "<3> 当前状态：跑步", Toast.LENGTH_SHORT);
                t.setGravity(Gravity.CENTER, 0, 0);
                t.show();
                service.setLabel(3);
                break;
            case R.id.btnElevator:
                t = Toast.makeText(this, "<4> 当前状态：乘坐电梯", Toast.LENGTH_SHORT);
                t.setGravity(Gravity.CENTER, 0, 0);
                t.show();
                service.setLabel(4);
                break;
            case R.id.btnBike:
                t = Toast.makeText(this, "<5> 当前状态：骑自行车", Toast.LENGTH_SHORT);
                t.setGravity(Gravity.CENTER, 0, 0);
                t.show();
                service.setLabel(5);
                break;
            case R.id.btnCar:
                t = Toast.makeText(this, "<6> 当前状态：坐车", Toast.LENGTH_SHORT);
                t.setGravity(Gravity.CENTER, 0, 0);
                t.show();
                service.setLabel(6);
                break;
            case R.id.btnUpstairs:
                t = Toast.makeText(this, "<7>当前状态：上楼梯", Toast.LENGTH_SHORT);
                t.setGravity(Gravity.CENTER, 0, 0);
                t.show();
                service.setLabel(7);
                break;
            case R.id.btnDownstairs:
                t = Toast.makeText(this, "<8> 当前状态：下楼梯", Toast.LENGTH_SHORT);
                t.setGravity(Gravity.CENTER, 0, 0);
                t.show();
                service.setLabel(8);
                break;
            case R.id.btnStopLabel:
                t = Toast.makeText(this, "已停止标记", Toast.LENGTH_SHORT);
                t.setGravity(Gravity.CENTER, 0, 0);
                t.show();
                service.setLabel(0);
                break;
        }
    }
    */

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        if (conn != null) unbindService(conn);
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
    }
}
