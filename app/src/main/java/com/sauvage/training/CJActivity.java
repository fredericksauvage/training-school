package com.sauvage.training;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class CJActivity extends AppCompatActivity {

    DBHandler dbh ;
    CJGroups groups;
    HashMap<String, Integer> views;
    TrainingApplication app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbh = new DBHandler(this);
        dbh.open();

    }


    @Override
    protected void onResume(){

        super.onResume();
        this.app = (TrainingApplication) this.getApplication();
        setContentView(R.layout.conjugaison);
        String tag = (String) findViewById(R.id.current_view).getTag().toString();
        if(tag.equals("portrait") && app.conjugaison != null && app.conjugaison.temps!=null){
            setContentView(R.layout.conj_form);
            fillForm();
        }else{
            /*setContentView(R.layout.conjugaison);*/
            this.views = new HashMap<String, Integer>();
            groups = new CJGroups(this, dbh.getDatabase(), null);
            CJEntry verb = null;
            CJEntry group = null;
            CJEntry mode = null;
            CJEntry temp = null;
            if(app.conjugaison != null) {
                group = app.conjugaison.group;
                verb = app.conjugaison.verb;
                mode = app.conjugaison.mode;
                temp = app.conjugaison.temps;
            }
            groups.init();
            groups.load();
            if(group != null){
                groups.setSelected(group.index);
                if(verb != null){
                    CJVerb verbs = groups.getVerbs();
                    verbs.setSelected(verb.index);
                    if(mode != null){
                        CJMode modes = verbs.getModes();
                        modes.setSelected(mode.index);
                        if(temp != null){
                            CJTemps temps = modes.getTemps();
                            temps.setSelected(temp.index);
                        }
                    }
                }
            }
        }
    }

    public int getView(String viewName){
        return this.views.get(viewName);
    }

    public void loadForm(){
        String tag = (String) findViewById(R.id.current_view).getTag().toString();
        if(tag.equals("portrait")) {
            Intent form = new Intent(this, CJActivity.class);
            CJGroups groups = this.groups;
            CJVerb verbs = (CJVerb) groups.next;
            CJMode mode = (CJMode) verbs.next;
            CJTemps temps = (CJTemps) mode.next;

            form.putExtra("group", groups.itemSelected.id);
            form.putExtra("verb", groups.next.itemSelected.id);
            form.putExtra("mode", groups.next.next.itemSelected.id);
            form.putExtra("temps", groups.next.next.next.itemSelected.id);
            this.startActivity(form);
            finish();
        } else {
            fillForm();
        }
    }

    public void fillForm(){
        EditText g = (EditText) findViewById(R.id.pers_0);
        EditText v = (EditText) findViewById(R.id.pers_1);
        EditText m = (EditText) findViewById(R.id.pers_2);
        EditText t = (EditText) findViewById(R.id.pers_3);
        g.setText(app.conjugaison.group.toString());
        v.setText(app.conjugaison.verb.toString());
        m.setText(app.conjugaison.mode.toString());
        t.setText(app.conjugaison.temps.toString());
    }

    @Override
    public void onBackPressed()  {

        Fragment selection = (Fragment) getFragmentManager().findFragmentById(R.id.fg_select);
        if(selection==null || !selection.isVisible() /*app.conjugaison.temps != null*/) {
            app.conjugaison.temps = null;
            Intent intent = new Intent(this, CJActivity.class);
            /*intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);*/
            this.startActivity(intent);
            finish();
        }else{
            super.onBackPressed();
        }
    }

    /* UNUSED */
    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }
}
