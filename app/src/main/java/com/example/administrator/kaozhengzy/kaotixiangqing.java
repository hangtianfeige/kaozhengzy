package com.example.administrator.kaozhengzy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import adapter.mylistadapter;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 作者：刘帅 on 2016/2/26 10:16
 * 邮箱：857279611@qq.com
 */
public class kaotixiangqing extends Activity {

    @Bind(R.id.title_kaoti)
    titlelayout titleKaoti;
    @Bind(R.id.list_kaoti)
    ListView listKaoti;
    private String year;
    private Intent intent;
    private String[] liebiao = {"http://www.baidu.com", "http://www.kaoshizl.cn", "http://www" +
            ".kaoshizl.cn",
            "http://pan.baidu.com/s/1pK4AkbD", "http://www.kaoshizl.cn",
            "http://www.kaoshizl.cn", "http://www.kaoshizl.cn"};
    private String[] kaotidesc = {"上半年模拟一", "上半年模拟一", "上半年模拟一", "上半年模拟一", "上半年模拟一", "上半年模拟一",
            "上半年模拟一"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kaotixiangqing);
        ButterKnife.bind(this);
        intent = getIntent();
        year = intent.getStringExtra("year");
        titleKaoti.txtTitle.setText(year + "考题");
        listKaoti.setAdapter(new mylistadapter(this, liebiao, kaotidesc, R.layout
                .kaotiliebiao_item));
        listKaoti.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(kaotixiangqing.this, WebView_activity.class);
                intent.putExtra("url", liebiao[position]);
                startActivity(intent);
            }
        });
    }
}
