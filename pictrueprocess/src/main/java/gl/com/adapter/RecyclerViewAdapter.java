package gl.com.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.List;

import gl.com.pictrueprocess.R;

/**
 * Created by mac on 15-11-2.
 * RecyclerViewAdapter
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private Context mContext;
    private List<String> mDatas;

    public interface OnItemClickListener{
        void onItemClick(View view,int position);
    }

    private OnItemClickListener mListener;

    public void setOnClickListener(OnItemClickListener listener){
        this.mListener = listener;
    }

    public RecyclerViewAdapter(Context context,List<String> datas){
        this.mContext = context;
        this.mDatas = datas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder holder = new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.recyclerview_item,parent,false));


        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.textView.setText(mDatas.get(position));
        if (mListener!=null){
            holder.textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    Log.e("tag",pos+"");
                    mListener.onItemClick(holder.textView,pos);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.pic_item);
        }
    }
}
