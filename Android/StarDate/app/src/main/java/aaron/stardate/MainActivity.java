package aaron.stardate;

import android.os.Handler;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import org.joda.time.Days;
import org.joda.time.LocalDateTime;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private TextView myTextView;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myTextView = findViewById(R.id.textView01);
        handler.post(runnable);
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            LocalDateTime dayZero = new LocalDateTime(1961,4,12,0,0,0,0);
            LocalDateTime today = new LocalDateTime();
            int d = Days.daysBetween(dayZero, today).getDays();
            int h = today.getHourOfDay();
            int m = today.getMinuteOfHour();
            int s = today.getSecondOfMinute();
            int ms = today.getMillisOfSecond();
            float sec = h * 3600 + m * 60 + s + (float) ms/1000;
            int dec = (int) Math.floor(sec / 0.864);
            int decond = (int) Math.round((1-(sec/.864)%1)*864);

            String sdate = String.format(Locale.getDefault(),
                    "\nStarDate\n%05d.%05d", d, dec);
            myTextView.setText(sdate);

            handler.postDelayed(runnable, decond);
        }
    };
}
