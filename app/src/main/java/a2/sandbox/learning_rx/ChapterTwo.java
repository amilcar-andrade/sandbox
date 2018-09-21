package a2.sandbox.learning_rx;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.observables.ConnectableObservable;


/**
 * SUMMARY
 * -----------
 *
 * Observable.create()
 * --------------------
 *
 * An Observable works through its onNext, onComplete, and onError() calls. An Observable sequentially passes items
 * down a chain to an Observer. At the highest level, an Observable works by passing 3 types of events.
 * The 3 methods are abstract methods in the Observer type
 *
 * The Observable.create() factory allows us to create an Observable by providing a lambda receiving an Observable emitter.
 *
 * NOTE: In Rx1 make sure to use fromEmitter instead of create because create is for advance cases in Rx1
 *
 * NOTE: The Observable contract dictates that emissions must be passed sequentially and one at a time. Emissions cannot be
 * passed by an Observable concurrently or in parallel
 *
 * The onNext, onComplete, and onError do not push directly to the final Observer, they can also push to an operator serving
 * as the next step in the chain.
 *
 * In RxJava2 observables no longer support emitting null values. You will immediately get a non-null exception if you
 * create an Observable that attempts to emit a null value.
 *
 * Observable.just()
 * -------------------
 * It is a a factory method that takes up to 10 items as parameters
 *
 * Observable.fromIterable()
 * ---------------------------
 * We can use this factory to emit items from any Iterable type, such as a List. It also call onNext for each element and then call
 * onComplete after the iteration is complete.
 *
 * Observer interface
 * -------------------
 *
 * The onNext, onComplete, and onError methods actually define the Observer type (plus the onSubscribe())
 * When we talk about Observer we are often talking about the final Observer at the end of the Observable chain
 * that consumes the emissions. Bue each operator, such as map and filter, also implements Observer internally
 * In RxJava1, the Subscriber essentially became an Observer in RxJava2
 * In RxJava 2, a Subscriber only exists when talking about Flowables
 *
 * When you call the subscribe() method on an Observable, an Observer is used to consume these 3 events by implementing its methods
 * We can implement an Observer and pass an instance of it to the subscribe() method
 *
 * There are a couple of overloaded methods for subscribe() it can take the 3(next, error, complete) actions,
 * or 2 (next, error) or just 1 (next). It is a bad idea to not implement the onError action because if there
 * is no handling of the error will get propagated and no one will handle it
 *
 * NOTE: Most of the subscribe() overload variants return a Disposable, a Disposable allow us to disconnect an Observable from an
 * Observer so emissions are terminated early, which is critical for infinite or long running Observables.
 *
 *
 * Cold vs Hot Observables
 * ----------------------------
 *
 * Cold Observables will replay the emissions to each Observer, ensuring that all Observers get all the data.
 * (e.g. CD than can be replayed to each lister). Each Observer get a separate stream. This is the typical behavior for a cold Observable.
 * Even if an Observer transforms its emissions with operators, it will still get its own stream of emissions.
 *
 * Observables sources that emit finite datasets are usually cold
 * Many Observables emitting from finite data sources such as databases , text files, or JSON are cold.
 *
 * Hot Observables is more like a radio station. It broadcasts the same emissions to all Observers at the same time.
 * If an Observer subscribe() to a hot Observable, receives some emission sand then another Observer comes in afterwards,
 * that second Observer will have missed the first emissions. Like a radio station if you tune in too late,
 * you will have missed that song
 *
 * on Android a UI event can be represented as a hot Observable.  There are prime examples of hot Observables but you can
 * also use hot Observable to reflect server requests. Hot Observables are likely to be infinite but they do not have to be.
 * They just have to share emissions to all Observers simultaneously and not reply missed emissions for tardy Observers
 *
 *
 * Connectable Observable
 * ----------------------------
 * A ConnectableObservable will take any Observable, even if its cold, and make it hot so that all emissions are played
 * to all Observers at once. To do this conversion, you simply need to call publish() on any Observable, and it will
 * create a ConnectableObservable. But subscribing will not start the emissions. You need to call its connect() method to start
 * firing the emissions.
 *
 * Using ConnectableObservable to force each emission to go to all Observer simultaneously is known as multicasting.
 *
 * You may also use it to force the operators upstream to use a single stream instance even if there are
 * multiple Observers downstream
 *
 *
 * Factory methods
 * --------------------------
 *
 * Observable.range() -> emit a consecutive range of integers it emits a number from a start value and increment each emission until
 * the specified count is reached.
 *
 * Observable.interval()  -> emits a consecutive long emission at every specified time interval. For example we can have an
 * Observable that can emit every second. e.g. Observable.interval(1, TimeUnit.SECONDS);
 * This Observable will emit infinitely at the specified interval. However because it operates on a timer, it needs to run on
 * a separate thread and will run on the computation Scheduler by default. This Observable is a cold observable but we can make
 * it hot by using a ConnectableObservable
 *
 * Observable.future() -> Uses Futures from Java
 *
 * Observable.empty() -> This observable emits nothing and calls onComplete()
 * Empty Observables are common to represent empty datasets. They can also be the result of a filter() operator when nothing matches
 * the predicate/conditon
 * An empty observable is essentially RxJAva's concept of null. It is the absence of a value.
 *
 * Observable.never() -> A close cousin of Observable.empty(). This Observable never calls onComplete, so it leaves the observers
 * waiting for emissions but never actually giving any. NOTE: This is primarily used for testing!
 *
 * Observable.error(e: Exception) -> It calls onError() using the exception that you used in the constructor
 *
 * Observable.defer() -> It creates a separate state for each Observer. Sometimes you might want to capture something that has changed
 * in the parameters of the Observable so when you need to update those references when a new Observer subscribes to the source.
 * To remedy this problem of Observables sources not capturing state changes, you can create a fresh Observable for each
 * subscription. Because it creates a new Observable each time, it will reflect any changes driving its parameters
 *
 * Observable.fromCallable() -> Lazily executed the callable(function/expression) when someone subscribes to the observable.
 * For example if you do Observable.just(1/0) an exception is going to get thrown outside of the stream because (1/0) gets evaluated first
 * This Observable accepts a Callable and it will emit any error that occurs down to the Observer
 *
 * RxJava 2 TYPES (Single, Completable, and Maybe)
 * ------------------------------------------------------
 *
 * Single
 *
 * The Single type will only emit one item. The onNext() and onComplete() are consolidated in just one method which is onSuccess()
 * It only accepts one emission.
 * NOTE: Certain types of Rx will return a Single. For example the first() operator returns a Single because it will only emit the first item.
 *
 * Maybe
 *
 * If there are 0 or 1 emissions, Maybe is the right type to use
 * Maybe is similar to Single except that it allows no emission to occur at all. The onNext() method is called onSuccess() with a T type
 * It will pass the possible emission to onSuccess(), and in either case, it will call onComplete().
 *
 * Note that {@code onSuccess}, {@code onError} and {@code onComplete} are mutually exclusive events
 *
 * Completable
 *
 * This type is simple concerned with an action being executed, it does not receive any emissions. It does not have onNext() or onSuccess()
 * but it does have onError() and onComplete()
 *
 * Some common factories are Completable.complete or Completable.fromRunnable or fromCallable
 *
 * Disposing/Disposable
 * ---------------------------
 *
 * The Disposable is a link between an Observable and an active Observer, you can call the dispose method to stop emissions and dispose of all resources
 * used for that Observer.
 *
 * Finite Observables that call onComplete will typically dispose of themselves safely when they are done an explicit disposal is necessary in order to
 * prevent memory leaks (rotation on activities)
 *
 * If you implement your own Observer you can the disposable inside onNext, onComplete or onError so you can decide what is right behaviour to do in those cases.
 * Passing an Observer to the subscribe() will be void and not return a Disposable since it is assumed that the Observer will handle it. If you do not want to handle
 * the Disposable you can extend ResourceObserver as your Observe, which handles the disposable. Use subscribeWith() to achieve this.
 *
 * CompositeDisposable -> It implements Disposable, but internally holds a collection of disposables, which you can add to and then dispose all at once
 *
 * If you create an Observable using Observable.create() and it is a long running or infinite Observable, you should check the isDisposed() method of ObservableEmitter to see
 * if you should keep sending emissions or not.
 *
 * The ObservableEmitter has two methods setCancellable and setDisposable to be able to clean up extra resources or listeners
 *
 */
public class ChapterTwo extends Fragment {

    public static final String TAG = "ChapterTwo";
    private static int start = 1;
    private static int count = 5;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Observable<String> sourceLambda = Observable.create(emitter -> {
            emitter.onNext("Alpha");
            emitter.onNext("Beta");
            emitter.onNext("Gamma");
            emitter.onNext("Delta");
            emitter.onComplete();
        });

        Observable<String> source = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                try {
                    emitter.onNext("A");
                    emitter.onNext("B");
                    emitter.onNext("C");
                    emitter.onNext("D");
                    emitter.onComplete();
                } catch (Throwable e) { // Assume an exception happens when emitting onNext events
                    emitter.onError(e);
                }

            }
        });

        source.subscribe(s -> System.out.println("Received: " + s));
        sourceLambda.subscribe(s -> System.out.println("Received from lambda: " + s));

        // Pushing to a different Observable and not to the final Observer

        // The map and filter operators between the source Observable and Observer, onNext will handle each item to the map operator.
        // Internally, it will act as an intermediary Observer and convert each string to its length. The, in turn will call onNext on filter
        // to pass that integer and the lambda condition i >= 5 will suppress emissions that fail to be at least five characters in length
        // Finally the filter operator will call onNext to hand each item to the final Observer

        // Since operators such as map and filter return a new Observable (which internally use Observer implementations to receive emissions),
        // we can chain all our returned Observables with the next operator rather than unnecessarily saving each one to a varibale
        Observable<Integer> lengths = sourceLambda.map(s -> s.length());
        Observable<Integer> filtered = lengths.filter(i -> i >= 5);
        filtered.subscribe(s -> System.out.println("Received from filtered: " + s));

        // Chain observables
        Observable<String> chain = Observable.create(emitter -> {
            emitter.onNext("Alpha");
            emitter.onNext("Beta");
            emitter.onNext("Gamma");
            emitter.onNext("Delta");
            emitter.onComplete();
        });

        chain.map(String::length)
                .filter(i -> i >= 5)
                .subscribe(i -> System.out.println("Chain received: " + i));


        // From iterable
        List<String> items = Arrays.asList("Alpha", "Beta", "Gamma", "Delta", "Epsilon");
        Observable<String> fromIterable = Observable.fromIterable(items);
        fromIterable.map(s -> s.length())
                .filter(i -> i >= 5)
                .subscribe(i -> System.out.println("Chain received: " + i),
                        throwable -> throwable.printStackTrace());


        // Implementing the Observer contract!
        Observable<String> sourceWithObserver = Observable.just("A", "B", "C");
        Observer<Integer> myObserver = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                // no-op for now
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("Received using observer: " + integer);
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onComplete() {
                System.out.println("Done");
            }
        };

        sourceWithObserver.map(s -> s.length())
                .filter(i -> i >= 5)
                .subscribe(myObserver);

        // ConnectableObservable
        ConnectableObservable<String> publish = Observable.just("Ab", "B", "C", "D", "E").publish();

        // set up observer 1
        publish.subscribe(s -> System.out.println("Observer 1: " + s));

        // set up observer 2
        publish.map(String::length).subscribe(i -> System.out.println("Observer 2: " + i));

        // Connect
        publish.connect();

        // Factories
        // The two arguments for Observable.range are not lower/upper bounds. The first argument is just the starting value
        Observable.range(1, 10).subscribe(i -> System.out.println("Observer for range: " + i));

        Observable.empty().subscribe(
                System.out::println,
                Throwable::printStackTrace,
                () -> System.out.println("Empty Observable - Done"));

        // Defer
        Observable<Integer> sourceDeffer = Observable.defer(() ->
                Observable.range(start, count));
        sourceDeffer.subscribe(integer -> System.out.println("Observer 1: " + integer));

        // Change count
        count = 10;
        sourceDeffer.subscribe(integer -> System.out.println("Observer 2: " + integer));

        // From callable
        Observable.fromCallable(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return 1/0;
            }
        }).subscribe(integer -> {}, Throwable::printStackTrace);

        // Maybe
        Maybe.just(100).subscribe(
                s -> System.out.println("Emissions of maybe just: " + s),
                Throwable::printStackTrace,
                () -> System.out.println("Maybe is exclusive this is not going to get printed!"));

        // Maybe empty
        Maybe.empty().subscribe(
                s -> System.out.println("This is not going to get printed, because there are no emissions it just completes: " + s),
                Throwable::printStackTrace,
                () -> System.out.println("Maybe empty done!"));

        // My own Observer
        Observer<Integer> observer = new Observer<Integer>() {
            private Disposable d;
            @Override
            public void onSubscribe(Disposable d) {
                this.d = d;
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println(integer);
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onComplete() {
                // done
            }
        };

        // Completable
        Completable.fromRunnable(new Runnable() {
            @Override
            public void run() {
                runThisMethod("Completable - new Runnable");
            }
        }).subscribe(() -> System.out.println("onComplete()"));

        // new Acton allows you to throw a checked Exception!
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                runThisMethod("Completable - new Action");
            }
        }).subscribe(() -> System.out.println("onComplete()"));

    }

    private void runThisMethod(@NonNull String from) {
        System.out.println("Run this method inside of a Runnable and using a: " + from);
    }
}
