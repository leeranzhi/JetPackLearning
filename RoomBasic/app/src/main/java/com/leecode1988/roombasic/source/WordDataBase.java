package com.leecode1988.roombasic.source;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.leecode1988.roombasic.words.Word;

/**
 * singleton
 * 实例化AppDatabase对象时，
 * 应遵循单例设计模式
 *
 * @author Lee
 * @create 2019/10/29 17:33
 */
@Database(entities = { Word.class }, version = 1, exportSchema = false)
public abstract class WordDataBase extends RoomDatabase {
    private static WordDataBase INSTANCE;


    public static WordDataBase getWordDataBase(Context context) {
        if (INSTANCE == null) {
            synchronized (WordDataBase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), WordDataBase.class, "word_database")
                        .build();
                }
            }
        }
        return INSTANCE;
    }


    public abstract WordDao getWordDao();
}
