package com.example.administrator.kaozhengzy;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.kaozhengzy.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import adapter.mylistadapter;
import adapter.mylisttuiguang;
import adapter.mypageadapter;
import butterknife.Bind;
import butterknife.ButterKnife;
import javabean.kaotiliebiao;
import javabean.tuiguang;
import utils.HttpCallbackListener;
import utils.HttpUtil;

public class MainActivity extends ActionBarActivity implements View.OnClickListener {
    @Bind(R.id.edt_shousuo)
    EditText edtShousuo;
    @Bind(R.id.btn_shousuo)
    Button btnShousuo;
    @Bind(R.id.ban_shouye)
    ViewPager banShouye;
    @Bind(R.id.msg)
    TextView msg;
    @Bind(R.id.point_group)
    LinearLayout pointGroup;
    @Bind(R.id.lay_shousuo)
    LinearLayout layShousuo;
    @Bind(R.id.zhanboxinxi)
    LinearLayout zhanboxinxi;
    @Bind(R.id.btn_liniankaoti)
    Button btnLiniankaoti;
    @Bind(R.id.btn_xiangguanzl)
    Button btnXiangguanzl;
    @Bind(R.id.btn_xiangguanxinxi)
    Button btnXiangguanxinxi;
    @Bind(R.id.btn_group)
    LinearLayout btnGroup;
    @Bind(R.id.list_tuisong)
    ListView listTuisong;
    // 图片资源ID
    private final int[] imageIds = {R.drawable.a, R.drawable.b, R.drawable.c,
            R.drawable.d, R.drawable.e};
    // 图片标题集合
    private final String[] imageDescriptions = {"巩俐不低俗，我就不能低俗",
            "扑树又回来啦！再唱经典老歌引万人大合唱", "揭秘北京电影如何升级", "乐视网TV版大派送", "热血屌丝的反杀"};
    private ArrayList<ImageView> imagelist;
    /**
     * 上一个页面的位置
     */
    protected int lastpointposition;
    private int[] logoResIds = {R.drawable.tianjindaxue, R.drawable.tianjingongydaxue, R.drawable
            .tianjinkejidaxue, R
            .drawable.tianjinshifandaxue, R.drawable.tianjinshangyedaxue};
    private String[] titlecontent = {"天津大学", "天津工业大学", "天津科技大学", "天津师范大学", "天津商业大学"};
    private String[] titletime = {"2016-2-24 11:17", "2016-2-24 11:17", "2016-2-24 11:17",
            "2016-2-24 11:17",
            "2016-2-24 11:17"};
    private List<tuiguang> list = new ArrayList<>();
    private tuiguang tuiguang1;

    /**
     * 判断是否执行自动滚动
     */
    private boolean isrunning = false;
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {

            // 让viewpager滑动到下一页
            banShouye.setCurrentItem(banShouye.getCurrentItem() + 1);
            if (isrunning) {
                handler.sendEmptyMessageDelayed(0, 4000);
            }

        }

        ;
    };
    private Intent intent;
    private mylisttuiguang adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
        SystemClock.sleep(500);
        msg.setText(imageDescriptions[0]);
        imagelist = new ArrayList<ImageView>();
        initViewPager();
        banShouye.setAdapter(new mypageadapter(this, imagelist));
        initViewPagerScroll();
        btnShousuo.setOnClickListener(this);
        btnLiniankaoti.setOnClickListener(this);
        btnXiangguanxinxi.setOnClickListener(this);
        btnXiangguanzl.setOnClickListener(this);
        adapter = new mylisttuiguang(this, list, R.layout.shouye_list_item);
        listTuisong.setAdapter(adapter);

    }

    public void init() {

        HttpUtil.sendHttpRequest("http://115.159.107.45/kaoshizy/tuiguang.php", new
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
                String content = jsonObject.getString("content");
                String time = jsonObject.getString("time");
                String url = jsonObject.getString("url");
                tuiguang1 = new tuiguang();
                tuiguang1.setContent(content);
                tuiguang1.setTime(time);
                tuiguang1.setUrl(url);
                list.add(tuiguang1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initViewPagerScroll() {
        banShouye.setCurrentItem(Integer.MAX_VALUE / 2
                - (Integer.MAX_VALUE / 2 % imagelist.size()));
        banShouye.setOnPageChangeListener(new OnPageChangeListener() {

            /**
             * 页面切换后调用 position 新的页面的位置
             */
            @Override
            public void onPageSelected(int position) {

                position = position % imagelist.size();
                // 设置文字描述
                msg.setText(imageDescriptions[position]);
                // 改变指示点的状态
                // 把当前点enable设为true将上一个点设为false
                pointGroup.getChildAt(position).setEnabled(true);
                pointGroup.getChildAt(lastpointposition).setEnabled(false);
                lastpointposition = position;

            }

            @Override
            /**
             * 页面正在滑动的时候回调
             */
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            /**
             * 当页面状态发生变化时回调
             */
            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        /**
         * 自动循环 1.定时器 Timer 2.开子线程,while(true) 3.Colockmanager 4.handle
         * 发送延时信息,实现循环
         */
        isrunning = true;
        handler.sendEmptyMessageDelayed(0, 4000);
    }


    private void initViewPager() {
        for (int i = 0; i < imageIds.length; i++) {
            // 初始化图片资源
            ImageView imageView = new ImageView(this);
            imageView.setBackgroundResource(imageIds[i]);
            imagelist.add(imageView);

            // 添加指示点
            ImageView point = new ImageView(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(10,
                    10);
            params.rightMargin = 20;

            point.setLayoutParams(params);
            point.setBackgroundResource(R.drawable.point_bg);
            if (i == 0) {
                point.setEnabled(true);
            } else {
                point.setEnabled(false);
            }
            pointGroup.addView(point);
        }
    }

    protected void onDestroy() {
        isrunning = false;
        super.onDestroy();

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_shousuo:
                Toast.makeText(this, "搜索按钮", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_liniankaoti:
                Toast.makeText(this, "正在加载中", Toast.LENGTH_SHORT).show();
                intent = new Intent(this, LiNianKaoTiActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_xiangguanxinxi:
                intent = new Intent(this, XiangGuanXinXi.class);
                startActivity(intent);
                break;
            case R.id.btn_xiangguanzl:
                intent = new Intent(this, XiangGuanZl.class);
                startActivity(intent);
                break;


        }
    }

}
