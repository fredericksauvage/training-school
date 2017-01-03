package com.sauvage.training;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by frederick on 16-11-22.
 */

public class CJMode extends CJEntries {

    public CJMode(CJActivity activity, SQLiteDatabase db, CJEntries previous){
        super(activity, db, previous);
    }

    public void init(){
        if(this.viewName == null) {
            this.viewName = "modes";
            this.next = new CJTemps(activity, db, this);
        }
        super.init();
    }

    @Override
    protected void clear() {
        super.clear();
        app.conjugaison.mode = null;
    }

    @Override
    public void setSelected(int id){
        super.setSelected(id);
        app.conjugaison.mode = this.itemSelected;
    }

    @Override
    protected int getView(){
        return  R.id.modes;
    }
    protected void  load(){
        this.query = "SELECT mode.id, mode.value FROM mode, temps,conjugaison where conjugaison.verbe = ? and conjugaison.temps = temps.id and temps.mode = mode.id group by mode.id";
        super.load();
    }

    public CJTemps getTemps(){
        return (CJTemps) this.next;
    }
}
