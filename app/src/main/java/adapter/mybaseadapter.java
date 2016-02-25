package adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * Created by Administrator on 2016/2/25.
 */
public abstract class mybaseadapter extends BaseAdapter {
    protected int[] logoResIds;
    protected String[] titletime;
    protected String[] titlecontent;
    protected Context context;
    protected int layout;

    public mybaseadapter(Context context, int[] logoResIds, String[] titlecontent, String[] titletime, int layout) {
        this.context = context;
        this.logoResIds = logoResIds;
        this.titlecontent = titlecontent;
        this.titletime = titletime;
        this.layout = layout;
    }

    public mybaseadapter(Context context, int[] logoResIds, String[] titlecontent, int layout) {
        this.context = context;
        this.logoResIds = logoResIds;
        this.titlecontent = titlecontent;
        this.layout = layout;
    }

    public mybaseadapter(Context context, String[] titlecontent, int layout) {
        this.context = context;
        this.titlecontent = titlecontent;
        this.layout = layout;
    }


    @Override
    public int getCount() {
        return titlecontent.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public abstract View getView(int position, View convertView, ViewGroup parent);


}
