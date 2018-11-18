package com.example.guice.viewmodelexample;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by Guice on 11/10/2018.
 */

public interface NoteDao {
    @Insert
    void insert(Note note);

    @Update
    void update(Note note);

    @Delete
    void delete(Note note);

    @Query("Delete from note_table")
    void deleteAllNotes();

    @Query("select * from note_table order by priority desc")
    LiveData<List<Note>> getAllNotes();
}
