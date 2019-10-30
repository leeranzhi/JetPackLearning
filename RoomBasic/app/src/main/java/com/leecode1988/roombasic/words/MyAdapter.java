package com.leecode1988.roombasic.words;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.leecode1988.roombasic.R;
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
    private WordViewModel wordViewModel;


    MyAdapter(boolean useCardView, WordViewModel wordViewModel) {
        this.useCardView = useCardView;
        this.wordViewModel = wordViewModel;
    }


    void setAllWordList(List<Word> allWordList) {
        this.allWordList = allWordList;
    }


    @NonNull @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView;
        if (useCardView) {
            itemView = layoutInflater.inflate(R.layout.cell_card_2, parent, false);
        } else {
            itemView = layoutInflater.inflate(R.layout.cell_normal_2, parent, false);
        }
        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        final Word word = allWordList.get(position);
        // word.setId(position);

        /**
         * 1.
         */
        // holder.bindDataToUI(word);

        holder.textViewNumber.setText(String.valueOf(position + 1));
        holder.textViewEnglish.setText(word.getWord());
        holder.textViewChinese.setText(word.getChineseMeaning());

        holder.aSwitchChineseInVisible.setOnCheckedChangeListener(null);

        if (word.isChineseInvisible()) {
            holder.textViewChinese.setVisibility(View.GONE);
            holder.aSwitchChineseInVisible.setChecked(true);
        } else {
            holder.textViewChinese.setVisibility(View.VISIBLE);
            holder.aSwitchChineseInVisible.setChecked(false);
        }

        /**
         * 此种写法会导致传入的是int类型的， setText(资源值) 会报错
         */
        // holder.textViewNumber.setText(position);
        /**
         * or 2.
         */
        // holder.textViewNumber.setText(String.valueOf(position + 1));
        // holder.textViewEnglish.setText(word.getWord());
        // holder.textViewChinese.setText(word.getChineseMeaning());

        holder.aSwitchChineseInVisible.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                if (checked) {
                    holder.textViewChinese.setVisibility(View.GONE);
                    word.setChineseInvisible(true);
                    wordViewModel.updateWords(word);
                } else {
                    holder.textViewChinese.setVisibility(View.VISIBLE);
                    word.setChineseInvisible(false);
                    wordViewModel.updateWords(word);
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return allWordList.size();
    }


    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textViewNumber,
            textViewEnglish,
            textViewChinese;

        Switch aSwitchChineseInVisible;


        MyViewHolder(@NonNull final View itemView) {
            super(itemView);
            textViewNumber = itemView.findViewById(R.id.tv_number);
            textViewEnglish = itemView.findViewById(R.id.tv_english);
            textViewChinese = itemView.findViewById(R.id.tv_chinese);

            aSwitchChineseInVisible = itemView.findViewById(R.id.chinese_invisible);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View view) {
                    Uri uri = Uri.parse("https://m.youdao.com/dict?le=eng&q=" + textViewEnglish.getText().toString().trim());
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(uri);
                    itemView.getContext().startActivity(intent);
                }
            });

        }


        void bindDataToUI(Word word) {
            textViewNumber.setText(String.valueOf(word.getId() + 1));
            textViewEnglish.setText(word.getWord());
            textViewChinese.setText(word.getChineseMeaning());

            aSwitchChineseInVisible.setOnCheckedChangeListener(null);

            if (word.isChineseInvisible()) {
                textViewChinese.setVisibility(View.GONE);
                aSwitchChineseInVisible.setChecked(true);
            } else {
                textViewChinese.setVisibility(View.VISIBLE);
                aSwitchChineseInVisible.setChecked(false);
            }
        }
    }
}
