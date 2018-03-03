package com.example.sarah.superservices.RegisterActivityies;

/**
 * Created by Mah on 2/4/2017.
 */

public interface RegisterPresenterProvidedOps {
    void validateCredentials(String name, String phone ,String email, String password);
    void onDestroy();

}
