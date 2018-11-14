package com.udacity.diynotes.ui.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

import com.udacity.diynotes.R;

import java.util.List;

/**
 * Implementation of App Widget functionality.
 */
public class BookListWidget extends AppWidgetProvider {
    private static final String TAG = BookListWidget.class.getSimpleName();
    private static final String BOOK_NAMES = "book_names";

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        CharSequence widgetText = "DYINotes";
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.book_list_widget);
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

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);

        final List<String> bookNames = intent.getStringArrayListExtra(BOOK_NAMES);

        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.book_list_widget);

        if (bookNames != null) {
            views.setTextViewText(R.id.appwidget_text, processBookNames(bookNames));

            // This time we dont have widgetId. Reaching our widget with that way.
            ComponentName appWidget = new ComponentName(context, BookListWidget.class);
            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
            // Instruct the widget manager to update the widget
            appWidgetManager.updateAppWidget(appWidget, views);
        }
    }

    private String processBookNames(List<String> bookNames) {
        String formattedNames = "";

        for (String name: bookNames) {
            formattedNames = formattedNames + name + "\n";
        }

        return formattedNames;
    }
}

