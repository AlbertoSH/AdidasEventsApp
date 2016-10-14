package com.github.albertosh.adidaseventsapp.ui.login;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.github.albertosh.adidaseventsapp.AdidasApp;
import com.github.albertosh.adidaseventsapp.BuildConfig;
import com.github.albertosh.adidaseventsapp.R;
import com.github.albertosh.adidaseventsapp.custom.CustomProperties;
import com.github.albertosh.adidaseventsapp.di.login.DaggerLoginComponent;
import com.github.albertosh.adidaseventsapp.di.login.LoginComponent;
import com.github.albertosh.adidaseventsapp.ui.utils.ViewAnimations;
import com.hannesdorfmann.mosby.mvp.MvpActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity
        extends MvpActivity<LoginView, ILoginPresenter>
        implements LoginView {

    private final static int PERMISSION_REQUEST_CODE = 1001;
    private LoginComponent loginComponent;

    @BindView(R.id.layLogin)
    ViewGroup layLogin;
    @BindView(R.id.edtEmail)
    EditText edtEmail;
    @BindView(R.id.edtPassword)
    EditText edtPassword;
    @BindView(R.id.btnLogin)
    Button btnLogin;
    @BindView(R.id.btnForgotPassword)
    Button btnForgotPassword;
    @BindView(R.id.btnSignUp)
    Button btnSignup;
    @BindView(R.id.txtError)
    TextView txtError;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    public static void start(Activity activity) {
        Intent intent = new Intent(activity.getApplicationContext(), LoginActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        if (BuildConfig.DEBUG) {
            edtEmail.setText("demo@demo.com");
            edtPassword.setText("qwerty");
        }

        CustomProperties customProperties = loginComponent.customProperties();
        customProperties.applyCustomProperties("edtLoginEmail", edtEmail);
        customProperties.applyCustomProperties("edtLoginPassword", edtPassword);
        customProperties.applyCustomProperties("btnLoginLogin", btnLogin);
        customProperties.applyCustomProperties("btnLoginForgotPassword", btnForgotPassword);
        customProperties.applyCustomProperties("btnLoginSignUp", btnSignup);
        customProperties.applyCustomProperties("txtLoginError", txtError);
        customProperties.applyCustomProperties("prgLoginProgressBar", progressBar);
    }

    @Override
    public Object onRetainNonMosbyCustomNonConfigurationInstance() {
        return loginComponent;
    }

    @NonNull
    @Override
    public ILoginPresenter createPresenter() {
        loginComponent = (LoginComponent) getNonMosbyLastCustomNonConfigurationInstance();
        if (loginComponent == null)
            loginComponent = DaggerLoginComponent.builder()
                    .appComponent(((AdidasApp)getApplication()).getAppComponent())
                    .build();

        return loginComponent.presenter();
    }

    @OnClick(R.id.btnLogin)
    public void onLoginClick() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.GET_ACCOUNTS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.GET_ACCOUNTS}, PERMISSION_REQUEST_CODE);
        } else {
            presenter.doLogin();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_REQUEST_CODE) {
            // Only 1 permission requested...
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                presenter.doLogin();
            } else {
                Toast.makeText(this, "This app needs account management permission in order add your account", Toast.LENGTH_SHORT).show();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @OnClick(R.id.btnForgotPassword)
    public void onForgotPasswordClick() {
        presenter.forgotPassword();
    }

    @OnClick(R.id.btnSignUp)
    public void onSignupClick() {
        presenter.doSignup();
    }

    @Override
    public void showLoading() {
        ViewAnimations.hide(layLogin);
        ViewAnimations.show(progressBar);
    }

    @Override
    public void dismissLoading() {
        ViewAnimations.hide(progressBar);
        ViewAnimations.show(layLogin);
    }

    @Override
    public void loginSuccessful() {
        finish();
    }

    @Override
    public void showInvalidUserPassword() {
        txtError.setText(R.string.login_error_invalidUserOrPassword);
    }

    @Override
    public void showNetworkError() {
        txtError.setText(R.string.login_error_network);
    }

    @Override
    public String getEmail() {
        return edtEmail.getText().toString();
    }

    @Override
    public String getPass() {
        return edtPassword.getText().toString();
    }

    @Override
    public void showEmailIsEmptyError() {
        edtEmail.setError(getString(R.string.login_error_emptyEmail));
    }

    @Override
    public void showPasswordIsEmptyError() {
        edtPassword.setError(getString(R.string.login_error_emptyPassword));
    }
}
