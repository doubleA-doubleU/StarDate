package aaron.stardate;

import android.os.Handler;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import org.joda.time.Days;
import org.joda.time.LocalDate;

import java.util.Calendar;
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
            LocalDate dayZero = new LocalDate(1961,4,12);
            LocalDate today = new LocalDate();
            int d = Days.daysBetween(dayZero, today).getDays();

            int h = Calendar.getInstance().getTime().getHours();
            int m = Calendar.getInstance().getTime().getMinutes();
            int s = Calendar.getInstance().getTime().getSeconds();
            int sec = h * 3600 + m * 60 + s;
            int dec = (int) Math.floor(sec / 0.864);
            String sdate = String.format(Locale.getDefault(),
                    "%05d.%05d", d, dec);
            myTextView.setText(sdate);

            handler.postDelayed(runnable, 1000);
        }
    };
}
