package com.example.administrator.kaozhengzy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 作者：刘帅 on 2016/2/26 10:16
 * 邮箱：857279611@qq.com
 */
public class kaotixiangqing extends Activity {

    @Bind(R.id.title_kaoti)
    titlelayout titleKaoti;
    private String year;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kaotixiangqing);
        ButterKnife.bind(this);
        intent = getIntent();
        year = intent.getStringExtra("year");
        titleKaoti.txtTitle.setText(year + "考题");
    }
}
