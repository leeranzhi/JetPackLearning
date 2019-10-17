package com.leecode1988.viewmodeltest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class RecyclerActivity extends AppCompatActivity {
    private static final String TAG = "RecyclerActivity";
    List<String> titleList = new ArrayList<>();
    private RecyclerAdapter recyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        initData();
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        findViewById(R.id.bt_submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
            }
        });

        recyclerAdapter = new RecyclerAdapter(titleList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recyclerAdapter);
    }

    private void getData() {
//        String[] content = recyclerAdapter.getContent();
//        for (int i = 0; i < content.length; i++) {
//            Log.e(TAG, "getData: " + content[i]);
//        }
    }

    private void initData() {
        for (int i = 0; i < 200; i++) {
            titleList.add("" + i);
        }
    }


}
