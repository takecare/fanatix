package org.vazteixeira.rui.fanatix.network.pojo;

import com.google.gson.annotations.SerializedName;

import org.vazteixeira.rui.fanatix.model.Friend;

import java.util.List;
import java.util.Map;

/**
 * Created by rmvt on 14/12/14.
 */
public class ItemFriendsResponsePojo {

    public static final String RESPONSE_OK = "ok";

    public String uid;
    public String status;
    public String version;

    @SerializedName("response")
    public Map<String,List<Friend>> teams;

    public boolean isResponseOk() {

        return status.equals(RESPONSE_OK);
    }
}
