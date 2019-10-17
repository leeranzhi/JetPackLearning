package com.leecode1988.viewmodeltest;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * author:LeeCode
 * create:2019/9/23 22:58
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private List<String> titleList;
    SparseArray<String> content = new SparseArray<>();

    public RecyclerAdapter(List<String> title) {
        this.titleList = title;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recycler, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindToHolder(content.get(position));
    }


    @Override
    public int getItemCount() {
        return titleList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        EditText editText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_name);
            editText = itemView.findViewById(R.id.edit_test);

            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }

                @Override
                public void afterTextChanged(Editable s) {
                    content.put(getAdapterPosition(), s.toString());
                }
            });
        }

        void bindToHolder(String content) {
            textView.setText(content);
            if (content == null) {
                editText.setHint("请输入" + textView.getText().toString());
                editText.setText("");
            } else {
                editText.setText(content);
            }
        }

    }
}
