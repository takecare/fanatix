package org.vazteixeira.rui.fanatix.view;

import org.vazteixeira.rui.fanatix.model.Friend;

import java.util.List;

/**
 * Created by rmvt on 14/12/14.
 */
public interface ResultsPresenter {

    public void showResults(List<Friend> selectedFriendsList);
    public void hideResults();

}
