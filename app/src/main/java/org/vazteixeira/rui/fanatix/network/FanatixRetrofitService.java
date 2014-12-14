package org.vazteixeira.rui.fanatix.network;

import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by rmvt on 14/12/14.
 */
public interface FanatixRetrofitService {

    @FormUrlEncoded
    @POST("/news/item-friends")
    public void listFriendsInterestedInItemFormEncoded(
            @Field("app-id") String appId,
            @Field("app-version") String appVersion,
            @Field("app-platform") String appPlatform,
            @Field("include-all") boolean shouldIncludeAll,
            @Field("itemid") String itemId,
            @Field("auth-fanatix-id") String authId,
            @Field("auth-fanatix-token") String authToken//,

            //Callback<Map<String,List<Friend>>> callback
    );
}
