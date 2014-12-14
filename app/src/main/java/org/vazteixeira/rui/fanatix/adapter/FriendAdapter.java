package org.vazteixeira.rui.fanatix.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import org.vazteixeira.rui.fanatix.model.Friend;

import java.util.List;

import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;

/**
 * Created by rmvt on 14/12/14.
 */
public class FriendAdapter  extends BaseAdapter implements StickyListHeadersAdapter {

    public static final String TAG = "FriendAdapter";

    private LayoutInflater mLayoutInflater;
    private List<Friend> mFriends;

    // 3 sections: Recommended, Other, All
    private static final int TYPE_COUNT = 3;
    public static enum FRIEND_TYPE {
        TEAM, OTHER, ALL
    }

    public FriendAdapter(List<Friend> friends, Context context) {

        mLayoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mFriends = friends;
    }

    @Override
    public View getHeaderView(int position, View convertView, ViewGroup parent) {

        return null;
    }

    @Override
    public long getHeaderId(int position) {

        return 0;
    }

    @Override
    public int getCount() {

        return 0;
    }

    @Override
    public Object getItem(int position) {

        return null;
    }

    @Override
    public long getItemId(int position) {

        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        return null;
    }

    @Override
    public int getViewTypeCount() {

        return FRIEND_TYPE.values().length;
    }
}
