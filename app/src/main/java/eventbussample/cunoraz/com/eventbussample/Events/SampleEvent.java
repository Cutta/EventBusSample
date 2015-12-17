package eventbussample.cunoraz.com.eventbussample.Events;

/**
 * Created by Cuneyt on 17.12.2015.
 *
 * This is just a sample class to pass event data between fragments and activities.
 */
public class SampleEvent {
    String message;

    public SampleEvent(String message){
        this.message=message;
    }

    public String getMessage() {
        return message;
    }


}
