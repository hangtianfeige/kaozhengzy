package adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.kaozhengzy.R;
import com.example.administrator.kaozhengzy.XiangGuanZl;

/**
 * Created by Administrator on 2016/2/24.
 */
public class mylistadapter extends mybaseadapter {
    private String biaozhi;

    public mylistadapter(Context context, int[] logoResIds, String[] titlecontent, String[]
            titletime, int layout) {
        super(context, logoResIds, titlecontent, titletime, layout);
        biaozhi = "all";
    }

    public mylistadapter(Context context, int[] logoResIds, String[] titlecontent, int layout) {
        super(context, logoResIds, titlecontent, layout);
        biaozhi = "no time";
    }

    public mylistadapter(Context context, String[] titlecontent, String[] titletime, int layout) {
        super(context, titlecontent, titletime, layout);
        biaozhi = "no picture";
    }


    public mylistadapter(Context context, String[] titlecontent, int layout) {
        super(context, titlecontent, layout);
        biaozhi = "only_content";

    }

    public mylistadapter(Context context, String[] logoResIds, String[] titlecontent, String[]
            titletime, int layout) {
        super(context, logoResIds, titlecontent, titletime, layout);
        biaozhi = "3-String";
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(context, layout, null);
            if (biaozhi == "all") {
                holder.time = (TextView) convertView.findViewById(R.id.list_item_time);
                holder.logo = (ImageView) convertView.findViewById(R.id.list_item_logo);
            } else if (biaozhi == "no time") {
                holder.logo = (ImageView) convertView.findViewById(R.id.list_item_logo);
            } else if (biaozhi == "no picture") {
                holder.time = (TextView) convertView.findViewById(R.id.list_item_time);
            } else if (biaozhi == "3-String") {
                holder.titlezhen = (TextView) convertView.findViewById(R.id.list_item_titlezhen);
                holder.time = (TextView) convertView.findViewById(R.id.list_item_time);
            }
            holder.title = (TextView) convertView.findViewById(R.id.list_item_title);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if (biaozhi == "all") {
            holder.logo.setImageResource(logoResIds[position]);
            holder.time.setText(titletime[position]);
        } else if (biaozhi == "no time") {
            holder.logo.setImageResource(logoResIds[position]);
        } else if (biaozhi == "no picture") {
            holder.time.setText(titletime[position]);
        }
        holder.title.setText(titlecontent[position]);
        return convertView;
    }

    // 依据item的layout
    class ViewHolder {

        TextView titlezhen;
        ImageView logo;
        TextView title;
        TextView time;
    }
}
