package com.example.administrator.kaozhengzy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import adapter.mygradviewadapter;
import butterknife.Bind;
import butterknife.ButterKnife;
import javabean.kaoti;
import javabean.kaotiliebiao;
import javabean.shipin;
import utils.HttpCallbackListener;
import utils.HttpUtil;

/**
 * Created by Administrator on 2016/2/25.
 */
public class LiNianKaoTiActivity extends Activity {
    @Bind(R.id.grdv_kaoti)
    GridView grdvKaoti;


    private List<kaotiliebiao> list = new ArrayList<>();
    private kaotiliebiao kaotiliebiaobean;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.liniankaoti);
        ButterKnife.bind(this);
        init();
        grdvKaoti.setAdapter(new mygradviewadapter(this, list, R.layout
                .mygradview_kaoti_item));
        grdvKaoti.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(LiNianKaoTiActivity.this, kaotixiangqing.class);
                intent.putExtra("year", list.get(position).getNianfen());
                startActivity(intent);
            }
        });
    }

    public void init() {

        HttpUtil.sendHttpRequest("http://115.159.107.45/kaoshizy/kaotiliebiao.php", new
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
                String nianfen = jsonObject.getString("nianfen");
                kaotiliebiaobean = new kaotiliebiao();
                kaotiliebiaobean.setNianfen(nianfen);
                list.add(kaotiliebiaobean);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
