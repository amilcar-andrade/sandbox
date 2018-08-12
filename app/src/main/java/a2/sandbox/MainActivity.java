package a2.sandbox;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showAnimateLayoutChangesFragment(View ignored) {
        replaceFragment(this, R.id.container, AnimateLayoutChangesFragment.newInstance(),
                AnimateLayoutChangesFragment.TAG, false, true);
    }


    public static void replaceFragment(@NonNull FragmentActivity activity,
                                       int containerViewId,
                                       @NonNull Fragment fragment,
                                       @NonNull String fragmentTag,
                                       boolean forceReplacement,
                                       boolean addToBackStack) {
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        Fragment newFragment = fragmentManager.findFragmentByTag(fragmentTag);
        if (newFragment == null || forceReplacement) {
            newFragment = fragment;
        }
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction().replace(containerViewId, newFragment, fragmentTag);
        if (addToBackStack) {
            fragmentTransaction.addToBackStack(fragmentTag);
        }
        fragmentTransaction.commit();
    }
}
