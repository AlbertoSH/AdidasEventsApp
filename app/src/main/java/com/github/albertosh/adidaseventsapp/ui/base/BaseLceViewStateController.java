package com.github.albertosh.adidaseventsapp.ui.base;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.CheckResult;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bluelinelabs.conductor.Controller;
import com.bluelinelabs.conductor.rxlifecycle.ControllerEvent;
import com.bluelinelabs.conductor.rxlifecycle.ControllerLifecycleSubjectHelper;
import com.bluelinelabs.conductor.rxlifecycle.RxControllerLifecycle;
import com.github.albertosh.adidaseventsapp.AdidasApp;
import com.github.albertosh.adidaseventsapp.di.AppComponent;
import com.github.albertosh.adidaseventsapp.di.ComponentWithPresenter;
import com.hannesdorfmann.mosby.conductor.viewstate.lce.MvpLceViewStateController;
import com.hannesdorfmann.mosby.mvp.lce.MvpLceView;
import com.trello.rxlifecycle.LifecycleProvider;
import com.trello.rxlifecycle.LifecycleTransformer;
import com.trello.rxlifecycle.RxLifecycle;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.Observable;
import rx.subjects.BehaviorSubject;
import rx.subscriptions.CompositeSubscription;

public abstract class BaseLceViewStateController
        <CV extends View, M, V extends MvpLceView<M>, P extends IBasePresenter<V>, C extends ComponentWithPresenter<P>>
        extends MvpLceViewStateController<CV, M, V, P>
        implements LifecycleProvider<ControllerEvent> {

    private final BehaviorSubject<ControllerEvent> lifecycleSubject;

    protected C component;
    private Unbinder unbinder;
    private CompositeSubscription viewBindings;
    protected M data;

    public BaseLceViewStateController() {
        this(null);
    }

    public BaseLceViewStateController(Bundle args) {
        super(args);
        lifecycleSubject = ControllerLifecycleSubjectHelper.create(this);
    }


    protected final AdidasApp getApplication() {
        return ((AdidasApp) getActivity().getApplication());
    }

    protected final AppComponent getApplicationComponent() {
        return getApplication().getAppComponent();
    }

    @NonNull
    @Override
    protected final View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        checkParentControllerPreconditions(getParentController());
        component = checkPresenter();
        if (component == null)
            throw new IllegalStateException("Component can't be null!");
        View view = inflater.inflate(getLayout(), container, false);
        unbinder = ButterKnife.bind(this, view);
        viewBindings = new CompositeSubscription();
        onViewBound(view);
        if (data != null)
            setData(data);
        return view;
    }

    private C checkPresenter() {
        if (component == null)
            component = createComponent();
        return component;
    }

    protected void checkParentControllerPreconditions(Controller parentController) {}

    @LayoutRes
    protected abstract int getLayout();

    protected void onViewBound(@NonNull View view) {}

    @Override
    @CallSuper
    protected final void onDestroyView(View view) {
        onViewUnbound(view);
        viewBindings.unsubscribe();
        viewBindings = null;
        unbinder.unbind();
        unbinder = null;
    }

    protected void onViewUnbound(View view) {
    }

    @Override
    public void onDestroy() {
        component.presenter().destroy();
        component = null;
    }

    @NonNull
    @Override
    public final P createPresenter() {
        return component.presenter();
    }

    @NonNull
    protected abstract C createComponent();

    @Override
    public M getData() {
        return data;
    }

    @Override
    public void setData(M data) {
        this.data = data;
        updateUIElements(data);
    }

    protected abstract void updateUIElements(M data);

    @Override
    public void onViewStateInstanceRestored(boolean instanceStateRetained) {
        component.presenter().onViewStateInstanceRestored(instanceStateRetained);
    }

    @Override
    @NonNull
    @CheckResult
    public final Observable<ControllerEvent> lifecycle() {
        return lifecycleSubject.asObservable();
    }

    @Override
    @NonNull
    @CheckResult
    public final <T> LifecycleTransformer<T> bindUntilEvent(@NonNull ControllerEvent event) {
        return RxLifecycle.bindUntilEvent(lifecycleSubject, event);
    }

    @Override
    @NonNull
    @CheckResult
    public final <T> LifecycleTransformer<T> bindToLifecycle() {
        return RxControllerLifecycle.bindController(lifecycleSubject);
    }
}
