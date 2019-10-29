package com.leecode1988.roombasic;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;

@Dao  //Database access object(数据库操作的声明)
public interface WordDao {
    @Insert
    void insertWords(Word... words);
    /**
     * 带回结果
     */
    // @Insert
    // long insertWords(Word... words);
    @Update
    void updateWords(Word... word);

    @Delete
    void deleteWords(Word... words);

    @Query("DELETE FROM WORD")
    void deleteAllWords();

    /**
     * DESC
     * 降序排列，最新的记录会放在前面
     *
     * @return
     */
    @Query("SELECT * FROM WORD ORDER BY ID DESC")
    LiveData<List<Word>> getAllWordsLive();

}
