package com.leecode1988.roombasic;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.leecode1988.roombasic.words.Word;
import java.util.ArrayList;
import java.util.List;

/**
 * 列表适配器
 *
 * @author Lee
 * @create 2019/10/30 11:18
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private List<Word> allWordList = new ArrayList<>();
    private boolean useCardView;


    public MyAdapter(boolean useCardView) {
        this.useCardView = useCardView;
    }


    public void setAllWordList(List<Word> allWordList) {
        this.allWordList = allWordList;
    }


    @NonNull @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView;
        if (useCardView) {
            itemView = layoutInflater.inflate(R.layout.cell_card, parent, false);
        } else {
            itemView = layoutInflater.inflate(R.layout.cell_normal, parent, false);
        }
        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        final Word word = allWordList.get(position);
        word.setId(position);
        /**
         * 1.
         */
        holder.bindDataToUI(word);
        /**
         * 此种写法会导致传入的是int类型的， setText(资源值)
         */
        // holder.textViewNumber.setText(position);
        /**
         * or 2.
         */
        // holder.textViewNumber.setText(String.valueOf(position + 1));
        // holder.textViewEnglish.setText(word.getWord());
        // holder.textViewChinese.setText(word.getChineseMeaning());
    }


    @Override
    public int getItemCount() {
        return allWordList.size();
    }


    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textViewNumber,
            textViewEnglish,
            textViewChinese;


        public MyViewHolder(@NonNull final View itemView) {
            super(itemView);
            textViewNumber = itemView.findViewById(R.id.tv_number);
            textViewEnglish = itemView.findViewById(R.id.tv_english);
            textViewChinese = itemView.findViewById(R.id.tv_chinese);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View view) {
                    Uri uri = Uri.parse("https://m.youdao.com/dict?le=eng&q=" + textViewEnglish.getText().toString().trim());
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(uri);
                    itemView.getContext().startActivity(intent);
                }
            });
        }


        public void bindDataToUI(Word word) {
            textViewNumber.setText(String.valueOf(word.getId()));
            textViewEnglish.setText(word.getWord());
            textViewChinese.setText(word.getChineseMeaning());
        }
    }
}
