package eventbussample.cunoraz.com.eventbussample.Events;

/**
 * Created by Cuneyt on 17.12.2015.
 * This is just a sample class to pass event data from activity to fragments.
 */
public class ActivityEvent {
    String message;

    public ActivityEvent(String message){
        this.message = message;
    }

    public String getMessage() {
        return message;
    }


}
