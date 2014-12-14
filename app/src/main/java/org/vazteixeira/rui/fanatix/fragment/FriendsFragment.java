package org.vazteixeira.rui.fanatix.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.vazteixeira.rui.fanatix.R;
import org.vazteixeira.rui.fanatix.network.FanatixNetwork;

/**
 * Created by rmvt on 14/12/14.
 */
public class FriendsFragment extends Fragment {

    public static final String TAG = "FriendsFragment";
    public static final String ITEM_ID_ARGUMENT = "itemid";

    private String mItemId;
    private FanatixNetwork mFanatixNetwork;
    private FriendsPresenter mFriendsPresenter;


    public static FriendsFragment newInstance() {

        return new FriendsFragment();
    }

    public static FriendsFragment newInstance(String itemId) {

        Bundle arguments = new Bundle();
        arguments.putString(ITEM_ID_ARGUMENT, itemId);

        FriendsFragment friendsFragment = new FriendsFragment();
        friendsFragment.setArguments(arguments);

        return friendsFragment;
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

        // TODO load data passed via bundle
        if (getArguments() != null) {

            mItemId = getArguments().getString(ITEM_ID_ARGUMENT);
        }

        if (mItemId == null || mItemId.length() < 1) {

            // TODO error!
        }
        else {

            mFanatixNetwork = new FanatixNetwork(); // FIXME consider singleton
            // TODO issue request

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_friends, container, false);

        // ...

        return view;
    }


    // ***
    //

    public interface FriendsPresenter {

        public void showFriends();
        public void hideFriends();
    }
}
