package com.example.administrator.kaozhengzy;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/2/25.
 */
public class titlelayout extends RelativeLayout {
    @Bind(R.id.btn_back)
    Button btnBack;
    @Bind(R.id.txt_title)
    TextView txtTitle;

    public titlelayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.back_title, this);
        ButterKnife.bind(this);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.titlelayout);
        String ltitletext = ta.getString(R.styleable.titlelayout_titletext);
        txtTitle.setText(ltitletext);
        ta.recycle();
        btnBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity) getContext()).finish();
            }
        });
    }

}
