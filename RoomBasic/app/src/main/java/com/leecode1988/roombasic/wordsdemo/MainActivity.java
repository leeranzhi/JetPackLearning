package com.leecode1988.roombasic.wordsdemo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.leecode1988.roombasic.R;
import java.util.List;

/**
 * Room 数据库 CRUD
 *
 * @author Lee
 * @create 2019/10/29 16:02
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button buttonInsert;
    Button buttonUpdate;
    Button buttonDelete;
    Button buttonQuery;
    TextView textView;

    private WordViewModel wordViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        wordViewModel = ViewModelProviders.of(this).get(WordViewModel.class);

        wordViewModel.getAllWordsLive().observe(this, new Observer<List<Word>>() {
            @Override public void onChanged(List<Word> words) {
                StringBuilder text = new StringBuilder();
                for (int i = 0; i < words.size(); i++) {
                    Word word = words.get(i);
                    text.append(word.getId()).append(":").append(word.getWord()).append("=").append(word.getChineseMeaning()).append("\n");
                }
                textView.setText(text);
            }
        });
    }


    private void initView() {
        buttonInsert = findViewById(R.id.button_insert);
        buttonUpdate = findViewById(R.id.button_update);
        buttonDelete = findViewById(R.id.button_delete);
        buttonQuery = findViewById(R.id.button_query);
        textView = findViewById(R.id.textView);

        buttonInsert.setOnClickListener(this);
        buttonUpdate.setOnClickListener(this);
        buttonDelete.setOnClickListener(this);
        buttonQuery.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_insert:
                Word word1 = new Word("hello", "你好");
                Word word2 = new Word("world", "世界");
                wordViewModel.insertWords(word1, word2);
                break;
            case R.id.button_update:
                Word word = new Word("Hi", "你好啊");
                word.setId(136);
                wordViewModel.updateWords(word);
                break;
            case R.id.button_delete:
                wordViewModel.deleteAllWords();
                break;
            case R.id.button_query:
                break;
        }
    }

}

