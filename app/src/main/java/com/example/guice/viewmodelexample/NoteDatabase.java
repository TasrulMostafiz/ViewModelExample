package com.example.guice.viewmodelexample;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.util.Log;

import java.io.File;

/**
 * Created by Guice on 11/11/2018.
 */

@Database(entities = {Note.class},version = 1)
public abstract class NoteDatabase extends RoomDatabase {
    private static NoteDatabase instance;

    public abstract NoteDao noteDao();

    public static synchronized NoteDatabase getInstance(Context context)
    {

        if(instance == null)
        {
            File dir = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "ViewModelExample_DB");
            if(!dir.exists()) {
                dir.mkdirs();
            }
            instance= Room.databaseBuilder(context.getApplicationContext(),NoteDatabase.class, Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator +"ViewModelExample_DB"+  File.separator +"note_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(new Callback() {
                        @Override
                        public void onCreate(@NonNull SupportSQLiteDatabase db) {
                            super.onCreate(db);
                            Log.e("sakib********","******* DB Created");
                            new PopulateDatabaseAsyncTask(instance).execute();
                        }
                    })
                    .build();
        }
        return instance;
    }

//    private static RoomDatabase.Callback roomCallback=new RoomDatabase.Callback() {
//        @Override
//        public void onCreate(@NonNull SupportSQLiteDatabase db) {
//            super.onCreate(db);
//            new PopulateDatabaseAsyncTask(instance).execute();
//        }
//    };

    private static class PopulateDatabaseAsyncTask extends AsyncTask<Void,Void,Void>
    {
        private NoteDao noteDao;

        private PopulateDatabaseAsyncTask(NoteDatabase db)
        {
            noteDao=db.noteDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            Log.e("sakib********","******* Insert Demo Data");
            noteDao.insert(new Note("Title 1","Description 1",1));
            noteDao.insert(new Note("Title 2","Description 2",2));
            noteDao.insert(new Note("Title 3","Description 3",3));
            return null;
        }
    }
}
