package albertosh.github.com.adidaseventsapp.di;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;

@PerController
public interface ComponentWithPresenter<T extends MvpPresenter> {

    T presenter();

}
