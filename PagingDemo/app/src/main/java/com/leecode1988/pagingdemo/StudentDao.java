package com.leecode1988.pagingdemo;

import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface StudentDao {
    @Insert
    void insertStudent(Student... students);

    @Query("Delete From student_table")
    void deleteAllStudents();
    @Query("Select * from student_table order by id desc")
        // LiveData<List<Student>> getAllStudents();
    DataSource.Factory<Integer, Student> getAllStudents();
}
