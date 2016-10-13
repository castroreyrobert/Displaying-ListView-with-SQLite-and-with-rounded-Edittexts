package com.example.castroreyrobert.notesdemo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;



import java.util.ArrayList;
import java.util.List;


public class NoteAdapter extends ArrayAdapter {

    List list = new ArrayList();

    public NoteAdapter(Context context, int resource) {
        super(context, resource);
    }


    public void add(NoteModel object) {
        list.add(object);
        super.add(object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;
        ViewHolder viewHolder;

        if (row == null){
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.custom_row,parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tvTitle2 = (TextView)row.findViewById(R.id.tvTitle2);
            viewHolder.tvDate2 = (TextView)row.findViewById(R.id.tvDate2);

            row.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) row.getTag();
        }

        NoteModel noteModel = (NoteModel) getItem(position);
        viewHolder.tvTitle2.setText(noteModel.getTitle().toString());
        viewHolder.tvDate2.setText(noteModel.getDate().toString());
        return row;
    }


    static class ViewHolder{
        TextView tvDate2, tvTitle2;
    }
}
