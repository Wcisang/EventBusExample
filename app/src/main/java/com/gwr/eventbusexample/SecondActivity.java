package com.gwr.eventbusexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.gwr.eventbusexample.eventbus.MessageEB;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        EventBus.getDefault().register(SecondActivity.this);


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(SecondActivity.this);
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onEvent(MessageEB nMessageEB) {

        Log.i("LOG","SecondActivity.this.onEventMainThread()");

        if (nMessageEB.getList() != null){
            Toast.makeText(SecondActivity.this,"Name: "+nMessageEB.getList().get(0).getName()+"\nJob: "+nMessageEB.getList().get(0).getJob(),Toast.LENGTH_LONG).show();
        }
    }
}
