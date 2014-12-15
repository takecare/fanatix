package org.vazteixeira.rui.fanatix.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.ObjectAnimator;

import org.vazteixeira.rui.fanatix.R;
import org.vazteixeira.rui.fanatix.fragment.FriendsFragment;
import org.vazteixeira.rui.fanatix.fragment.MainFragment;
import org.vazteixeira.rui.fanatix.fragment.ResultFragment;
import org.vazteixeira.rui.fanatix.model.Friend;
import org.vazteixeira.rui.fanatix.view.FragmentChangedListener;
import org.vazteixeira.rui.fanatix.view.FriendsPresenter;
import org.vazteixeira.rui.fanatix.view.LoadingPresenter;
import org.vazteixeira.rui.fanatix.view.ResultsPresenter;
import org.vazteixeira.rui.fanatix.view.StubAnimationListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import de.keyboardsurfer.android.widget.crouton.Crouton;


public class MainActivity extends ActionBarActivity implements FriendsPresenter, LoadingPresenter, ResultsPresenter,
        FragmentChangedListener {

    public static final String TAG = "MainActivity";
    private static final String LAYOUT_ID_TAG = "loadingRelativeLayout";

    @InjectView(R.id.main_activity_loading_RelativeLayout)  RelativeLayout loadingRelativeLayout;
    @InjectView(R.id.main_activity_loading_ProgressBar)     ProgressBar loadingProgressBar;

    private Fragment mCurrentFragment;

    // ********
    // LIFECYLE

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {

            mCurrentFragment = getSupportFragmentManager().getFragment(savedInstanceState, TAG);
        }
        else {

            mCurrentFragment = MainFragment.newInstance();
        }

        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(
                        R.id.main_activity_container_FrameLayout,
                        mCurrentFragment)
                .commit();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Crouton.cancelAllCroutons();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        getSupportFragmentManager().putFragment(outState, TAG, mCurrentFragment);
    }

    // ****
    // MENU

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_about) {

            Toast.makeText(this, R.string.rmvt, Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // *******
    // HELPERS

    private void showFragment(Fragment fragment) {

        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(fragment.getClass().getSimpleName())
                .replace(
                        R.id.main_activity_container_FrameLayout,
                        fragment,
                        fragment.getClass().getSimpleName())
                .commit();
    }

    // ****************
    // FriendsPresenter

    @Override
    public void showFriends(String itemId) {

        showFragment(FriendsFragment.newInstance(itemId));
    }

    @Override
    public void hideFriends() {

        showFragment(MainFragment.newInstance());
    }

    // ****************
    // LoadingPresenter

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


    // ****************
    // ResultsPresenter

    @Override
    public void showResults(List<Friend> selectedFriendsList) {

        showFragment(ResultFragment.newInstance((ArrayList<Friend>) selectedFriendsList));
    }

    @Override
    public void hideResults() {

        showFragment(MainFragment.newInstance());
    }


    // ***********************
    // FragmentChangedListener

    @Override
    public void fragmentChanged(Fragment fragment) {

        mCurrentFragment = fragment;
    }

}
