package com.example.sarah.superservices.LoginAdmin;


import com.example.sarah.superservices.R;
import com.example.sarah.superservices.model.Connector;
import com.example.sarah.superservices.model.Users;

/**
 * Created by Mah on 2/16/2017.
 */

public class LoginAdminPresenterImpl implements LoginAdminPresenterProviderOps {

    private LoginAdminViewOps loginViewOps;

    public LoginAdminPresenterImpl(LoginAdminViewOps loginViewOps) {
        this.loginViewOps = loginViewOps;
    }

    @Override
    public void validateCredentials(String username, String password) {
        if (loginViewOps != null) {
            loginViewOps.showProgress();
        }


        if(username.length()==0){loginViewOps.setUsernameError(loginViewOps.getContext().getString(R.string.username_error));loginViewOps.hideProgress();}

        if(password.length()==0){loginViewOps.setPasswordError(loginViewOps.getContext().getString(R.string.password_error));loginViewOps.hideProgress();}





        Connector connector = new Connector(loginViewOps.getContext(), loginViewOps);

        Users u1 = new Users();

        u1.setEmail(username);
        u1.setPassword(password);


        connector.LoginAdmin(u1);


    }

    @Override
    public void onDestroy() {
        loginViewOps = null;
    }
}
