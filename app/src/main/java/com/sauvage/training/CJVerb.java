package com.sauvage.training;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by frederick on 16-11-22.
 */

public class CJVerb  extends CJEntries{

    public CJVerb(CJActivity activity, SQLiteDatabase db, CJEntries previous){
        super(activity, db, previous);
    }

    public void init(){
        if(this.viewName == null) {
            this.viewName = "verbes";
            this.next = new CJMode(activity, db, this);
        }
        super.init();
    }

    @Override
    protected void clear() {
        super.clear();
        app.conjugaison.verb = null;
    }

    @Override
    public void setSelected(int id){
        super.setSelected(id);
        app.conjugaison.verb = this.itemSelected;
        app.conjugaison.mode = null;
    }
    @Override
    protected int getView(){
        return  R.id.verbes;
    }
    protected void  load(){
        this.query = "SELECT * FROM verbe where groupe = ?";
        super.load();
    }

    public CJMode getModes(){
        return (CJMode) this.next;
    }
}
