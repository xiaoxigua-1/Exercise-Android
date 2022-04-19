package com.example.myapplication

import android.annotation.SuppressLint
import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.provider.BaseColumns
import android.provider.SyncStateContract
import android.view.LayoutInflater
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
        if (intent.action == AppWidgetManager.ACTION_APPWIDGET_UPDATE) {
            val mg = AppWidgetManager.getInstance(context)
            val com = ComponentName(context, NewAppWidget::class.java)
            mg.notifyAppWidgetViewDataChanged(mg.getAppWidgetIds(com), R.id.listView)
            super.onReceive(context, intent)
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
    views.setRemoteAdapter(R.id.listView, Intent(context, B::class.java))
    views.setOnClickPendingIntent(R.id.button11, PendingIntent.getActivity(context, 0, Intent(context, MainActivity3::class.java), 0))
    // Instruct the widget manager to update the widget
    appWidgetManager.updateAppWidget(appWidgetId, views)
}

class B : RemoteViewsService() {
    override fun onGetViewFactory(p0: Intent): RemoteViewsFactory {
        return A(applicationContext, p0)
    }
}

class A(private val context: Context, val intent: Intent) : RemoteViewsService.RemoteViewsFactory {
    private val dataList = mutableListOf<Data>()

    override fun onCreate() {
        val db = MySQL(context).writableDatabase

        val cr = db.query("DATA", arrayOf("*"), null, null, null, null, null)
        with(cr) {
            while (cr.moveToNext()) {
                val code = getInt(getColumnIndexOrThrow("code"))
                val time = getString(getColumnIndexOrThrow("time"))
                val date = getString(getColumnIndexOrThrow("date"))
                val id = getString(getColumnIndexOrThrow(BaseColumns._ID))
                val timeA = getInt(getColumnIndexOrThrow("time_a"))

                dataList.add(Data(code, date, time, timeA, id))
            }
        }
    }

    override fun onDataSetChanged() {
        onCreate()
//        TODO("Not yet implemented")
    }

    override fun onDestroy() {
//        onCreate()
//        TODO("Not yet implemented")
    }

    override fun getCount(): Int = dataList.size

    @SuppressLint("RemoteViewLayout")
    override fun getViewAt(p0: Int): RemoteViews {
        val views = RemoteViews(context.packageName, R.layout.widget_list_item)
        views.setTextViewText(R.id.textView16, dataList[p0].date)
        val amOrPm = if (dataList[p0].timeA == R.id.radioButton2) "PM" else "AM"
        views.setTextViewText(R.id.textView17, "${dataList[p0].time} $amOrPm")
        views.setTextViewText(R.id.textView20, dataList[p0].code.toString())
        return views
    }

    override fun getLoadingView(): RemoteViews? = null

    override fun getViewTypeCount(): Int = dataList.size

    override fun getItemId(p0: Int): Long = p0.toLong()

    override fun hasStableIds(): Boolean = true

}