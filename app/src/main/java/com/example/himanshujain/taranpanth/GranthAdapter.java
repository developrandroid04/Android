package com.example.himanshujain.taranpanth;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


public class GranthAdapter extends RecyclerView.Adapter<GranthAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private Context mContext;
    private GranthListener mListener;
     Boolean isSearch;
    List<String> data;


    public GranthAdapter(Context context, List<String> data, LayoutInflater inflater, GranthListener colorListener) {
        this.mContext = context;
        this.inflater = inflater;
        this.data = data;
        mListener = colorListener;
         this.isSearch = isSearch;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.layout_granth_name, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;


    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {


        holder.colorName.setText(data.get(position));


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
mListener.onGranthClick(data.get(position));
            }
        });

    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView colorName;


        public ViewHolder(View itemView) {
            super(itemView);

            colorName = (TextView) itemView.findViewById(R.id.tv_name);


        }
    }

    public interface GranthListener {
        public void onGranthClick(String name);
    }


}

