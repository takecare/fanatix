package org.vazteixeira.rui.fanatix.adapter.holder;

import android.widget.TextView;
import android.widget.ToggleButton;

/**
 * Created by rmvt on 14/12/14.
 */
public class FriendViewHolder {

    protected TextView titleTextView;
    protected ToggleButton selectedCheckBox;

    public TextView getTitleTextView() {
        return titleTextView;
    }

    public void setTitleTextView(TextView titleTextView) {
        this.titleTextView = titleTextView;
    }

    public ToggleButton getSelectedCheckBox() {
        return selectedCheckBox;
    }

    public void setSelectedCheckBox(ToggleButton selectedCheckBox) {
        this.selectedCheckBox = selectedCheckBox;
    }
}