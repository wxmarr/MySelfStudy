package gl.com.frescodemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by mac on 15-10-23.
 */
public class ListViewAdapter extends BaseAdapter {

    private List<String> mDatas;
    private Context mContext;
    public ListViewAdapter(Context context,List<String> datas){
        this.mContext=context;
        this.mDatas=datas;
    }
    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView==null){
            convertView= LayoutInflater.from(mContext).inflate(R.layout.listview_item,parent,false);
            viewHolder=new ViewHolder();
            viewHolder.textView= (TextView) convertView.findViewById(R.id.my_text);
            convertView.setTag(viewHolder);
        }
        viewHolder= (ViewHolder) convertView.getTag();
        viewHolder.textView.setText(mDatas.get(position));
        return convertView;
    }

    private class ViewHolder{
        TextView textView;
    }
}
