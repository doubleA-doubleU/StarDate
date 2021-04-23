package aaron.stardate;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.widget.RemoteViews;
import org.joda.time.LocalDateTime;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import static android.appwidget.AppWidgetManager.ACTION_APPWIDGET_UPDATE;

public class Standard12hrAppWidget extends AppWidgetProvider {

    private PendingIntent RepeatingIntent(Context context) {
        Intent intent = new Intent(ACTION_APPWIDGET_UPDATE);
        return PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        if (ACTION_APPWIDGET_UPDATE.equals(intent.getAction())) {
            ComponentName thisAppWidget = new ComponentName(context.getPackageName(), getClass().getName());
            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
            int[] ids = appWidgetManager.getAppWidgetIds(thisAppWidget);
            for (int appWidgetID : ids) {
                updateAppWidget(context, appWidgetManager, appWidgetID);
            }
        }
    }

    @Override
    public void onDisabled(Context context) {
        super.onDisabled(context);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(RepeatingIntent(context));
    }

    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Calendar start = Calendar.getInstance();
        start.setTimeInMillis(System.currentTimeMillis());
        LocalDateTime now = new LocalDateTime();
        int delay = 60000 - 1000*now.getSecondOfMinute() - now.getMillisOfSecond();
        start.add(Calendar.MILLISECOND, delay);
        alarmManager.setRepeating(AlarmManager.RTC, start.getTimeInMillis(), 60000, RepeatingIntent(context));
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    public static void updateAppWidget(final Context context, final AppWidgetManager appWidgetManager, final int appWidgetId) {
        DateFormat dateFormat = new SimpleDateFormat(" h:mm:ss aa ");
        String time12 = dateFormat.format(new Date());

        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.standard12hr_app_widget);
        views.setTextViewText(R.id.std12_widget_text, time12);
        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);

        new Handler().postDelayed(new Runnable(){
            public void run(){
                updateAppWidget(context, appWidgetManager, appWidgetId);
            }
        },96);
    }
}