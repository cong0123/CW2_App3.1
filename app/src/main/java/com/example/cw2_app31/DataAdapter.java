package com.example.cw2_app31;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    private List<DataModel> dataList;

    public DataAdapter(List<DataModel> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_data, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DataModel dataModel = dataList.get(position);

        if (dataModel != null) {
            holder.nameTextView.setText("Name: " + dataModel.getName());
            holder.dobTextView.setText("DOB: " + dataModel.getDob());
            holder.emailTextView.setText("Email: " + dataModel.getEmail());

            // Set hình ảnh
            // Đảm bảo rằng hình ảnh không null trước khi thực hiện
            if (dataModel.getImageResourceId() != 0) {
                holder.imageView.setImageResource(dataModel.getImageResourceId());
            } else {
                // Nếu không có hình ảnh, có thể hiển thị một hình mặc định hoặc ẩn ImageView
                holder.imageView.setImageResource(R.drawable.no_image);
                // holder.imageView.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTextView;
        public TextView dobTextView;
        public TextView emailTextView;
        public ImageView imageView;

        public ViewHolder(View view) {
            super(view);
            nameTextView = view.findViewById(R.id.textViewName);
            dobTextView = view.findViewById(R.id.textViewDob);
            emailTextView = view.findViewById(R.id.textViewEmail);
            imageView = view.findViewById(R.id.imageView);
        }
    }
}

