package org.vazteixeira.rui.fanatix.view;

/**
 * Created by rmvt on 14/12/14.
 */
public interface FriendSelectedListener {

    /**
     * Called when a friend in the list view is selected/deselected.
     * @param isSelected the new value for the friend.
     */
    public void friendSelected(boolean isSelected, int position);
}
