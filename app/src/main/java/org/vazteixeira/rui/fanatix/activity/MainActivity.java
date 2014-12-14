package org.vazteixeira.rui.fanatix.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.ObjectAnimator;

import org.vazteixeira.rui.fanatix.R;
import org.vazteixeira.rui.fanatix.fragment.FriendsFragment;
import org.vazteixeira.rui.fanatix.fragment.MainFragment;
import org.vazteixeira.rui.fanatix.fragment.ResultFragment;
import org.vazteixeira.rui.fanatix.model.Friend;
import org.vazteixeira.rui.fanatix.view.FriendsPresenter;
import org.vazteixeira.rui.fanatix.view.LoadingPresenter;
import org.vazteixeira.rui.fanatix.view.ResultsPresenter;
import org.vazteixeira.rui.fanatix.view.StubAnimationListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class MainActivity extends ActionBarActivity implements FriendsPresenter, LoadingPresenter, ResultsPresenter {

    public static final String TAG = "MainActivity";

    @InjectView(R.id.main_activity_loading_RelativeLayout)  RelativeLayout loadingRelativeLayout;
    @InjectView(R.id.main_activity_loading_ProgressBar)     ProgressBar loadingProgressBar;

    // ***
    // LIFECYLE

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.main_activity_container_FrameLayout, MainFragment.newInstance(), MainFragment.class.getSimpleName())
                .commit();
    }

    @Override
    protected void onPause() {
        super.onPause();

    }


    // ***
    // MENU

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    protected void onSaveInstanceState(Bundle outState) {

        outState.putInt("teste",loadingRelativeLayout.getId());
    }

    protected void onRestoreInstanceState(Bundle savedInstanceState) {

        if (savedInstanceState != null && savedInstanceState.containsKey("teste")) {

            loadingRelativeLayout = (RelativeLayout) findViewById(savedInstanceState.getInt("teste"));
        }
    }


    // ***
    //

    @Override
    public void showFriends(String itemId) {

        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(FriendsFragment.class.getSimpleName())
                .replace(
                        R.id.main_activity_container_FrameLayout,
                        FriendsFragment.newInstance(itemId),
                        FriendsFragment.class.getSimpleName())
                .commit();
    }

    @Override
    public void hideFriends() {

        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(MainFragment.class.getSimpleName())
                .replace(
                        R.id.main_activity_container_FrameLayout,
                        MainFragment.newInstance(),
                        MainFragment.class.getSimpleName())
                .commit();
    }

    // ***
    //

    @Override
    public void showLoading() {

        loadingRelativeLayout.setVisibility(View.VISIBLE);
        ObjectAnimator.ofFloat(loadingRelativeLayout, "alpha", 0f, 1f)
                .setDuration(500)
                .start(); // FIXME hardcoded value
    }

    @Override
    public void showLoadingForTimeinterval(long timerInvervalMs) {

        // TODO
    }

    @Override
    public void hideLoading() {

        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(loadingRelativeLayout, "alpha", 1f, 0f)
                .setDuration(500); // FIXME hardcoded value

        objectAnimator.addListener(new StubAnimationListener() {
            @Override
            public void onAnimationEnd(Animator animation) {
                loadingRelativeLayout.setVisibility(View.GONE);
            }
        });

        objectAnimator.start();
    }


    // ***
    //

    @Override
    public void showResults(List<Friend> selectedFriendsList) {

        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(FriendsFragment.class.getSimpleName())
                .replace(
                        R.id.main_activity_container_FrameLayout,
                        ResultFragment.newInstance((ArrayList<Friend>) selectedFriendsList),
                        ResultFragment.class.getSimpleName())
                .commit();
    }

    @Override
    public void hideResults() {

        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(MainFragment.class.getSimpleName())
                .replace(
                        R.id.main_activity_container_FrameLayout,
                        MainFragment.newInstance(),
                        MainFragment.class.getSimpleName())
                .commit();
    }
}
