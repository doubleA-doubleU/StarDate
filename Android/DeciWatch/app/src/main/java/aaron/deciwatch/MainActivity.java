package aaron.deciwatch;

import android.os.Handler;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import org.joda.time.LocalDateTime;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private TextView myTextView1;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myTextView1 = findViewById(R.id.textView01);
        handler.post(runnable);
    }
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            LocalDateTime today = new LocalDateTime();
            int h = today.getHourOfDay();
            int m = today.getMinuteOfHour();
            int s = today.getSecondOfMinute();
            int ms = today.getMillisOfSecond();
            float sec = h * 3600 + m * 60 + s + (float) ms/1000;
            int dec = (int) Math.floor(sec / 0.864);
            int dh = (int) (dec / 10000);
            int dm = (int) ((dec - dh * 10000) / 100);
            int ds = (int) (dec - dh * 10000 - dm * 100);
            String dtime = String.format(Locale.getDefault(),
                    "\n%02d:%02d:%02d\n%02d:%02d:%02d", h, m, s, dh, dm, ds);
            myTextView1.setText(dtime);

            handler.postDelayed(runnable, 96);
        }
    };
}
