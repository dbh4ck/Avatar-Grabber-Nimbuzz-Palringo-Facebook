package com.blogspot.dbh4ck.avatargrabberdb.Dialogs;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;

import com.blogspot.dbh4ck.avatargrabberdb.R;

/**
 * Created by DB on 10-12-2016.
 */

public class dbDialog extends Dialog implements View.OnClickListener {
    public dbDialog(Context context) {
        super(context);
    }

    public dbDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    protected dbDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    protected void onStart() {
        super.onStart();
        setContentView(R.layout.dbinfo_layout);
        getWindow().setFlags(4, 4);
        setTitle("About");

        Button ok = (Button) findViewById(R.id.ok);
        ok.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        this.dismiss();
    }
}
