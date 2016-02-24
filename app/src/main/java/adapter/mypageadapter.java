package adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/2/24.
 */
public class mypageadapter extends PagerAdapter {
    private ArrayList<ImageView> imagelist;
    private Context context;

    public mypageadapter(Context context, ArrayList<ImageView> imagelist) {
        this.imagelist = imagelist;
        this.context = context;
    }

    @Override
    /**
     * 获得页面的总数
     */
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    /**
     * 获得相应位置上的item
     * container view的容器,其实就是viewpage自身
     * position  相应的位置
     */
    public Object instantiateItem(ViewGroup container, int position) {
        // 给container添加内容
        container.addView(imagelist.get(position % imagelist.size()));

        return imagelist.get(position % imagelist.size());
    }

    @Override
    /**
     * 判断view和object的对应关系
     */
    public boolean isViewFromObject(View view, Object object) {

        if (view == object) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * 销毁对应位置上的object
     */
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        // super.destroyItem(container, position, object);
        container.removeView((View) object);
        object = null;
    }

}