package com.example.githubsample.login;

import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.android.FragmentEvent;
import com.trello.rxlifecycle2.components.support.RxFragment;
import io.reactivex.SingleTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public abstract class BaseFragment extends RxFragment {

    protected <T> SingleTransformer<T, T> moveToBackground() {
        return upstream -> upstream.compose(bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    protected <T> LifecycleTransformer<T> bindUntilDestroy() {
        return bindUntilEvent(FragmentEvent.DESTROY);
    }
}
