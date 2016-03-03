package adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.kaozhengzy.R;

import java.util.List;

import javabean.tuiguang;
import utils.ImageLoader;

/**
 * 作者：刘帅 on 2016/3/3 09:08
 * 邮箱：857279611@qq.com
 */
public class mylisttuiguang extends BaseAdapter {

    private Context context;
    private List<tuiguang> list;
    private int layout;

    public mylisttuiguang(Context context, List<tuiguang> list, int layout) {
        this.context = context;
        this.list = list;
        this.layout = layout;
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

    ImageLoader imageLoader = new ImageLoader(context);

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(context, layout, null);
            holder.time = (TextView) convertView.findViewById(R.id.list_item_time);
            holder.logo = (ImageView) convertView.findViewById(R.id.list_item_logo);
            holder.title = (TextView) convertView.findViewById(R.id.list_item_title);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        imageLoader.DisplayImage(list.get(position).getUrl(), holder.logo);
        holder.time.setText(list.get(position).getTime());
        holder.title.setText(list.get(position).getContent());
        return convertView;
    }

    // 依据item的layout
    class ViewHolder {

        ImageView logo;
        TextView title;
        TextView time;
    }
}
