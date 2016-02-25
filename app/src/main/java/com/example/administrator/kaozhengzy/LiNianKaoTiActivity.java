package com.example.administrator.kaozhengzy;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import adapter.mygradviewadapter;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/2/25.
 */
public class LiNianKaoTiActivity extends Activity {
    @Bind(R.id.grdv_kaoti)
    GridView grdvKaoti;
    private String[] iconName = {"2010", "2011", "2012", "2013", "2014", "2015"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.liniankaoti);
        ButterKnife.bind(this);
        grdvKaoti.setAdapter(new mygradviewadapter(this, iconName, R.layout
                .mygradview_kaoti_item));
        grdvKaoti.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(LiNianKaoTiActivity.this, position + "", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
