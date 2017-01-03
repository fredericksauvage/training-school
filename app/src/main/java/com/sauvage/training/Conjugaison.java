package com.sauvage.training;

/**
 * Created by frederick.sauvage on 16-12-06.
 */

public class Conjugaison {

    public CJEntry group;
    public CJEntry verb;
    public CJEntry mode;
    public CJEntry temps;

    public void clear(){
        group = null;
        verb = null;
        mode = null;
        temps = null;
    }

}
