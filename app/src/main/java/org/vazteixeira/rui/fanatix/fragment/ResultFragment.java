package org.vazteixeira.rui.fanatix.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.vazteixeira.rui.fanatix.R;
import org.vazteixeira.rui.fanatix.adapter.FriendAdapter;
import org.vazteixeira.rui.fanatix.model.Friend;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import se.emilsjolander.stickylistheaders.StickyListHeadersListView;

/**
 * Created by rmvt on 14/12/14.
 */
public class ResultFragment extends Fragment {

    public static final String TAG = "ResultFragment";
    public static final String FRIENDS_LIST_ARGUMENT = "friends";

    @InjectView(R.id.fragment_result_friends_ListView)  StickyListHeadersListView mListView;
    @InjectView(R.id.fragment_result_warning_TextView)  TextView mWarningTextView;

    private FriendAdapter mFriendAdapter;
    private List<Friend> mSelectedFriends;
    private boolean mAreThereNonPrimaryFriends = false;


    // ************
    // NEW INSTANCE

    public static ResultFragment newInstance() {

        return new ResultFragment();
    }

    public static ResultFragment newInstance(ArrayList<Friend> friends) {

        Bundle arguments = new Bundle();
        arguments.putParcelableArrayList(FRIENDS_LIST_ARGUMENT, friends);

        ResultFragment resultFragment = new ResultFragment();
        resultFragment.setArguments(arguments);

        return resultFragment;
    }


    // *********
    // LIFECYLCE

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {

            mSelectedFriends = getArguments().getParcelableArrayList(FRIENDS_LIST_ARGUMENT);
            for (Friend friend : mSelectedFriends) {

                if (!friend.isPrimary()) {

                    mAreThereNonPrimaryFriends = true;
                    break;
                }
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_result, container, false);

        ButterKnife.inject(this, view);

        if (mSelectedFriends != null) {

            mFriendAdapter = new FriendAdapter(mSelectedFriends, null, getActivity());
            mListView.setAdapter(mFriendAdapter);
            mWarningTextView.setVisibility(mAreThereNonPrimaryFriends ? View.VISIBLE : View.GONE);
        }

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        ButterKnife.reset(this);
    }

}
