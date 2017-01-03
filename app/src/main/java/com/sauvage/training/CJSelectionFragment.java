package com.sauvage.training;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by frederick on 16-11-30.
 */

public class CJSelectionFragment extends Fragment {
    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = (View) inflater.inflate(R.layout.conj_select, container, false);
        //Instancier vos composants graphique ici (faîtes vos findViewById)
        return view;
    }

}
