package com.example.sarah.superservices.LoginServiceProvider;

/**
 * Created by Mah on 2/16/2017.
 */

public interface LoginServicePresenterProviderOps {

    void validateCredentials(String username, String password);
    void onDestroy();

}
