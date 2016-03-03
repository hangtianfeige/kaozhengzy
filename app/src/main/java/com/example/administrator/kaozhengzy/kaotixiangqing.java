package com.example.administrator.kaozhengzy;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import adapter.mykaotixiangqinglist;
import butterknife.Bind;
import butterknife.ButterKnife;
import utils.HttpCallbackListener;
import utils.HttpUtil;

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
    private mykaotixiangqinglist adapter;
    private List<javabean.kaotixiangqing> list = new ArrayList<>();
    private javabean.kaotixiangqing kaotixiangqing1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kaotixiangqing);
        ButterKnife.bind(this);

        intent = getIntent();
        year = intent.getStringExtra("year");
        titleKaoti.txtTitle.setText(year + "考题");
        init();
        SystemClock.sleep(500);
        adapter = new mykaotixiangqinglist(this, list, R.layout
                .kaotiliebiao_item);
        listKaoti.setAdapter(adapter);
        listKaoti.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(list.get(position).getUrl
                        ())));
            }
        });
    }

    public void init() {

        HttpUtil.sendHttpRequest("http://115.159.107.45/kaoshizy/kaoti.php?nianfen=" + year, new
                HttpCallbackListener() {
                    @Override
                    public void onFinish(String response) {
                        // 在这里根据返回内容执行具体的逻辑
                        parseJSONWithJSONObject(response);

                    }

                    @Override
                    public void onError(Exception e) {
                        // 在这里对异常情况进行处理
                        System.out.println("获取失败" + e);

                    }
                });
    }

    private void parseJSONWithJSONObject(String jsonData) {
        try {
            JSONArray jsonArray = new JSONArray(jsonData);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String name = jsonObject.getString("name");
                String url = jsonObject.getString("url");
                kaotixiangqing1 = new javabean.kaotixiangqing();
                kaotixiangqing1.setName(name);
                kaotixiangqing1.setUrl(url);
                list.add(kaotixiangqing1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        list.clear();
        adapter.notifyDataSetChanged();
    }
}
