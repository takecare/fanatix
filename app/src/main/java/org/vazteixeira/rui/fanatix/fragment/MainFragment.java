package org.vazteixeira.rui.fanatix.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.vazteixeira.rui.fanatix.R;

public class MainFragment extends Fragment {

    public static final String TAG = "MainFragment";


    public static MainFragment newInstance() {

        return new MainFragment();
    }

    // ***
    // LIFECYCLE

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container, false);

        // ...

        return view;
    }

}
