package gl.com.as;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mac on 15-10-9.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    private Context context;
    private List<String> mDatas;
    private List<Integer> mHeight;

    public RecyclerViewAdapter(Context context,List<String> mDatas){
        this.context=context;
        this.mDatas=mDatas;
        mHeight = new ArrayList<Integer>();
        for (int i =0;i<mDatas.size();i++){
            mHeight.add((int) (100+Math.random()*300));
        }
    }
    public interface OnItemClickListener {
        void onClick(int pos);
        void onLongClick(int pos);
    }

    private OnItemClickListener listener;

    @Override
    public int getItemViewType(int position) {
        if (position%2==0){
            return 0;
        }else{
            return 1;
        }

    }

    public void setOnclickListener(OnItemClickListener listener){
        this.listener=listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder holder=null;
        Log.e("tag","this is on onCreateViewHolder"+viewType);
        switch (viewType){
            case 0:
                holder = new ViewHolder(LayoutInflater.from(context).inflate(R.layout.rccyclerview_item,parent,false));
                break;
            default:
                holder = new ViewHolder(LayoutInflater.from(context).inflate(R.layout.rccyclerview_item2,parent,false));
                break;
        }
        return holder;
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        ViewGroup.LayoutParams lp = holder.textView.getLayoutParams();
        lp.height=mHeight.get(position);
        holder.textView.setLayoutParams(lp);
        holder.textView.setText(mDatas.get(position));
        if (listener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos=holder.getLayoutPosition();
                    listener.onClick(pos);
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int pos = holder.getLayoutPosition();
                    listener.onLongClick(pos);
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public void addData(int position){
        mDatas.add(position,"Insert One");
        notifyItemInserted(position);
    }

    public void removeData(int position){
        mDatas.remove(position);
        notifyItemRemoved(position);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView= (TextView) itemView.findViewById(R.id.id_text);
        }
    }
}
