package org.vazteixeira.rui.fanatix.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import org.vazteixeira.rui.fanatix.R;
import org.vazteixeira.rui.fanatix.adapter.FriendAdapter;
import org.vazteixeira.rui.fanatix.model.Friend;
import org.vazteixeira.rui.fanatix.network.FanatixNetwork;
import org.vazteixeira.rui.fanatix.network.pojo.ItemFriendsResponsePojo;
import org.vazteixeira.rui.fanatix.view.FriendSelectedListener;
import org.vazteixeira.rui.fanatix.view.LoadingPresenter;
import org.vazteixeira.rui.fanatix.view.ResultsPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;
import retrofit.Callback;
import retrofit.RetrofitError;
import se.emilsjolander.stickylistheaders.StickyListHeadersListView;

public class FriendsFragment extends BaseFragment implements FriendSelectedListener {

    public static final String TAG = "FriendsFragment";
    public static final String ITEM_ID_ARGUMENT = "itemid";

    private String mItemId;
    private FanatixNetwork mFanatixNetwork;

    private LoadingPresenter mLoadingPresenter;
    private ResultsPresenter mResultsPresenter;

    private FriendAdapter mFriendAdapter;
    @InjectView(R.id.fragment_friends_friends_ListView) StickyListHeadersListView mListView;
    @InjectView(R.id.fragment_friends_create_Button)    Button mCreateButton;


    // ************
    // NEW INSTANCE

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


    // *********
    // LIFECYCLE

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {

            mLoadingPresenter = (LoadingPresenter) activity;
            mResultsPresenter = (ResultsPresenter) activity;
        }
        catch (ClassCastException exception) {

            throw new ClassCastException(activity.toString() + " must implement "
                    + LoadingPresenter.class.getSimpleName() + " and "
                    + ResultsPresenter.class.getSimpleName());
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {

            mItemId = getArguments().getString(ITEM_ID_ARGUMENT);
        }

        if (mItemId == null || mItemId.length() == 0) {

            Crouton.makeText(getActivity(), R.string.error_no_item_id, Style.ALERT).show();
            Log.e(TAG, "No item id was supplied...");
        }
        else {

            mFanatixNetwork = new FanatixNetwork(); // FIXME consider singleton
            mFanatixNetwork.init();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_friends, container, false);

        ButterKnife.inject(this, view);

        if (mItemId != null && mItemId.length() > 0) { // double check

            requestData();
        }

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        ButterKnife.reset(this);
    }


    // ***************
    // CLICK LISTENERS

    @OnClick(R.id.fragment_friends_create_Button)
    public void onCreateClicked() {

        mResultsPresenter.showResults(mFriendAdapter.getSelectedFriends());
    }


    // *******
    // HELPERS

    private void requestData() {

        mLoadingPresenter.showLoading();
        mFanatixNetwork.listFriendsInterestedInItemFormEncoded(
                "cos-iphone", // FIXME hardcoded values!
                "1.2.3AT",
                "ios",
                true,
                mItemId,
                "50f82e1d4a8b519d6d000069",
                "5fd203caf74e219f585067338b5afae3",
                new Callback<ItemFriendsResponsePojo>() {
                    @Override
                    public void success(ItemFriendsResponsePojo responsePojo, retrofit.client.Response response) {

                        mLoadingPresenter.hideLoading();
                        if (responsePojo.isResponseOk()) {

                            loadData(responsePojo);
                        }
                        else {

                            Crouton.makeText(getActivity(), R.string.error_bad_response, Style.ALERT).show();
                        }

                    }

                    @Override
                    public void failure(RetrofitError error) {

                        Log.d(TAG, "FAILURE: " + error.getMessage());
                        mLoadingPresenter.hideLoading();

                        Crouton.makeText(getActivity(), R.string.error_connectivity, Style.ALERT).show();

                    }
                }
        );
    }

    private void loadData(ItemFriendsResponsePojo responsePojo) {

        // "bonus points if you remove already used names from the "all" section, but it's by no means something you
        // should delay the test for." -- we could easily remove duplicated friends here, but won't waste time on that

        // we need to "flatten" the map...
        List<Friend> team;
        List<Friend> friends = new ArrayList<>();
        for (String key : responsePojo.teams.keySet()) {

            team = responsePojo.teams.get(key);
            for (Friend friend : team) {

                friend.setTeam(key);
                friends.add(friend);
            }
        }

        mFriendAdapter = new FriendAdapter(friends, this, getActivity());
        mListView.setAdapter(mFriendAdapter);
    }


    // ***********************
    // FriendSelectedListerner

    @Override
    public void friendSelected(boolean isSelected, int position) {

        mCreateButton.setEnabled(mFriendAdapter.hasSelectedFriends());
    }
}
