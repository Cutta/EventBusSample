package eventbussample.cunoraz.com.eventbussample;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import de.greenrobot.event.EventBus;
import eventbussample.cunoraz.com.eventbussample.Events.ActivityEvent;
import eventbussample.cunoraz.com.eventbussample.Events.SampleEvent;
import eventbussample.cunoraz.com.eventbussample.Fragments.FirstFragment;
import eventbussample.cunoraz.com.eventbussample.Fragments.SecondFragment;

public class MainActivity extends AppCompatActivity {

    TextView tvEventMessage;
    Button btnSendToFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**Register to receive events*/
        EventBus.getDefault().register(this);
        setContentView(R.layout.activity_main);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        tvEventMessage = (TextView) findViewById(R.id.tv_activity_main_event_message);
        btnSendToFragments = (Button) findViewById(R.id.button_send_to_fragments);

        loadFragments();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnSendToFragments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new ActivityEvent("Event sent by MainActivity"));
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Evenbus is a miracle!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void loadFragments() {
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.frame_keyboard, FirstFragment.getInstance())
                .add(R.id.frame_mouse, SecondFragment.getInstance())
                .commit();
    }

    @Override
    protected void onDestroy() {
        /**
         * You need to unregister EventBus while your activity destroying.
         * Create a MainActivity class and move this code MainActivity's onDestroy method to avoid code repetition.
         * */
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    /**
     * EventBus uses this method to pass specified event to classes which is registered
     */
    public void onEvent(SampleEvent event) {
        Log.d("MainActivity", "Event message: " + event.getMessage());
        tvEventMessage.setText(event.getMessage());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
