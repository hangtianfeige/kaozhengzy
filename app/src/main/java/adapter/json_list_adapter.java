package adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.kaozhengzy.R;

import java.util.List;

import javabean.shipin;

/**
 * 作者：刘帅 on 2016/2/27 15:15
 * 邮箱：857279611@qq.com
 */
public class json_list_adapter extends BaseAdapter {

    protected List<shipin> list;
    protected int layout;
    protected Context context;

    public json_list_adapter(Context context, List<shipin> list, int layout) {
        this.list = list;
        this.layout = layout;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
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

        holder.time.setText(list.get(position).getTime());
        holder.title.setText(list.get(position).getName());
        return convertView;
    }

    // 依据item的layout
    class ViewHolder {

        TextView title;
        TextView time;
    }
}
