package com.leecode1988.roombasic.source;

import android.content.Context;
import android.os.AsyncTask;
import androidx.lifecycle.LiveData;
import com.leecode1988.roombasic.wordsdemo.Word;
import java.util.List;

/**
 * 数据仓库类
 *
 * @author Lee
 * @create 2019/10/29 18:02
 */
class WordRepository {
    private WordDao wordDao;


     LiveData<List<Word>> getAllWordsLive() {
        return allWordsLive;
    }


    private LiveData<List<Word>> allWordsLive;


    public WordRepository(Context context) {
        WordDataBase wordDataBase = WordDataBase.getWordDataBase(context.getApplicationContext());
        wordDao = wordDataBase.getWordDao();
        allWordsLive = wordDao.getAllWordsLive();
    }


    void insertWords(Word... word) {
        new InsertAsyncTask(wordDao).execute(word);
    }


    void updateWords(Word... word) {
        new UpdateAsyncTask(wordDao).execute(word);
    }


    void deleteWords(Word... word) {
        new DeleteAsyncTask(wordDao).execute(word);
    }


    void deleteAllWords() {
        new DeleteAllAsyncTask(wordDao).execute();
    }


    static class InsertAsyncTask extends AsyncTask<Word, Void, Void> {
        private WordDao wordDao;


        InsertAsyncTask(WordDao wordDao) {
            this.wordDao = wordDao;
        }


        /**
         * 执行之前
         */
        @Override protected void onPreExecute() {
            super.onPreExecute();
        }


        /**
         * 进度获取
         *
         * @param values
         * @Tag some
         */
        @Override protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }


        /**
         * 后台任务
         *
         * @param words
         */
        @Override protected Void doInBackground(Word... words) {
            wordDao.insertWords(words);
            return null;
        }
    }


    static class DeleteAsyncTask extends AsyncTask<Word, Void, Void> {
        private WordDao wordDao;


        DeleteAsyncTask(WordDao wordDao) {
            this.wordDao = wordDao;
        }


        /**
         * 后台任务
         *
         * @param word
         */
        @Override protected Void doInBackground(Word... word) {
            wordDao.deleteWords(word);
            return null;
        }


        /**
         * 执行之前
         */
        @Override protected void onPreExecute() {
            super.onPreExecute();
        }


        /**
         * 进度获取
         *
         * @param values
         */
        @Override protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

    }


    static class UpdateAsyncTask extends AsyncTask<Word, Void, Void> {
        private WordDao wordDao;


        UpdateAsyncTask(WordDao wordDao) {
            this.wordDao = wordDao;
        }


        /**
         * 后台任务
         *
         * @param words
         */
        @Override protected Void doInBackground(Word... words) {
            wordDao.updateWords(words);
            return null;
        }


        /**
         * 执行之前
         */
        @Override protected void onPreExecute() {
            super.onPreExecute();
        }


        /**
         * 进度获取
         *
         * @param values
         */
        @Override protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

    }


    static class DeleteAllAsyncTask extends AsyncTask<Void, Void, Void> {
        private WordDao wordDao;


        DeleteAllAsyncTask(WordDao wordDao) {
            this.wordDao = wordDao;
        }


        /**
         * 后台任务
         *
         * @param voids
         */
        @Override protected Void doInBackground(Void... voids) {
            wordDao.deleteAllWords();
            return null;
        }


        /**
         * 执行之前
         */
        @Override protected void onPreExecute() {
            super.onPreExecute();
        }


        /**
         * 进度获取
         *
         * @param values
         */
        @Override protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

    }
}
