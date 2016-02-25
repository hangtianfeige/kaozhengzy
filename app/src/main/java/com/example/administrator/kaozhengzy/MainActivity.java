package com.example.administrator.kaozhengzy;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.kaozhengzy.R;

import java.util.ArrayList;

import adapter.mylistadapter;
import adapter.mypageadapter;
import butterknife.Bind;
import butterknife.ButterKnife;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        msg.setText(imageDescriptions[0]);
        imagelist = new ArrayList<ImageView>();
        initViewPager();
        banShouye.setAdapter(new mypageadapter(this, imagelist));
        initViewPagerScroll();
        btnShousuo.setOnClickListener(this);
        btnLiniankaoti.setOnClickListener(this);
        btnXiangguanxinxi.setOnClickListener(this);
        btnXiangguanzl.setOnClickListener(this);
        listTuisong.setAdapter(new mylistadapter(this, logoResIds, titlecontent, titletime, R.layout.shouye_list_item));

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
                intent = new Intent(this, LiNianKaoTiActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_xiangguanxinxi:
                Toast.makeText(this, "相关信息", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_xiangguanzl:
                Toast.makeText(this, "相关资料", Toast.LENGTH_SHORT).show();
                break;


        }
    }
}
