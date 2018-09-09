package a2.sandbox.learning_rx;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * Summary
 * -----------
 * Observable pushes things. A given Observable<T> pushes things of type T through a series of operators
 * until it arrived at an Observer that consumes the items
 *
 * We can also use several operators between Observable and Observer to transform each pushed item or manipulate them
 * in some way. Each operator returns a new Observable derived-off the previous one but reflects that transformation.
 * For example, we can use a map() to turn string emissions into its length(). and each length integer will then be
 * pushed to Observer.
 *
 * You can push not only data but also events. For example, Observable.interval will push a consecutive
 * Long at each specified time interval.
 *
 */
public class ChapterOne extends Fragment {

    public static final String TAG = "ChapterOne";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Observable<String> myStrings = Observable.just("Alpha", "Beta", "Gama",
                "Delta", "Epsilon");

        myStrings.subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.println(s);
            }
        });

        // Composing observables
        Observable<Integer> integerObservable = myStrings.map(new Function<String, Integer>() {
            @Override
            public Integer apply(String s) throws Exception {
                return s.length();
            }
        });

        integerObservable.subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer i) throws Exception {
                System.out.println(i);
            }
        });

        // Events
        Observable<Long> secondInterval = Observable.interval(1, TimeUnit.SECONDS);
        secondInterval.subscribe(s -> System.out.println(s));
    }
}
