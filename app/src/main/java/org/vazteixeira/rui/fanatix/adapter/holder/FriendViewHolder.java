package org.vazteixeira.rui.fanatix.adapter.holder;

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

/**
 * Created by rmvt on 14/12/14.
 */
public class FriendViewHolder {

    protected TextView titleTextView;
    protected ImageView avatarImageView;
    protected ToggleButton selectedCheckBox;

    public TextView getTitleTextView() {
        return titleTextView;
    }

    public void setTitleTextView(TextView titleTextView) {
        this.titleTextView = titleTextView;
    }

    public ImageView getAvatarImageView() {
        return avatarImageView;
    }

    public void setAvatarImageView(ImageView avatarImageView) {
        this.avatarImageView = avatarImageView;
    }

    public ToggleButton getSelectedCheckBox() {
        return selectedCheckBox;
    }

    public void setSelectedCheckBox(ToggleButton selectedCheckBox) {
        this.selectedCheckBox = selectedCheckBox;
    }
}