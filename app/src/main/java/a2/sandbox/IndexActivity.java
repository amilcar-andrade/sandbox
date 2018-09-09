package a2.sandbox;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import a2.sandbox.kotlin_action.KotlinActivity;
import a2.sandbox.learning_rx.RxJavaIndexActivity;
import a2.sandbox.transitions_no_frag_act.TransitionsWithoutActivitiesFragments;

public class IndexActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
    }

    public void startTransitionWithoutActFragActivity(View view) {
        startActivity(new Intent(this, TransitionsWithoutActivitiesFragments.class));
    }

    public void startLearningRx(View view) {
        startActivity(new Intent(this, RxJavaIndexActivity.class));
    }

    public void startKotlinInAction(View view) {
        startActivity(new Intent(this, KotlinActivity.class));
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
