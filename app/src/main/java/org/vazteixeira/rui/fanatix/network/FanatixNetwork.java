package org.vazteixeira.rui.fanatix.network;

import org.vazteixeira.rui.fanatix.network.pojo.ItemFriendsResponsePojo;

import retrofit.Callback;
import retrofit.Endpoint;
import retrofit.RestAdapter;

/**
 * Created by rmvt on 14/12/14.
 */
public class FanatixNetwork {

    // TODO maybe load url from strings.xml

    public static final Endpoint sLiveEndpoint = new Endpoint() {
        @Override
        public String getUrl() {
            return "http://api.fanatix.com/app-api/1.5";
        }

        @Override
        public String getName() {
            return "Live endpoint";
        }
    };

    private RestAdapter mRestAdapter;
    private Endpoint mEndpoint;

    private FanatixRetrofitService mFanatixService;

    private String mAuthId;
    private String mAuthToken;

    public FanatixNetwork() {}

    public void init() {

        initWithEndpoint(sLiveEndpoint);
    }

    public void initWithEndpoint(Endpoint endpoint) {

        mRestAdapter = new RestAdapter.Builder()
                .setEndpoint(endpoint)
                .build();

        mFanatixService = mRestAdapter.create(FanatixRetrofitService.class);
    }

    public void listFriendsInterestedInItemFormEncoded(String appId, String appVersion, String appPlatform,
                                                       boolean shouldIncludeAll, String itemId, String authId,
                                                       String authToken,
                                                       Callback<ItemFriendsResponsePojo> callback) {

        mFanatixService.listFriendsInterestedInItemFormEncoded(appId, appVersion, appPlatform, shouldIncludeAll, itemId,
                authId, authToken, callback);
    }
}
