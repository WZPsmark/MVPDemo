package com.sk.mvpdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sk.mvpdemo.R;

import java.util.List;

/**
 * Created by smark on 2020/5/7.
 * 邮箱：smarkwzp@163.com
 */
public class RVAdapter extends RecyclerView.Adapter<RVAdapter.MyHodel> {

    private List<String> data;

    private Context mContext;

    public RVAdapter(List<String> data, Context context) {
        this.data = data;
        mContext = context;
    }

    @NonNull
    @Override
    public MyHodel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item, parent, false);
        return new MyHodel(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHodel holder, int position) {
        String model = data.get(position);
        holder.tv_text.setText(model);
    }

    @Override
    public int getItemCount() {
        return data.size() != 0 ? data.size() : 0;
    }

    class MyHodel extends RecyclerView.ViewHolder {
        TextView tv_text;

        public MyHodel(@NonNull View itemView) {
            super(itemView);
            tv_text = itemView.findViewById(R.id.tv_text);
        }
    }
}
