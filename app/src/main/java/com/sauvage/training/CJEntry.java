package com.sauvage.training;

/**
 * Created by frederick on 16-11-27.
 */

public class CJEntry {
    public int id;
    public int index;
    String value;

    public CJEntry(int id, String value, int index){
        this.id = id;
        this.index = index;
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

}
