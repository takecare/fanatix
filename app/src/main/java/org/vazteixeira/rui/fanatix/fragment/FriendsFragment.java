package org.vazteixeira.rui.fanatix.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by rmvt on 14/12/14.
 */
public class FriendsFragment extends Fragment {

    public static final String TAG = "FriendsFragment";


    public static FriendsFragment newInstance() {

        return new FriendsFragment();
    }

    // ***
    // LIFECYCLE

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return null;
    }
}
