package a2.sandbox;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
}
