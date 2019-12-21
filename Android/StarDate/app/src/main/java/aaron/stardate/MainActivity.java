package aaron.stardate;

import android.os.Handler;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import org.joda.time.Days;
import org.joda.time.LocalDateTime;
import java.util.Locale;
import android.media.MediaPlayer;

public class MainActivity extends AppCompatActivity {
    private TextView myTextView1;
    private TextView myTextView2;
    private TextView myTextView3;
    private Handler handler = new Handler();
    private int pressed = 0;

    public void buttonPressed(View view) {
        final MediaPlayer mp = MediaPlayer.create(this, R.raw.chirp);
        mp.start();
        if (pressed==0){
            pressed = 1;
        }
        else {
            pressed=0;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myTextView1 = findViewById(R.id.textView01);
        myTextView2 = findViewById(R.id.textView02);
        myTextView3 = findViewById(R.id.textView03);
        handler.post(runnable);
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            LocalDateTime dayZero = new LocalDateTime(1961,4,12,0,0,0,0);
            LocalDateTime today = new LocalDateTime();
            int sd = Days.daysBetween(dayZero, today).getDays();
            int h = today.getHourOfDay();
            int m = today.getMinuteOfHour();
            int s = today.getSecondOfMinute();
            int ms = today.getMillisOfSecond();
            float sec = h * 3600 + m * 60 + s + (float) ms/1000;
            int dec = (int) Math.floor(sec / 0.864);

            String sdate = String.format(Locale.getDefault(),
                    "\nStar*Date\n%05d.%05d", sd, dec);
            myTextView1.setText(sdate);

            if (pressed==0){
                myTextView2.setText("");
                myTextView3.setText("");
            }
            else {
                myTextView2.setText("\nThis represents the number of days elapsed since 12-Apr-1961, the date on which Yuri Gagarin became the first human to journey into outer-space!\n\nA decimal representation is used for inter-day timing, with each day being divided into 100,000 units, equal to 0.864 standard seconds each.\n");
                String now = today.toString("EEEE, dd-MMM-yyyy, HH:mm:ss");
                String dt = String.format(Locale.getDefault(),
                        "The normal date and time is:\n%s\n",now);
                myTextView3.setText(dt);
            }

            handler.postDelayed(runnable, 96);
        }
    };
}
