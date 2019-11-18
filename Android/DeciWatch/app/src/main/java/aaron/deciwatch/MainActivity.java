package aaron.deciwatch;

import android.support.v7.app.AppCompatActivity;
import android.os.Handler;
import android.os.Bundle;
import android.widget.TextView;

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
            int h = Calendar.getInstance().getTime().getHours();
            int m = Calendar.getInstance().getTime().getMinutes();
            int s = Calendar.getInstance().getTime().getSeconds();
            int sec = h * 3600 + m * 60 + s;
            double dec = sec / 0.864;
            int dh = (int) (dec / 10000);
            int dm = (int) ((dec - dh * 10000) / 100);
            int ds = (int) (dec - dh * 10000 - dm * 100);
            String dtime = String.format(Locale.getDefault(),
                    "\n%02d:%02d:%02d\n%02d:%02d:%02d", h, m, s, dh, dm, ds);
            myTextView.setText(dtime);

            handler.postDelayed(runnable, 1000);
        }
    };
}
