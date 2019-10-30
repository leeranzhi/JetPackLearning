package com.leecode1988.roombasic.words;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.leecode1988.roombasic.source.WordRepository;
import java.util.List;

public class WordViewModel extends AndroidViewModel {

    private final WordRepository wordRepository;


    public WordViewModel(@NonNull Application application) {
        super(application);
        wordRepository = WordRepository.getInstance(application);
    }


     LiveData<List<Word>> getAllWordsLive() {
        return wordRepository.getAllWordsLive();
    }


    void insertWords(Word... word) {
        wordRepository.insertWords(word);
    }


    void updateWords(Word... word) {
        wordRepository.updateWords(word);
    }


    void deleteWords(Word... word) {
        wordRepository.deleteWords(word);
    }


    void deleteAllWords() {
        wordRepository.deleteAllWords();
    }

}
