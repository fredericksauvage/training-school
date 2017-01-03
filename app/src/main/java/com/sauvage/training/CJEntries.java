package com.sauvage.training;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by frederick on 16-11-26.
 */

public class CJEntries {
    List<CJEntry> entries;
    CJEntries next, previous;
    ListView listViewContainer;
    CJActivity activity;
    CJEntry itemSelected;
    SQLiteDatabase db;
    String viewName = null;
    String query = null;
    TrainingApplication app;

    public CJEntries(){}

    public CJEntries(CJActivity activity,SQLiteDatabase db, CJEntries previous){
        this.previous = previous;
        this.activity = activity;
        this.app = (TrainingApplication) this.activity.getApplication();
        this.db = db;
        this.listViewContainer = (ListView) activity.findViewById(this.getView());
        this.listViewContainer.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long arg3) {
                view.setSelected(true);
                CJEntries.this.setSelected( position );
            }
        });
    }

    protected int getView(){
        return  0;
    }
    protected void init(){
        if(this.next != null){
            this.next.clear();
        }
        this.next = next;

        String[] parameters;
        if (this.previous != null){
            parameters = new String[]{ Integer.toString( previous.getItemSelected().id ) };
        }
    }
    public CJEntry getItemSelected(){
        return this.itemSelected;
    }

    public void setSelected(int indexSelected){
        if(indexSelected<0){
            this.itemSelected = null;
        }else {
            this.itemSelected = this.entries.get(indexSelected);
        }
        if (this.next != null) {
            this.next.clear();
            this.next.init();
            this.next.load();
        }
    }


    protected List<CJEntry> select(String query, String[] parameters){

        entries = new ArrayList<>();
        Cursor cursor = this.db.rawQuery(query, parameters);
        cursor.moveToFirst();
        int cpt = 0;
        while (!cursor.isAfterLast()) {
            int group = Integer.parseInt( cursor.getString(0));
            entries.add( new CJEntry(group, cursor.getString(1), cpt) );
            cursor.moveToNext();
            cpt++;
        }
        cursor.close();
        return entries;
    }

    protected String[] getParameters(){
        String[] parameters = null;
        if (this.previous != null){
            parameters = new String[]{ Integer.toString( previous.getItemSelected().id ) };
        }
        return  parameters;
    }

    protected void load(){
       //attacher l'adaptateur
        this.entries = this.select(query, this.getParameters());
        ArrayAdapter dataAdapter = new ArrayAdapter(activity, android.R.layout.simple_list_item_1, this.entries) ;
        this.listViewContainer.setAdapter(dataAdapter);
    }

    protected void clear(){
        if(this.listViewContainer != null) {
            this.listViewContainer.setAdapter(null);
        }
        if(this.next != null){
            this.next.clear();
        }
    }
}
