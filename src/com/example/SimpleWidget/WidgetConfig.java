package com.example.SimpleWidget;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RemoteViews;

public class WidgetConfig extends Activity implements OnClickListener
{
    EditText info;
    AppWidgetManager awm;
    Context c;
    int awID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.widgetconfig);
        Button b = (Button)findViewById(R.id.bwidgetconfing);
        b.setOnClickListener(this);
        c = WidgetConfig.this;
        info = (EditText)findViewById(R.id.etwidgetconfing);

        Intent i = getIntent();
        Bundle extras = i.getExtras();

        if (extras != null) {
            awID = extras.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID,
                    AppWidgetManager.INVALID_APPWIDGET_ID
            );
        }
        else {
            finish();
        }
         awm = AppWidgetManager.getInstance(c);
    }



      public void onClick(View v)
      {
          String e = info.getText().toString();

          RemoteViews views = new RemoteViews(c.getPackageName(),R.layout.widget);
          views.setTextViewText(R.id.tvConfigInput,e);

          awm.updateAppWidget(awID,views);

          Intent result = new Intent();
          result.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,awID);
          setResult(RESULT_OK,result);
          finish();
      }
}
