package com.github.albertosh.adidaseventsapp.ui.main;

import android.util.Pair;

import com.github.albertosh.adidaseventsapp.model.AEvent;
import com.hannesdorfmann.mosby.mvp.lce.MvpLceView;

import java.util.List;

public interface MainView extends MvpLceView<Pair<List<AEvent>, AEvent>> {
}
