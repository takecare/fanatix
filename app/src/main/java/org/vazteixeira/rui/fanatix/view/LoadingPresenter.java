package org.vazteixeira.rui.fanatix.view;

/**
 * Created by rmvt on 14/12/14.
 */
public interface LoadingPresenter {

    public void showLoading();
    public void showLoadingForTimeinterval(long timerInvervalMs);
    public void hideLoading();

}
