package org.vazteixeira.rui.fanatix.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;

import com.squareup.picasso.Picasso;

import org.vazteixeira.rui.fanatix.R;
import org.vazteixeira.rui.fanatix.adapter.holder.FriendViewHolder;
import org.vazteixeira.rui.fanatix.adapter.holder.HeaderViewHolder;
import org.vazteixeira.rui.fanatix.adapter.holder.RecommendedViewHolder;
import org.vazteixeira.rui.fanatix.model.Friend;
import org.vazteixeira.rui.fanatix.view.FriendSelectedListener;

import java.util.ArrayList;
import java.util.List;

import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;

public class FriendAdapter  extends BaseAdapter implements StickyListHeadersAdapter {

    public static final String TAG = "FriendAdapter";

    private Context mContext;
    private Resources mResources;

    private LayoutInflater mLayoutInflater;
    private List<Friend> mFriends;
    private List<Friend> mSelectedFriends;

    private FriendSelectedListener mFriendSelectedListener;

    public static enum FRIEND_TYPE {
        TEAM, OTHER, ALL
    }

    public FriendAdapter(List<Friend> friends, FriendSelectedListener friendSelectedListener, Context context) {

        mLayoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mFriendSelectedListener = friendSelectedListener;
        mSelectedFriends = new ArrayList<>();
        mResources = context.getResources();
        mContext = context;
        mFriends = friends;
    }

    @Override
    public View getHeaderView(int position, View convertView, ViewGroup parent) {

        HeaderViewHolder headerViewHolder;

        if (convertView == null) {

            convertView = mLayoutInflater.inflate(R.layout.list_row_header, parent, false);

            headerViewHolder = new HeaderViewHolder(convertView);
            convertView.setTag(headerViewHolder);
        }
        else {

            headerViewHolder = (HeaderViewHolder) convertView.getTag();
        }

        if (getItemViewType(position) == FRIEND_TYPE.TEAM.ordinal()) {

            headerViewHolder.titleTextView.setText(mResources.getString(R.string.header_view_title_recommended));
        }
        else if (getItemViewType(position) == FRIEND_TYPE.OTHER.ordinal()) {

            headerViewHolder.titleTextView.setText(mResources.getString(R.string.header_view_title_other));
        }
        else {

            headerViewHolder.titleTextView.setText(mResources.getString(R.string.header_view_title_all));
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
    public View getView(final int position, View convertView, ViewGroup parent) {

        FriendViewHolder friendViewHolder;

        if (convertView == null) {

            if (getItemViewType(position) == FRIEND_TYPE.TEAM.ordinal()) {

                convertView = mLayoutInflater.inflate(R.layout.list_row_recommended, parent, false);
                friendViewHolder = new RecommendedViewHolder(convertView);
            }
            else {

                convertView = mLayoutInflater.inflate(R.layout.list_row_normal, parent, false);
                friendViewHolder = new FriendViewHolder(convertView);
            }

            convertView.setTag(friendViewHolder);
        }
        else {

            friendViewHolder = (FriendViewHolder) convertView.getTag();
        }

        friendViewHolder.titleTextView.setText(getItem(position).getName());

        friendViewHolder.selectedToggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                getItem(position).setSelected(isChecked);

                if (isChecked) {

                    mSelectedFriends.add(getItem(position));
                }
                else {

                    mSelectedFriends.remove(getItem(position));
                }

                if (mFriendSelectedListener != null) {

                    mFriendSelectedListener.friendSelected(isChecked, position);
                }
            }
        });

        friendViewHolder.selectedToggleButton.setChecked(getItem(position).isSelected());

        // not using state list drawable because this is more readable
        if (getItem(position).isPrimary()) {

            friendViewHolder.chatFrameLayout.setVisibility(View.VISIBLE);
            friendViewHolder.chatFrameLayout.setBackgroundColor(
                    getItem(position).isChat() ?
                        mResources.getColor(R.color.chat_capable)
                        : mResources.getColor(R.color.not_chat_capable));
        }
        else {

            friendViewHolder.chatFrameLayout.setVisibility(View.INVISIBLE);
        }

        Picasso.with(mContext).load(getItem(position).getImage()).into(friendViewHolder.avatarImageView);

        if (getItemViewType(position) == FRIEND_TYPE.TEAM.ordinal()) {

            ((RecommendedViewHolder)friendViewHolder).teamTextView.setText(getItem(position).getTeam());
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

    @Override
    public boolean hasStableIds() {

        return true;
    }

    public List<Friend> getSelectedFriends() {

        ArrayList<Friend> selectedFriends = new ArrayList<>();
        for (Friend friend : mFriends) {

            if (friend.isSelected()) {

                selectedFriends.add(friend);
            }
        }

        return selectedFriends;
    }

    public boolean hasSelectedFriends() {

        // having a counter here removes the need to iterate through the list all the time
        return mSelectedFriends.size() > 0;
    }
}
