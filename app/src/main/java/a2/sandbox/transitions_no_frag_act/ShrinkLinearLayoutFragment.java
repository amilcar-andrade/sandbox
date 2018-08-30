package a2.sandbox.transitions_no_frag_act;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import a2.sandbox.R;

public class ShrinkLinearLayoutFragment extends Fragment implements View.OnClickListener {

    public static final String TAG = "ShrinkLinearLayoutFragment";

    private View helpButton;

    private View title;

    private View instructions;

    private View okButton;

    private LinearLayout linearContainer;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_shrink_linear_layout, container, false);
        linearContainer = rootView.findViewById(R.id.linear_container);
        helpButton = linearContainer.findViewById(R.id.help_button);
        title = linearContainer.findViewById(R.id.title);
        instructions = linearContainer.findViewById(R.id.instructions);
        okButton = linearContainer.findViewById(R.id.ok_button);
        helpButton.setOnClickListener(this);
        okButton.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onClick(View v) {
        TransitionManager.beginDelayedTransition(linearContainer);
        ViewGroup.LayoutParams layoutParams = linearContainer.getLayoutParams();

        switch (v.getId()) {
            case R.id.help_button:
                layoutParams.height = LinearLayout.LayoutParams.WRAP_CONTENT;
                title.setVisibility(View.GONE);
                instructions.setVisibility(View.GONE);
                helpButton.setVisibility(View.GONE);
                okButton.setVisibility(View.VISIBLE);
                break;

            case R.id.ok_button:
                layoutParams.height = LinearLayout.LayoutParams.MATCH_PARENT;
                title.setVisibility(View.VISIBLE);
                instructions.setVisibility(View.VISIBLE);
                helpButton.setVisibility(View.VISIBLE);
                okButton.setVisibility(View.GONE);
        }

    }

    public static ShrinkLinearLayoutFragment newInstance() {
        return new ShrinkLinearLayoutFragment();
    }
}
