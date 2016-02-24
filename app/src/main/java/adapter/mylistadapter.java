package adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.kaozhengzy.R;

/**
 * Created by Administrator on 2016/2/24.
 */
public class mylistadapter extends BaseAdapter {
    private int[] logoResIds;
    private String[] titlecontent;
    private String[] titletime;
    private Context context;

    public mylistadapter(Context context, int[] logoResIds, String[] titlecontent, String[] titletime) {
        this.context = context;
        this.logoResIds = logoResIds;
        this.titlecontent = titlecontent;
        this.titletime = titletime;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(context, R.layout.shouye_list_item, null);
            holder.logo = (ImageView) convertView.findViewById(R.id.list_item_logo);
            holder.title = (TextView) convertView.findViewById(R.id.list_item_title);
            holder.time = (TextView) convertView.findViewById(R.id.list_item_time);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.logo.setImageResource(logoResIds[position]);
        holder.title.setText(titlecontent[position]);
        holder.time.setText(titletime[position]);
        return convertView;
    }

    // 依据item的layout
    class ViewHolder {
        ImageView logo;
        TextView title;
        TextView time;
    }
}
