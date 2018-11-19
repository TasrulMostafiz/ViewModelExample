package com.example.guice.viewmodelexample;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

/**
 * Created by Guice on 11/17/2018.
 */

public class NoteViewMOdel extends AndroidViewModel {

    private NoteRepository repository;
    private LiveData<List<Note>> allNotes;

    public NoteViewMOdel(@NonNull Application application) {
        super(application);

        repository=new NoteRepository(application);
        allNotes=repository.getAllNotes();
    }

    public void insert(Note note)
    {
        repository.insert(note);
    }

    public void update(Note note)
    {
        repository.update(note);
    }

    public void delete(Note note)
    {
        repository.delete(note);
    }

    public void deleteAllNotes() {
        repository.deleteAllNotes();
    }

    public LiveData<List<Note>> getAllNotes()
    {
        return allNotes;
    }
}
