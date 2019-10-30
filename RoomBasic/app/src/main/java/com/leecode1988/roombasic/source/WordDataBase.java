package com.leecode1988.roombasic.source;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;
import com.leecode1988.roombasic.words.Word;

/**
 * singleton
 * 实例化AppDatabase对象时，
 * 应遵循单例设计模式
 *
 * @author Lee
 * @create 2019/10/29 17:33
 */
@Database(entities = { Word.class }, version = 5, exportSchema = false)
public abstract class WordDataBase extends RoomDatabase {
    private static WordDataBase INSTANCE;


    public static synchronized WordDataBase getWordDataBase(Context context) {
        if (INSTANCE == null) {
            synchronized (WordDataBase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), WordDataBase.class, "word_database")
                        //重新生成
                        // .fallbackToDestructiveMigration()
                        //迁移策略
                        .addMigrations(migration_4_5)
                        .build();
                }
            }
        }
        return INSTANCE;
    }


    /**
     * 新增表中字段属性
     */
    static final Migration migration_2_3 = new Migration(2, 3) {
        @Override public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE WORD ADD COLUMN bar_data INTEGER NOT NULL DEFAULT 1");
        }
    };

    /**
     * 新增表中字段属性
     */
    static final Migration migration_4_5 = new Migration(4, 5) {
        @Override public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE WORD ADD COLUMN chinese_invisible INTEGER NOT NULL DEFAULT 1");
        }
    };

    /**
     * 删除表中的字段属性
     */
    static final Migration migration_3_4 = new Migration(3, 4) {
        @Override public void migrate(@NonNull SupportSQLiteDatabase database) {
            //新建
            database.execSQL("CREATE TABLE word_temp (id INTEGER PRIMARY KEY NOT NULL, english_word TEXT," +
                " chinese_meaning Text)");
            //数据全部迁移插入
            database.execSQL("INSERT INTO word_temp (id ,english_word ,chinese_meaning )" +
                "SELECT id,english_word,chinese_word FROM word");
            //删除旧表
            database.execSQL("DROP TABLE word");
            //重命名为旧表的名称
            database.execSQL("ALTER TABLE WORD_TEMP RENAME TO word");
        }
    };


    public abstract WordDao getWordDao();
}
