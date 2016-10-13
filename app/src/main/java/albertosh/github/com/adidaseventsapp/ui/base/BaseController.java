package albertosh.github.com.adidaseventsapp.ui.base;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bluelinelabs.conductor.Controller;
import com.bluelinelabs.conductor.rxlifecycle.RxController;
import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.hannesdorfmann.mosby.mvp.MvpView;
import com.hannesdorfmann.mosby.mvp.conductor.delegate.MvpConductorDelegateCallback;
import com.hannesdorfmann.mosby.mvp.conductor.delegate.MvpConductorLifecycleListener;

import albertosh.github.com.adidaseventsapp.di.ComponentWithPresenter;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseController
        <V extends MvpView, P extends MvpPresenter<V>, C extends ComponentWithPresenter<P>>
        extends RxController
        implements MvpView, MvpConductorDelegateCallback<V, P> {

    private Unbinder unbinder;
    protected P presenter;
    protected C component;

    {
        addLifecycleListener(new MvpConductorLifecycleListener<V, P>(this));
    }

    protected BaseController() { }
    protected BaseController(Bundle args) {
        super(args);
    }

    @NonNull @Override public P getPresenter() {
        return presenter;
    }

    @Override public void setPresenter(@NonNull P presenter) {
        this.presenter = presenter;
    }

    @NonNull @Override public V getMvpView() {
        return (V) this;
    }

    protected abstract View inflateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container);

    @NonNull
    @Override
    protected View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        checkParentControllerPreconditions(getParentController());
        View view = inflateView(inflater, container);
        unbinder = ButterKnife.bind(this, view);
        onViewBound(view);
        return view;
    }

    protected void checkParentControllerPreconditions(Controller parentController) {}

    protected void onViewBound(@NonNull View view) { }

    @Override
    protected final void onDestroyView(View view) {
        onViewUnbound(view);
        unbinder.unbind();
        unbinder = null;
    }

    protected void onViewUnbound(View view) {}


    @Override @CallSuper
    public void onDestroy() {
        component = null;
    }

    @NonNull
    @Override
    public final P createPresenter() {
        component = createComponent();

        if (component == null)
            throw new IllegalStateException("Component can't be null!");

        return component.presenter();
    }

    @NonNull
    protected abstract C createComponent();
}
