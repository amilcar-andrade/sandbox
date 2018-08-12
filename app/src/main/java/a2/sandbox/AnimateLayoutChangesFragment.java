package a2.sandbox;

import android.animation.LayoutTransition;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

// Watch - https://youtu.be/55wLsaWpQ4g and https://youtu.be/9Y5cbC5YrOY?t=6m54s
public class AnimateLayoutChangesFragment extends Fragment implements View.OnClickListener{
    public static final String TAG = "AnimateLayoutChangesFragment";

    private View blueView;

    private View greenView;

    private boolean isMax = true;

    private int maxHeight;

    private int minHeight;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_animate_layout_changes, container, false);
        final Resources resources = getResources();
        minHeight = resources.getDimensionPixelSize(R.dimen.color_view_min_height);
        maxHeight = resources.getDimensionPixelSize(R.dimen.color_view_height);
        blueView = view.findViewById(R.id.blue_view);
        greenView = view.findViewById(R.id.green_view);
        View visibilityButton = view.findViewById(R.id.animate_visibility_button);
        visibilityButton.setOnClickListener(this);
        View layoutParamsButton = view.findViewById(R.id.animate_layout_params_button);
        layoutParamsButton.setOnClickListener(this);

        final LayoutTransition layoutTransition = view.getLayoutTransition();
        layoutTransition.enableTransitionType(LayoutTransition.CHANGING);
        return view;
    }

    public static AnimateLayoutChangesFragment newInstance() {
        return new AnimateLayoutChangesFragment();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.animate_layout_params_button:
                greenView.getLayoutParams().height = isMax ? minHeight : maxHeight;
                greenView.requestLayout();
                isMax = !isMax;
                break;
            case R.id.animate_visibility_button:
                blueView.setVisibility(blueView.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
        }
    }
}
