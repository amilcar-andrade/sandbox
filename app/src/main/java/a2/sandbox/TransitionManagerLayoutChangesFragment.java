package a2.sandbox;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

// Watch - https://youtu.be/9Y5cbC5YrOY?t=8m40s
public class TransitionManagerLayoutChangesFragment extends Fragment implements View.OnClickListener{
    public static final String TAG = "TransitionManagerLayoutChangesFragment";

    private View blueView;

    private View greenView;

    private ViewGroup root;

    private boolean isMax = true;

    private int maxHeight;

    private int minHeight;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = (ViewGroup) inflater.inflate(R.layout.fragment_transition_manager_layout_changes, container, false);
        final Resources resources = getResources();
        minHeight = resources.getDimensionPixelSize(R.dimen.color_view_min_height);
        maxHeight = resources.getDimensionPixelSize(R.dimen.color_view_height);
        blueView = root.findViewById(R.id.blue_view);
        greenView = root.findViewById(R.id.green_view);
        View visibilityButton = root.findViewById(R.id.animate_visibility_button);
        visibilityButton.setOnClickListener(this);
        View layoutParamsButton = root.findViewById(R.id.animate_layout_params_button);
        layoutParamsButton.setOnClickListener(this);
        return root;
    }

    public static TransitionManagerLayoutChangesFragment newInstance() {
        return new TransitionManagerLayoutChangesFragment();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.animate_layout_params_button:
                TransitionManager.beginDelayedTransition(root);
                updateLayoutParams();
                isMax = !isMax;
                break;
            case R.id.animate_visibility_button:
                TransitionManager.beginDelayedTransition(root);
                blueView.setVisibility(blueView.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
        }
    }

    private void updateLayoutParams() {
        greenView.getLayoutParams().height = isMax ? minHeight : maxHeight;
        // NOTE: setLayoutParams calls requestLayout() we want to still keep the margin so we just
        // manually update the height property and call requestLayout()
        greenView.requestLayout();
    }
}
