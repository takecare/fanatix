package org.vazteixeira.rui.fanatix.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import org.vazteixeira.rui.fanatix.R;
import org.vazteixeira.rui.fanatix.fragment.FriendsFragment;
import org.vazteixeira.rui.fanatix.fragment.MainFragment;
import org.vazteixeira.rui.fanatix.view.FriendsPresenter;


public class MainActivity extends ActionBarActivity implements FriendsPresenter {

    public static final String TAG = "MainActivity";


    // ***
    // LIFECYLE

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

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


    // ***
    //

    @Override
    public void showFriends(String itemId) {

        getSupportFragmentManager()
                .beginTransaction()
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
                .add(
                        R.id.main_activity_container_FrameLayout,
                        MainFragment.newInstance(),
                        MainFragment.class.getSimpleName())
                .commit();
    }
}
