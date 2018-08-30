package a2.sandbox.transitions_no_frag_act;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v4.app.Fragment;
import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.transition.ChangeBounds;
import android.transition.Fade;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import a2.sandbox.R;

public class ShrinkConstraintLayoutFragment extends Fragment implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    public static final String TAG = "ShrinkConstraintLayoutFragment";

    private ConstraintLayout constraintLayout;

    private ConstraintSet idleSet = new ConstraintSet();

    private ConstraintSet triggeredSet = new ConstraintSet();

    private boolean useAutoTransition;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_shrinkg_constraint_layout, container, false);
        CheckBox checkBox = root.findViewById(R.id.checkbox);
        checkBox.setOnCheckedChangeListener(this);
        constraintLayout = root.findViewById(R.id.constraint_layout);
        View helpButton = constraintLayout.findViewById(R.id.help_button);
        View okButton = constraintLayout.findViewById(R.id.ok_button);
        helpButton.setOnClickListener(this);
        okButton.setOnClickListener(this);

        // Persist the previous constraints so we can switch between both states
        idleSet.clone(constraintLayout);

        triggeredSet.clone(constraintLayout);
        triggeredSet.setVisibility(R.id.title, View.GONE);
        triggeredSet.setVisibility(R.id.instructions, View.GONE);
        triggeredSet.setVisibility(R.id.help_button, View.GONE);
        triggeredSet.setVisibility(R.id.ok_button, View.VISIBLE);
        triggeredSet.connect(R.id.white_background, ConstraintSet.TOP, R.id.guideline, ConstraintSet.TOP);
        return root;
    }

    public static ShrinkConstraintLayoutFragment newInstance() {
        return new ShrinkConstraintLayoutFragment();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.help_button:
                beginDelayedTransition(false);
                triggeredSet.applyTo(constraintLayout);
                break;
            case R.id.ok_button:
                beginDelayedTransition(true);
                idleSet.applyTo(constraintLayout);
        }
    }

    private void beginDelayedTransition(boolean playReverse) {
        if (useAutoTransition) {
            TransitionManager.beginDelayedTransition(constraintLayout);
            return;
        }
        TransitionManager.beginDelayedTransition(constraintLayout, createTransition(playReverse));
    }

    // LATER: Create the transition only once
    private Transition createTransition(boolean playReverse) {
       return new TransitionSet()
                .addTransition(new Fade()
                        .addTarget(R.id.title)
                        .addTarget(R.id.instructions)
                        .setDuration(playReverse ? 400 : 100))
                .addTransition(new Fade()
                        .addTarget(R.id.help_button)
                        .addTarget(R.id.ok_button)
                        .setDuration(playReverse ? 100 : 400))
                .addTransition(new ChangeBounds()
                        .setDuration(playReverse ? 100 : 400)
                        .setInterpolator(
                                playReverse ?
                                new FastOutLinearInInterpolator() :
                                new FastOutSlowInInterpolator()));
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        useAutoTransition = isChecked;
    }
}
