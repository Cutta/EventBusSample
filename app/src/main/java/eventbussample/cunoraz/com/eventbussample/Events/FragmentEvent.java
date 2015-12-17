package eventbussample.cunoraz.com.eventbussample.Events;

/**
 * Created by Cuneyt on 17.12.2015.
 *
 * This is just a sample class to pass event data between fragments and activities.
 */
public class FragmentEvent {
    String message;
    String title; //title is dummy variable. just using to show can pass several parameters

    public FragmentEvent(String message, String title) {
        this.message = message;
        this.title = title;
    }

    public String getMessage() {
        return message;
    }
}
