package aaron.deciwatch;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.widget.RemoteViews;
import java.util.Calendar;
import java.util.Locale;

/**
 * Implementation of App Widget functionality.
 */
public class NewAppWidget extends AppWidgetProvider {
    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {
        int h = Calendar.getInstance().getTime().getHours();
        int m = Calendar.getInstance().getTime().getMinutes();
        int s = Calendar.getInstance().getTime().getSeconds();
        int sec = h*3600 + m*60 + s;
        double dec = sec/0.864;
        int dh = (int) (dec/10000);
        int dm = (int) ((dec - dh*10000) / 100);
        int ds = (int) (dec - dh*10000 - dm*100);
        CharSequence widgetText = String.format(Locale.getDefault(),
                "%02d:%02d:%02d\n%02d:%02d:%02d", h, m, s, dh, dm, ds);

        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);
        views.setTextViewText(R.id.appwidget_text, widgetText);
        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

