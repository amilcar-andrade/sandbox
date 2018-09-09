package a2.sandbox.transitions_no_frag_act;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import static a2.sandbox.IndexActivity.replaceFragment;
import a2.sandbox.R;

public class TransitionsWithoutActivitiesFragments extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transitions_without_act_frag);
    }

    public void showAnimateLayoutChangesFragment(View ignored) {
        replaceFragment(this, R.id.container, AnimateLayoutChangesFragment.newInstance(),
                AnimateLayoutChangesFragment.TAG, false, true);
    }

    public void showTransitionManagerLayoutChangesFragment(View ignored) {
        replaceFragment(this, R.id.container, TransitionManagerLayoutChangesFragment.newInstance(),
                TransitionManagerLayoutChangesFragment.TAG, false, true);
    }

    public void showShrinkLinearLayoutFragment(View ignored) {
        replaceFragment(this, R.id.container, ShrinkLinearLayoutFragment.newInstance(),
                ShrinkLinearLayoutFragment.TAG, false, true);
    }

    public void showShrinkConstraintLayoutFragment(View ignored) {
        replaceFragment(this, R.id.container, ShrinkConstraintLayoutFragment.newInstance(),
                ShrinkConstraintLayoutFragment.TAG, false, true);
    }
}
