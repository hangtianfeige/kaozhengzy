package com.example.administrator.kaozhengzy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import adapter.mylistadapter;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 作者：刘帅 on 2016/2/27 10:49
 * 邮箱：857279611@qq.com
 */
public class XiangGuanXinXi extends Activity {
    @Bind(R.id.list_xiangguanxinxi)
    ListView listXiangguanxinxi;
    private int[] logoResIds = {R.drawable.tianjindaxue, R.drawable.tianjingongydaxue, R.drawable
            .tianjinkejidaxue, R
            .drawable.tianjinshifandaxue, R.drawable.tianjinshangyedaxue};
    private String[] titlecontent = {"天津大学", "天津工业大学", "天津科技大学", "天津师范大学", "天津商业大学"};
    private String[] titletime = {"2016-2-24 11:17", "2016-2-24 11:17", "2016-2-24 11:17",
            "2016-2-24 11:17",
            "2016-2-24 11:17"};
    private String[] liebiao = {"http://pan.baidu.com/s/1c1r8V9e", "http://www.kaoshizl.cn",
            "http://www" +
                    ".kaoshizl.cn",
            "http://pan.baidu.com/s/1pK4AkbD", "http://www.kaoshizl.cn",
            "http://www.kaoshizl.cn", "http://www.kaoshizl.cn"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xiangguanxinxi);
        ButterKnife.bind(this);
        listXiangguanxinxi.setAdapter(new mylistadapter(this, logoResIds, titlecontent, titletime, R
                .layout.shouye_list_item));
        listXiangguanxinxi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(XiangGuanXinXi.this, WebView_activity.class);
                intent.putExtra("url", liebiao[position]);
                startActivity(intent);
            }
        });
    }
}
