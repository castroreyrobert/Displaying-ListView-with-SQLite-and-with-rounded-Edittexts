package com.example.castroreyrobert.notesdemo;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.Toast;


public class BackgroundTask extends AsyncTask<String, NoteModel, String> {

    Context context;
    NoteAdapter noteAdapter;
    Activity activity;
    ListView lvNote;

    public BackgroundTask(Context context) {
        this.context = context;
        activity = (Activity) context;
    }

    @Override
    protected String doInBackground(String... params) {

        String method = params[0];
        DBHandler dbHandler = new DBHandler(context);

        if (method.equals("add_info")){
            String title = params[1];
            String note = params[2];
            String date = params[3];

            SQLiteDatabase db = dbHandler.getWritableDatabase();
            NoteModel noteModel = new NoteModel( title, note, date);
            dbHandler.addNotes(db,noteModel);

            return "One Row is inserted";
        }else if (method.equals("view_info")){
            lvNote = (ListView)activity.findViewById(R.id.lvNotes);
            SQLiteDatabase db = dbHandler.getReadableDatabase();
            Cursor res = dbHandler.viewNotes(db);

            noteAdapter = new NoteAdapter(context,R.layout.custom_row);

            String title,note,date;

            while (res.moveToNext()){
                title = res.getString(res.getColumnIndex(DBHandler.COL_2));
                note = res.getString(res.getColumnIndex(DBHandler.COL_3));
                date = res.getString(res.getColumnIndex(DBHandler.COL_4));

                NoteModel notemodel = new NoteModel(title, note, date);
                publishProgress(notemodel);
            }
            return "view_info";
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(NoteModel... values) {
        noteAdapter.add(values[0]);
    }

    @Override
    protected void onPostExecute(String s) {
        if (s.equals("view_info")){
            lvNote.setAdapter(noteAdapter);
        }else {
            Toast.makeText(context,s, Toast.LENGTH_LONG).show();
        }
    }
}


