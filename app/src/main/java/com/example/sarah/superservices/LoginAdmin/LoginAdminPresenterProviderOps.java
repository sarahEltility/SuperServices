package com.example.sarah.superservices.LoginAdmin;

/**
 * Created by Mah on 2/16/2017.
 */

public interface LoginAdminPresenterProviderOps {

    void validateCredentials(String username, String password);
    void onDestroy();

}
