package com.example.myapplication

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import android.widget.RemoteViewsService


/**
 * Implementation of App Widget functionality.
 */
class NewAppWidget : AppWidgetProvider() {
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    override fun onReceive(context: Context, intent: Intent) {
        val action = intent.action
        if (action == AppWidgetManager.ACTION_APPWIDGET_UPDATE) {
            // refresh all your widgets
            val mgr = AppWidgetManager.getInstance(context)
            val cn = ComponentName(context, NewAppWidget::class.java)
            mgr.notifyAppWidgetViewDataChanged(mgr.getAppWidgetIds(cn), R.id.list_view_1)
        }
    }
}

internal fun updateAppWidget(
    context: Context,
    appWidgetManager: AppWidgetManager,
    appWidgetId: Int
) {
    // Construct the RemoteViews object
    val views = RemoteViews(context.packageName, R.layout.new_app_widget)
    val intent = Intent(context, A::class.java)
    intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)

    views.setRemoteAdapter(R.id.list_view_1, intent)

    // Instruct the widget manager to   update the widget
    appWidgetManager.updateAppWidget(appWidgetId, views)
}

class A : RemoteViewsService() {
    override fun onGetViewFactory(intent: Intent): RemoteViewsFactory {
        return MyRemoteViewFactory(applicationContext)
    }
}

class MyRemoteViewFactory(val context: Context) : RemoteViewsService.RemoteViewsFactory {
    val list: MutableList<String> = mutableListOf()
    val ID_CONSTANT = 0x0101010

    override fun onCreate() {
        val db = MySQLite(context).writableDatabase
        val cursor = db.query("A", arrayOf("a"), "", null, null, null, null)
        with(cursor) {
            while (moveToNext()) {
                list.add(getString(getColumnIndexOrThrow("a")))
            }
        }
    }

    override fun onDataSetChanged() {
    }

    override fun onDestroy() {
//        TODO("Not yet implemented")
    }

    override fun getCount(): Int {
        return list.size
    }

    override fun getViewAt(p0: Int): RemoteViews {
        val views = RemoteViews(context.packageName, R.layout.item)

        views.setTextViewText(R.id.widgetItemTaskNameLabel, list[p0])
        return views
    }

    override fun getLoadingView(): RemoteViews? {
        return null
    }

    override fun getViewTypeCount(): Int {
        return 1
    }

    override fun getItemId(pos: Int): Long {
        return (ID_CONSTANT + pos).toLong()
    }

    override fun hasStableIds(): Boolean {
        return true
    }

}