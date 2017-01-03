package com.sauvage.training;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by frederick on 16-11-22.
 */

public class CJGroups extends CJEntries {

    public CJVerb verbs;
    public CJGroups(CJActivity activity, SQLiteDatabase db, CJEntries previous){
        super(activity, db, previous);
    }
    protected void init() {
        if(this.viewName == null) {
            this.viewName = "groupes";
            this.next = new CJVerb(activity, db, this);
        }
        super.init();
    }
    public void  load(){
        this.query = "SELECT * FROM groupe";
        super.load();
    }

    @Override
    protected int getView(){
        return  R.id.groups;
    }

    @Override
    protected void clear() {
        super.clear();
        app.conjugaison.group = null;
    }

    @Override
    public void setSelected(int id){
        super.setSelected(id);
        app.conjugaison.group = this.itemSelected;
    }

    public CJVerb getVerbs(){
        return (CJVerb) this.next;
    }
}
