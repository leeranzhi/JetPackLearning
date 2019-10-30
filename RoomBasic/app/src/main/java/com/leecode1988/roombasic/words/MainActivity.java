package com.leecode1988.roombasic.words;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.leecode1988.roombasic.MyAdapter;
import com.leecode1988.roombasic.R;
import java.util.List;

/**
 * Room 数据库 CRUD
 *
 * @author Lee
 * @create 2019/10/29 16:02
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";
    private Button buttonInsert;
    private Button buttonDelete;
    private Switch aSwitch;
    private RecyclerView recyclerView;

    private MyAdapter myAdapterNormal, myAdapterCardView;

    private WordViewModel wordViewModel;
    private final static String[] english;
    private final static String[] chinese;


    static {
        english = new String[] {
            "Hello",
            "World",
            "Android",
            "Google",
            "Studio",
            "Project",
            "Database",
            "Recycler",
            "View",
            "String",
            "Value",
            "Integer"
        };
        chinese = new String[] {
            "你好",
            "世界",
            "安卓系统",
            "谷歌公司",
            "工作室",
            "项目",
            "数据库",
            "回收站",
            "视图",
            "字符串",
            "价值",
            "整数类型"
        };
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        wordViewModel = ViewModelProviders.of(this).get(WordViewModel.class);

        wordViewModel.getAllWordsLive().observe(this, new Observer<List<Word>>() {
            @Override public void onChanged(List<Word> words) {
                myAdapterNormal.setAllWordList(words);
                myAdapterCardView.setAllWordList(words);
                myAdapterNormal.notifyDataSetChanged();
                myAdapterCardView.notifyDataSetChanged();
            }
        });
    }


    private void initView() {
        buttonInsert = findViewById(R.id.button_insert);
        buttonDelete = findViewById(R.id.button_delete);
        // textView = findViewById(R.id.textView);
        recyclerView = findViewById(R.id.recyclerView);
        aSwitch = findViewById(R.id.switch_card);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        myAdapterNormal = new MyAdapter(false);
        myAdapterCardView = new MyAdapter(true);

        recyclerView.setAdapter(myAdapterNormal);

        buttonInsert.setOnClickListener(this);
        buttonDelete.setOnClickListener(this);

        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    recyclerView.setAdapter(myAdapterCardView);
                } else {
                    recyclerView.setAdapter(myAdapterNormal);
                }
            }
        });
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_insert:

                for (int i = 0; i < english.length; i++) {
                    Word word = new Word(english[i], chinese[i]);
                    wordViewModel.insertWords(word);
                }

                break;

            case R.id.button_delete:
                wordViewModel.deleteAllWords();
                break;

        }
    }

}

