package com.example.sarah.superservices.Login;

/**
 * Created by Mah on 2/16/2017.
 */

public interface LoginPresenterProviderOps {

    void validateCredentials(String username, String password);
    void onDestroy();

}
