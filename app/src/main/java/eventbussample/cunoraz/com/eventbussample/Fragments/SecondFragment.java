package eventbussample.cunoraz.com.eventbussample.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import de.greenrobot.event.EventBus;
import eventbussample.cunoraz.com.eventbussample.R;
import eventbussample.cunoraz.com.eventbussample.Events.ActivityEvent;
import eventbussample.cunoraz.com.eventbussample.Events.FragmentEvent;
import eventbussample.cunoraz.com.eventbussample.Events.SampleEvent;

/**
 *  Created by Cuneyt on 17.12.2015
 */
public class SecondFragment extends Fragment implements View.OnClickListener {
    private Button btnSendToAll;
    private TextView tvMouse;
    private Button btnSendToFragments;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_second, container, false);
        btnSendToAll = (Button) view.findViewById(R.id.btn_fragment_mouse_send_all);
        btnSendToFragments = (Button) view.findViewById(R.id.btn_fragment_mouse_send_to_fragments);
        btnSendToAll.setOnClickListener(this);
        btnSendToFragments.setOnClickListener(this);
        tvMouse = (TextView) view.findViewById(R.id.tv_fragment_mouse);
        return view;
    }

    /**
     * EventBus uses this method to pass specified event to classes which is registered
     */
    public void onEvent(SampleEvent event) {
        Log.d(getClass().getSimpleName(), "Event message: " + event.getMessage());
        tvMouse.setText(event.getMessage());
    }

    /**
     * EventBus uses this method to pass specified event to classes which is registered
     */
    public void onEvent(FragmentEvent event) {
        Log.d(getClass().getSimpleName(), "Event message: " + event.getMessage());
        tvMouse.setText(event.getMessage());
    }

    /**
     * EventBus uses this method to pass specified event to classes which is registered
     */
    public void onEvent(ActivityEvent event) {
        Log.d(getClass().getSimpleName(), "Event message: " + event.getMessage());
        tvMouse.setText(event.getMessage());
    }


    public static SecondFragment getInstance() {
        return new SecondFragment();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**Register to receive events*/
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        /**
         * You need to unregister EventBus while your fragment destroying.
         * Create a BaseFragment class and move this code BaseFragment's onDestroy method to avoid code repetition.
         * */
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_fragment_mouse_send_all:
                EventBus.getDefault().post(new SampleEvent("Event sent by SecondFragment"));
                break;
            case R.id.btn_fragment_mouse_send_to_fragments:
                EventBus.getDefault().post(new FragmentEvent("Event sent by SecondFragment", "I'm title"));
                break;

        }
    }
}
