package org.vazteixeira.rui.fanatix.adapter.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import org.vazteixeira.rui.fanatix.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by rmvt on 14/12/14.
 */
public class FriendViewHolder {

    @InjectView(R.id.list_row_title_TextView)           public TextView titleTextView;
    @InjectView(R.id.list_row_avatar_ImageView)         public ImageView avatarImageView;
    @InjectView(R.id.list_row_selected_ToggleButton)    public ToggleButton selectedToggleButton;

    public FriendViewHolder(View view) {

        ButterKnife.inject(this, view);
    }
}