package com.leecode1988.pagingdemo;

import android.os.AsyncTask;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    Button btAdd, btClear;
    StudentDao studentDao;
    StudentDataBase studentDataBase;
    LiveData<PagedList<Student>> allStudentLivePaged;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        btAdd = findViewById(R.id.bt_add);
        btClear = findViewById(R.id.bt_clear);
        final MyPagingAdapter myPagingAdapter = new MyPagingAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(myPagingAdapter);

        studentDataBase = StudentDataBase.getInstance(this);
        studentDao = studentDataBase.getStudentDao();

        //pageSize设置大一点，就会避免列表滑动时产生闪烁的情况。
        allStudentLivePaged = new LivePagedListBuilder<>(studentDao.getAllStudents(), 50)
            .build();
        allStudentLivePaged.observe(this, new Observer<PagedList<Student>>() {
            @Override public void onChanged(PagedList<Student> students) {
                myPagingAdapter.submitList(students);
                students.addWeakCallback(null, new PagedList.Callback() {
                    @Override public void onChanged(int position, int count) {

                    }


                    @Override public void onInserted(int position, int count) {

                    }


                    @Override public void onRemoved(int position, int count) {

                    }
                });
            }
        });
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                Student[] students = new Student[1000];
                for (int i = 0; i < 1000; i++) {
                    Student student = new Student();
                    student.setStudentNumber(i);
                    students[i] = student;
                }
                new InsertAsyTask(studentDao).execute(students);
            }
        });
        btClear.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                new ClearAsyTask(studentDao).execute();
            }
        });
    }


    static class InsertAsyTask extends AsyncTask<Student, Void, Void> {
        StudentDao studentDao;


        public InsertAsyTask(StudentDao studentDao) {
            this.studentDao = studentDao;
        }


        @Override protected Void doInBackground(Student... students) {
            studentDao.insertStudent(students);
            return null;
        }
    }


    static class ClearAsyTask extends AsyncTask<Void, Void, Void> {
        StudentDao studentDao;


        public ClearAsyTask(StudentDao studentDao) {
            this.studentDao = studentDao;
        }


        @Override protected Void doInBackground(Void... voids) {
            studentDao.deleteAllStudents();
            return null;
        }
    }
}
