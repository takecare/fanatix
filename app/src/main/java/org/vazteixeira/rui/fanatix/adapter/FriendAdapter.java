package org.vazteixeira.rui.fanatix.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.vazteixeira.rui.fanatix.R;
import org.vazteixeira.rui.fanatix.adapter.holder.FriendViewHolder;
import org.vazteixeira.rui.fanatix.adapter.holder.HeaderViewHolder;
import org.vazteixeira.rui.fanatix.adapter.holder.RecommendedViewHolder;
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

        HeaderViewHolder headerViewHolder;

        if (convertView == null) {

            convertView = mLayoutInflater.inflate(R.layout.list_row_header, parent, false);

            headerViewHolder = new HeaderViewHolder();
            headerViewHolder.setTitleTextView((TextView) convertView.findViewById(R.id.header_view_title_TextView));
            convertView.setTag(headerViewHolder);
        }
        else {

            headerViewHolder = (HeaderViewHolder) convertView.getTag();
        }

        if (getItemViewType(position) == FRIEND_TYPE.TEAM.ordinal()) {

            headerViewHolder.getTitleTextView().setText("RECOMMENDED"); // FIXME
        }
        else if (getItemViewType(position) == FRIEND_TYPE.OTHER.ordinal()) {

            headerViewHolder.getTitleTextView().setText("OTHER"); // FIXME
        }
        else {

            headerViewHolder.getTitleTextView().setText("ALL"); // FIXME
        }

        return convertView;
    }

    @Override
    public long getHeaderId(int position) {

        return getItemViewType(position);
    }

    @Override
    public int getCount() {

        return mFriends.size();
    }

    @Override
    public Friend getItem(int position) {

        return mFriends.get(position);
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        FriendViewHolder friendViewHolder;

        if (convertView == null) {

            if (getItemViewType(position) == FRIEND_TYPE.TEAM.ordinal()) {

                convertView = mLayoutInflater.inflate(R.layout.list_row_recommended, parent, false);
                friendViewHolder = new RecommendedViewHolder();
                friendViewHolder.setTitleTextView(
                        (TextView) convertView.findViewById(R.id.list_row_recommended_title_TextView));
                ((RecommendedViewHolder)friendViewHolder).setTeamTextView(
                        (TextView) convertView.findViewById(R.id.list_row_recommended_team_TextView));
            }
            else {

                convertView = mLayoutInflater.inflate(R.layout.list_row_normal, parent, false);
                friendViewHolder = new FriendViewHolder();
                friendViewHolder.setTitleTextView(
                        (TextView) convertView.findViewById(R.id.list_row_normal_title_TextView));
            }

            convertView.setTag(friendViewHolder);
        }
        else {

            friendViewHolder = (FriendViewHolder) convertView.getTag();
        }

        friendViewHolder.getTitleTextView().setText(getItem(position).getName());
        if (getItemViewType(position) == FRIEND_TYPE.TEAM.ordinal()) {

            ((RecommendedViewHolder)friendViewHolder).getTeamTextView().setText(getItem(position).getTeam());
        }

        return convertView;
    }

    @Override
    public int getViewTypeCount() {

        return FRIEND_TYPE.values().length;
    }

    @Override
    public int getItemViewType(int position) {

        if (mFriends.get(position).isRecommended()) {

            return FRIEND_TYPE.TEAM.ordinal();
        }
        else if (mFriends.get(position).isOther()) {

            return FRIEND_TYPE.OTHER.ordinal();
        }
        else {

            return FRIEND_TYPE.ALL.ordinal();
        }
    }
}
