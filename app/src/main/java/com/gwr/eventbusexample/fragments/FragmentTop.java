package com.gwr.eventbusexample.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.gwr.eventbusexample.R;
import com.gwr.eventbusexample.eventbus.MessageEB;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by willi on 14/06/2016.
 */
public class FragmentTop extends Fragment implements View.OnClickListener {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_top,null);
        Button btn = (Button) view.findViewById(R.id.btDataFragmentBottom);
        btn.setOnClickListener(FragmentTop.this);

        EventBus.getDefault().register(FragmentTop.this);
        return view;
    }

    @Override
    public void onClick(View v) {
        Log.i("LOG", "FragmentTop.onClick()");

        MessageEB n = new MessageEB();
        n.setClassTester(FragmentBottom.class+"");
        n.setText("This message came from FragmentTop");

        EventBus.getDefault().post(n);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        EventBus.getDefault().unregister(FragmentTop.this);
    }

    @Subscribe
    public void onEvent(MessageEB nMessageEB){
        if(!nMessageEB.getClassTester().equalsIgnoreCase(FragmentTop.class+"")){
            return;
        }
        Log.i("LOG","FragmentTop.this.onEvent()");
        Toast.makeText(getActivity(),nMessageEB.getText(),Toast.LENGTH_LONG).show();
    }
}
