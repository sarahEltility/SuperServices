package com.example.sarah.superservices.Login;

import android.content.Context;

/**
 * Created by Mah on 2/16/2017.
 */

public interface LoginViewOps {

    Context getContext();

    void showProgress();

    void hideProgress();

    void setUsernameError(String s);

    void setPasswordError(String s);

    void setEmailError(String s);

    void setPhoneError(String s);

    void setNameError(String s);
}
