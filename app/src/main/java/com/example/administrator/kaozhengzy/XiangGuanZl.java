package com.example.administrator.kaozhengzy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.LogRecord;

import adapter.json_list_adapter;
import adapter.mylistadapter;
import butterknife.Bind;
import butterknife.ButterKnife;
import javabean.shipin;
import utils.DateUtil;
import utils.HttpCallbackListener;
import utils.HttpUtil;

/**
 * 作者：刘帅 on 2016/2/27 09:49
 * 邮箱：857279611@qq.com
 */
public class XiangGuanZl extends Activity implements XListView.IXListViewListener {

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                Toast.makeText(XiangGuanZl.this, "没有历史", Toast.LENGTH_SHORT).show();
            }
        }
    };
    @Bind(R.id.list_xiangguanzl_ppt)
    ListView listXiangguanzlPpt;
    @Bind(R.id.list_xiangguanzl_vido)
    XListView listXiangguanzlVido;
    private String[] liebiao = {"http://pan.baidu.com/s/1c1r8V9e", "http://www.kaoshizl.cn",
            "http://www" +
                    ".kaoshizl.cn",
            "http://pan.baidu.com/s/1pK4AkbD", "http://www.kaoshizl.cn",
            "http://www.kaoshizl.cn", "http://www.kaoshizl.cn"};

    private int[] logoResIds = {R.drawable.tianjindaxue, R.drawable.tianjingongydaxue, R.drawable
            .tianjinkejidaxue, R
            .drawable.tianjinshifandaxue, R.drawable.tianjinshangyedaxue};
    private String[] titlecontent = {"天津大学", "天津工业大学", "天津科技大学", "天津师范大学", "天津商业大学"};
    private String[] titletime = {"2016-2-24 11:17", "2016-2-24 11:17", "2016-2-24 11:17",
            "2016-2-24 11:17",
            "2016-2-24 11:17"};
    private Intent intent;
    private json_list_adapter adapter;
    private int count = 1;
    private int oldsize;
    private SimpleDateFormat sdf = DateUtil.getSimpleDateFormat();
    private List<shipin> list = new ArrayList<shipin>();
    private shipin shipinbean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xiangguanzl);
        ButterKnife.bind(this);
        init();
        SystemClock.sleep(100);
        listXiangguanzlVido.setPullLoadEnable(true);// 开启加载更多
        listXiangguanzlVido.setPullRefreshEnable(true);// 开启下拉刷新

        listXiangguanzlPpt.setAdapter(new mylistadapter(this, logoResIds, titlecontent, titletime, R
                .layout.shouye_list_item));
        adapter = new json_list_adapter(this, list, R.layout.xiangguanzl_shipin_item);
        listXiangguanzlVido.setAdapter(adapter);
        listXiangguanzlVido.setXListViewListener(this);
        listXiangguanzlVido.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                intent = new Intent(XiangGuanZl.this, WebView_activity.class);
                intent.putExtra("url", list.get(position - 1).getUrl());
                startActivity(intent);
            }
        });
    }


    public void init() {

        HttpUtil.sendHttpRequest("http://115.159.107.45/kaoshizy/shipin.php", new
                HttpCallbackListener() {
                    @Override
                    public void onFinish(String response) {
                        // 在这里根据返回内容执行具体的逻辑
                        parseJSONWithJSONObject(response);
                    }

                    @Override
                    public void onError(Exception e) {
                        // 在这里对异常情况进行处理
                        System.out.println("获取失败");

                    }
                });
    }

    @Override
    public void onRefresh() {
        list.clear();
        init();
        listXiangguanzlVido.setRefreshTime(sdf.format(new Date()));
        adapter.notifyDataSetChanged();
        listXiangguanzlVido.stopRefresh();// 让下拉刷新的圈圈消失
        count = 1;
    }

    public void initmore() {
        HttpUtil.sendHttpRequest("http://115.159.107.45/kaoshizy/shipinmore.php?count=" + count, new
                HttpCallbackListener() {
                    @Override
                    public void onFinish(String response) {
                        if (response.contains("jiazaiwanbi")) {
                            Message message = new Message();
                            message.what = 1;
                            handler.sendMessage(message); //  将Message 对象发送出去
                        } else {
                            // 在这里根据返回内容执行具体的逻辑
                            parseJSONWithJSONObject(response);
                        }

                    }

                    @Override
                    public void onError(Exception e) {
                        // 在这里对异常情况进行处理
                        System.out.println("获取失败");

                    }
                });
    }

    @Override
    public void onLoadMore() {

        oldsize = list.size();
        count++;
        initmore();//再次从服务器获取数据（需要替换成自己的代码）
        SystemClock.sleep(500);
        adapter.notifyDataSetChanged();
        listXiangguanzlVido.stopLoadMore();// 让加载更多的圈圈消失
        listXiangguanzlVido.setSelection(oldsize);
    }

    private void parseJSONWithJSONObject(String jsonData) {
        try {
            JSONArray jsonArray = new JSONArray(jsonData);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String name = jsonObject.getString("name");
                String time = jsonObject.getString("time");
                String url = jsonObject.getString("url");
                shipinbean = new shipin();
                shipinbean.setName(name);
                shipinbean.setTime(time);
                shipinbean.setUrl(url);
                list.add(shipinbean);
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
