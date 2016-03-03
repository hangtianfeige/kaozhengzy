package adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.kaozhengzy.R;

import java.util.List;

import javabean.kaotixiangqing;

/**
 * 作者：刘帅 on 2016/3/3 11:25
 * 邮箱：857279611@qq.com
 */
public class mykaotixiangqinglist extends BaseAdapter {
    private List<kaotixiangqing> list;
    private Context context;
    private int layout;

    public mykaotixiangqinglist(Context context, List<javabean.kaotixiangqing> list, int
            layout) {
        this.context = context;
        this.layout = layout;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(context, layout, null);
            holder.time = (TextView) convertView.findViewById(R.id.list_item_time);
            holder.title = (TextView) convertView.findViewById(R.id.list_item_title);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.time.setText(list.get(position).getName());
        holder.title.setText(list.get(position).getUrl());
        return convertView;
    }

    // 依据item的layout
    class ViewHolder {

        TextView title;
        TextView time;
    }
}
