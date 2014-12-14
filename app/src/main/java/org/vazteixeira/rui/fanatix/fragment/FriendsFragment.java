package org.vazteixeira.rui.fanatix.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.vazteixeira.rui.fanatix.R;
import org.vazteixeira.rui.fanatix.network.FanatixNetwork;
import org.vazteixeira.rui.fanatix.network.pojo.ItemFriendsResponsePojo;
import org.vazteixeira.rui.fanatix.view.LoadingPresenter;

import retrofit.Callback;
import retrofit.RetrofitError;

/**
 * Created by rmvt on 14/12/14.
 */
public class FriendsFragment extends Fragment {

    public static final String TAG = "FriendsFragment";
    public static final String ITEM_ID_ARGUMENT = "itemid";

    private String mItemId;
    private FanatixNetwork mFanatixNetwork;

    private LoadingPresenter mLoadingPresenter;


    // ***
    //

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

            mLoadingPresenter = (LoadingPresenter) activity;
        }
        catch (ClassCastException exception) {

            throw new ClassCastException(activity.toString() + " must implement "
                    + LoadingPresenter.class.getSimpleName());
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // TODO load data passed via bundle
        if (getArguments() != null) {

            mItemId = getArguments().getString(ITEM_ID_ARGUMENT);
        }

        if (mItemId == null || mItemId.length() == 0) {

            // TODO error!
            Log.e(TAG, "No item id was supplied...");
        }
        else {

            mLoadingPresenter.showLoading();

            mFanatixNetwork = new FanatixNetwork(); // FIXME consider singleton
            mFanatixNetwork.init();

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

                            Log.d(TAG, "SUCCESS!");
                            mLoadingPresenter.hideLoading();
                        }

                        @Override
                        public void failure(RetrofitError error) {

                            Log.d(TAG, "FAILURE: " + error.getMessage());
                            mLoadingPresenter.hideLoading();
                        }
                    }
            );

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_friends, container, false);

        // ...

        return view;
    }


}
