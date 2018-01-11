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
public class FragmentBottom extends Fragment implements View.OnClickListener {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bottom,null);
        Button btn = (Button) view.findViewById(R.id.btDataFragmentTop);
        btn.setOnClickListener(FragmentBottom.this);

        EventBus.getDefault().register(FragmentBottom.this);
        return view;
    }

    @Override
    public void onClick(View v) {
        Log.i("LOG", "FragmentBottom.onClick()");

        MessageEB n = new MessageEB();
        n.setClassTester(FragmentTop.class+"");
        n.setText("This message came from FragmentBottom");

        EventBus.getDefault().post(n);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        EventBus.getDefault().unregister(FragmentBottom.this);
    }

    @Subscribe
    public void onEvent(MessageEB nMessageEB){
        if(!nMessageEB.getClassTester().equalsIgnoreCase(FragmentBottom.class+"")){
            return;
        }
        Log.i("LOG","FragmentBottom.this.onEvent()");
        Toast.makeText(getActivity(),nMessageEB.getText(),Toast.LENGTH_LONG).show();
    }
}
