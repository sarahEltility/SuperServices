package com.example.sarah.superservices.RegisterActivityies;

import android.content.Context;

/**
 * Created by Mah on 2/4/2017.
 */

public interface RegisterViewOps {

    Context getContext();

    void showProgress();

    void hideProgress();

    void setPasswordError(String s);

    void setEmailError(String s);

    void setNameError(String s);

    boolean isPasswordValid(String password);

    boolean isEmailValid(CharSequence email);

    void navigateToHome();
}
