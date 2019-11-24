package aaron.deciwatch;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.widget.RemoteViews;
import org.joda.time.LocalDateTime;

import java.util.Locale;

public class NewAppWidget extends AppWidgetProvider {

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
    }

    @Override
    public void onDisabled(Context context) {
        super.onDisabled(context);
    }

    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    public static void updateAppWidget(final Context context, final AppWidgetManager appWidgetManager, final int appWidgetId) {
        LocalDateTime today = new LocalDateTime();
        int h = today.getHourOfDay();
        int m = today.getMinuteOfHour();
        int s = today.getSecondOfMinute();
        int ms = today.getMillisOfSecond();
        float sec = h * 3600 + m * 60 + s + (float) ms/1000;
        int dec = (int) Math.floor(sec / .864);
        int decond = (int) Math.round((1-(sec/.864)%1)*864);
        int dh = (int) (dec / 10000);
        int dm = (int) ((dec - dh * 10000) / 100);
        int ds = (int) (dec - dh * 10000 - dm * 100);
        String dtime = String.format(Locale.getDefault(),
                "%02d:%02d:%02d\n%02d:%02d:%02d", h, m, s, dh, dm, ds);

        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);
        views.setTextViewText(R.id.widget_text, dtime);
        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);

        new Handler().postDelayed(new Runnable(){
            public void run(){
                updateAppWidget(context, appWidgetManager, appWidgetId);
            }
        },decond);
    }
}