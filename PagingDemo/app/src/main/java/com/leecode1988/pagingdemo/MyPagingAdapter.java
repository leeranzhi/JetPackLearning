package com.leecode1988.pagingdemo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

public class MyPagingAdapter extends PagedListAdapter<Student, MyPagingAdapter.MyViewHolder> {

    protected MyPagingAdapter() {
        super(new DiffUtil.ItemCallback<Student>() {
            @Override public boolean areItemsTheSame(@NonNull Student oldItem, @NonNull Student newItem) {
                return oldItem.getId() == newItem.getId();
            }


            //比较两者内容是否相等
            @Override public boolean areContentsTheSame(@NonNull Student oldItem, @NonNull Student newItem) {
                return oldItem.getStudentNumber() == newItem.getStudentNumber();
            }
        });
    }


    @NonNull @Override public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.cell, parent, false);
        return new MyViewHolder(view);
    }


    @Override public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Student student = getItem(position);
        //可能会出现null
        if (student == null) {
            holder.textView.setText("loading");
        } else {
            holder.textView.setText(String.valueOf(student.getStudentNumber()));
        }
    }


    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
        }
    }
}
