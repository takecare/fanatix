package org.vazteixeira.rui.fanatix.fragment;

import android.app.Activity;
import android.support.v4.app.Fragment;

import org.vazteixeira.rui.fanatix.view.FragmentChangedListener;

public class BaseFragment extends Fragment {

    protected FragmentChangedListener mFragmentChangedListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {

            mFragmentChangedListener = (FragmentChangedListener) activity;
        }
        catch (ClassCastException exception) {

            throw new ClassCastException(activity.toString() + " must implement "
                    + FragmentChangedListener.class.getSimpleName());
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        mFragmentChangedListener.fragmentChanged(this);
    }
}
