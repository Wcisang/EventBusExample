package com.gwr.eventbusexample;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.gwr.eventbusexample.eventbus.MessageEB;
import com.gwr.eventbusexample.fragments.FragmentBottom;
import com.gwr.eventbusexample.fragments.FragmentTop;
import com.gwr.eventbusexample.service.MyService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EventBus.getDefault().register(MainActivity.this);

        Intent it = new Intent(MainActivity.this, MyService.class);
        startService(it);

        FragmentTransaction mFragmentTransaction = getSupportFragmentManager().beginTransaction();
        FragmentTop mFragmentTop = new FragmentTop();
        FragmentBottom mFragmentBottom = new FragmentBottom();

        mFragmentTransaction.replace(R.id.llContainerFragmentTop, mFragmentTop);
        mFragmentTransaction.replace(R.id.llContainerFragmentBottom, mFragmentBottom);
        mFragmentTransaction.commit();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();


        Intent it = new Intent(MainActivity.this, MyService.class);
        stopService(it);

        EventBus.getDefault().unregister(MainActivity.this);
    }

    public void askAboutPerson (View view){

        MessageEB n = new MessageEB();
        n.setClassTester(MyService.class+"");


        EventBus.getDefault().post(n);

    }

    public void callSecondActivity(View view){
        Intent it = new Intent(MainActivity.this,SecondActivity.class);
        startActivity(it);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(MessageEB nMessageEB){
        if(!nMessageEB.getClassTester().equalsIgnoreCase(MainActivity.class+"")){
            return;
        }
        Log.i("LOG","MainActivity.this.onEventMainThread()");

        if (nMessageEB.getList() != null){
            Toast.makeText(MainActivity.this,"Name: "+nMessageEB.getList().get(0).getName()+"\nJob: "+nMessageEB.getList().get(0).getJob(),Toast.LENGTH_LONG).show();
        }
    }

    @Subscribe
    public void onEvent(MessageEB nMessageEB){
        if(!nMessageEB.getClassTester().equalsIgnoreCase(MainActivity.class+"")){
            return;
        }

        Log.i("LOG","MainActivity.this.onEvent()");
        if(nMessageEB.getNumber() >= 0){
            Log.i("LOG","MainActivity.this.onEvent().number "+nMessageEB.getNumber());

        }

        if(nMessageEB.getText() != null){
            Log.i("LOG","MainActivity.this.onEvent().text "+nMessageEB.getText());

        }



    }
}
