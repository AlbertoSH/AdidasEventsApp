package com.github.albertosh.adidaseventsapp.ui.utils;

import android.animation.Animator;
import android.view.View;

public class ViewAnimations {

    public static void hide(View... vs) {
        for (View v : vs) {
            doHide(false, v);
        }
    }

    public static void remove(View... vs) {
        for (View v : vs) {
            doHide(true, v);
        }
    }

    private static void doHide(boolean setVisibilityToGone, View v) {
        v.animate()
                .alpha(0)
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {
                    }

                    @Override
                    public void onAnimationEnd(Animator animator) {
                        v.setVisibility(setVisibilityToGone
                                ? View.GONE
                                : View.INVISIBLE);
                    }

                    @Override
                    public void onAnimationCancel(Animator animator) {
                        v.setAlpha(1);
                        v.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAnimationRepeat(Animator animator) {
                    }
                });
    }

    public static void show(View... vs) {
        for (View v : vs) {
            show(v);
        }
    }

    public static void show(View v) {
        v.animate()
                .alpha(1)
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {
                        v.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAnimationEnd(Animator animator) {
                    }

                    @Override
                    public void onAnimationCancel(Animator animator) {
                        v.setAlpha(0);
                        v.setVisibility(View.GONE);
                    }

                    @Override
                    public void onAnimationRepeat(Animator animator) {
                    }
                });
    }

}
