package org.vazteixeira.rui.fanatix.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
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
import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

public class MainFragment extends BaseFragment {

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.inject(this, view);
        // ...

        // we're on the ui thread...
        final String itemId = "49797863";
        final EditText finalItemIdEditText = itemIdEditText;
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                finalItemIdEditText.setText(itemId);
            }
        },500); // FIXME hardcoded value

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        ButterKnife.reset(this);
    }

    // ***
    //

    @OnClick(R.id.fragment_main_huddle_Button)
    public void huddleButtonClicked() {

        String itemId = itemIdEditText.getText().toString();

        if (itemId == null || itemId.length() == 0) {

            Crouton.makeText(getActivity(), R.string.error_no_item_id, Style.ALERT).show();
        }
        else {

            mFriendsPresenter.showFriends(itemId);
        }
    }

}
