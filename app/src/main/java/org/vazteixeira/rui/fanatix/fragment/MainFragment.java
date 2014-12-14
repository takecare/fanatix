package org.vazteixeira.rui.fanatix.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import org.vazteixeira.rui.fanatix.R;
import org.vazteixeira.rui.fanatix.view.FriendsPresenter;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class MainFragment extends Fragment {

    public static final String TAG = "MainFragment";

    @InjectView(R.id.fragment_main_itemid_EditText) EditText itemIdEditText;
    @InjectView(R.id.fragment_main_huddle_Button)   Button huddleButton;

    private FriendsPresenter mFriendsPresenter;

    public static MainFragment newInstance() {

        return new MainFragment();
    }

    // ***
    // LIFECYCLE

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {

            mFriendsPresenter = (FriendsPresenter) activity;
        }
        catch (ClassCastException exception) {

            throw new ClassCastException(activity.toString() + " must implement "
                    + FriendsPresenter.class.getSimpleName());
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.inject(this, view);
        // ...

        return view;
    }


    // ***
    //

    @OnClick(R.id.fragment_main_huddle_Button)
    public void huddleButtonClicked() {

        String itemId = itemIdEditText.getText().toString();

        if (itemId == null || itemId.length() == 0) {

            // TODO error!
        }
        else {

            mFriendsPresenter.showFriends(itemId);
        }
    }

}
