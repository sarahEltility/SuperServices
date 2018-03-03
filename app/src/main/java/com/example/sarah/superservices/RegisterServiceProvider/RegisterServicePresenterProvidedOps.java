package com.example.sarah.superservices.RegisterServiceProvider;

/**
 * Created by Mah on 2/4/2017.
 */

public interface RegisterServicePresenterProvidedOps {
    void validateCredentials(String name, String email, String phone,String password,String age,String service);
    void onDestroy();

}
