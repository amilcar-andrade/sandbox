package a2.sandbox.learning_rx;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import a2.sandbox.R;

public class RxJavaIndexActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning_rx);
    }

    public void showChapters(View view) {
        switch (view.getId()) {
            case R.id.chapter_one:
                addInvisibleFragment(this, new ChapterOne(), ChapterOne.TAG);
                break;
        }
    }

    public static void addInvisibleFragment(@NonNull FragmentActivity activity,
                                       @NonNull Fragment fragment,
                                       @NonNull String fragmentTag) {
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        Fragment currentFragment = fragmentManager.findFragmentByTag(fragmentTag);
        if (currentFragment != null) {
            return;
        }
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction().add(fragment, fragmentTag);
        fragmentTransaction.commit();
    }
}
