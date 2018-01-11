package com.gwr.eventbusexample.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.gwr.eventbusexample.MainActivity;
import com.gwr.eventbusexample.domain.Person;
import com.gwr.eventbusexample.eventbus.MessageEB;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }

    @Override
    public void onCreate() {
        Log.i("LOG", "MyService.onCreate()");

        EventBus.getDefault().register(MyService.this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("LOG", "MyService.onStartCommand()");
        countThread();
        return super.onStartCommand(intent, flags, startId);
    }

    private void countThread() {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i =0 ; i<2 ; i++){

                    MessageEB n = new MessageEB();
                    n.setClassTester(MainActivity.class+"");
                    n.setNumber(i+1);
                    n.setText("Random Message "+(i+1));

                    EventBus.getDefault().post(n);


                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        t.start();
    }

    @Subscribe
    public void onEvent(MessageEB nMessageEB) {
        if (!nMessageEB.getClassTester().equalsIgnoreCase(MyService.class + "")) {
            return;
        }

        Log.i("LOG", "MyService.this.onEvent()");

        Person p = new Person("William", "Programador");
        List<Person> list = new ArrayList<Person>();
        list.add(p);
        nMessageEB.setClassTester(MainActivity.class+"");
        nMessageEB.setList(list);
        EventBus.getDefault().postSticky(nMessageEB);


    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        EventBus.getDefault().unregister(MyService.this);
    }
}
