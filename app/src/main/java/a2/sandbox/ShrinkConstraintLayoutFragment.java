package a2.sandbox;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v4.app.Fragment;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ShrinkConstraintLayoutFragment extends Fragment implements View.OnClickListener {

    public static final String TAG = "ShrinkConstraintLayoutFragment";

    private ConstraintLayout root;

    private ConstraintSet idleSet = new ConstraintSet();

    private ConstraintSet triggeredSet = new ConstraintSet();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = (ConstraintLayout) inflater.inflate(R.layout.fragment_shrinkg_constraint_layout, container, false);
        View helpButton = root.findViewById(R.id.help_button);
        View okButton = root.findViewById(R.id.ok_button);
        helpButton.setOnClickListener(this);
        okButton.setOnClickListener(this);

        // Persist the previous constraints so we can switch between both states
        idleSet.clone(root);

        triggeredSet.clone(root);
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
        TransitionManager.beginDelayedTransition(root);
        switch (v.getId()) {
            case R.id.help_button:
                triggeredSet.applyTo(root);
                break;
            case R.id.ok_button:
                idleSet.applyTo(root);
        }
    }
}
