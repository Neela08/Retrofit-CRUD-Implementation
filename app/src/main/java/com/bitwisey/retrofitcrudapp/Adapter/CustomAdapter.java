package com.bitwisey.retrofitcrudapp.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bitwisey.retrofitcrudapp.Model.RetroInfo;
import com.bitwisey.retrofitcrudapp.R;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {

    private List<RetroInfo> dataList;
    private Context context;

    public CustomAdapter(Context context, List<RetroInfo> dataList){
        this.context = context;
        this.dataList = dataList;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        public final View mView;

        TextView txtName;
        TextView txtNumber,txtId;

        CustomViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            txtName = mView.findViewById(R.id.nameView);
            txtNumber = mView.findViewById(R.id.numberView);
            txtId=      mView.findViewById(R.id.idView);
        }
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.single_row, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        holder.txtId.setText(String.valueOf(dataList.get(position).getId()));
        holder.txtName.setText(dataList.get(position).getName());
        holder.txtNumber.setText(dataList.get(position).getNumber());



    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
