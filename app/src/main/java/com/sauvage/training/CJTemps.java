package com.sauvage.training;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by frederick on 16-11-22.
 */

public class CJTemps extends CJEntries{


    public CJTemps(CJActivity activity, SQLiteDatabase db, CJEntries previous){
        super(activity, db, previous);
    }

    public void init(){
        this.viewName = "temps";
        super.init();
    }

    protected String[] getParameters(){
        String[] parameters = null;
        parameters = new String[]{
                Integer.toString( previous.previous.getItemSelected().id ),
                Integer.toString( previous.getItemSelected().id ) };
        return  parameters;
    }

    @Override
    protected int getView(){
        return  R.id.temps;
    }
    protected void  load(){
        this.query = "SELECT temps.id, temps.value, temps.mode FROM conjugaison,temps where conjugaison.verbe = ? and conjugaison.temps = temps.id and temps.mode = ?";
        super.load();
    }

    @Override
    protected void clear() {
        super.clear();
        app.conjugaison.temps = null;
    }

    @Override
    public void setSelected(int id){
        super.setSelected(id);
        app.conjugaison.temps = this.itemSelected;
        activity.loadForm();

    }
}
