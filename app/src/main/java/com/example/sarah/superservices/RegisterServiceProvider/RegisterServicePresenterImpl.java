package com.example.sarah.superservices.RegisterServiceProvider;

import android.util.Log;

import com.example.sarah.superservices.R;
import com.example.sarah.superservices.model.Connector;
import com.example.sarah.superservices.model.Users;

/**
 * Created by Mah on 2/4/2017.
 */

public class RegisterServicePresenterImpl implements RegisterServicePresenterProvidedOps {

    private RegisterServiceViewOps registerViewOps;

    public RegisterServicePresenterImpl(RegisterServiceViewOps registerViewOps) {
        this.registerViewOps = registerViewOps;
       //call model
    }

    @Override
    public void validateCredentials(String name,String email, String phone,String password,String age,String service) {
        if (registerViewOps != null) {
           registerViewOps.showProgress();
         //
            Log.i("show progress","----------");
        }


        if(password.length()==0){registerViewOps.setPasswordError(registerViewOps.getContext().getString(R.string.password_error));registerViewOps.hideProgress();}

        if(email.length()==0){   registerViewOps.setEmailError(registerViewOps.getContext().getString(R.string.email_error));   registerViewOps.hideProgress();}

        if(name.length()==0) {   registerViewOps.setNameError(registerViewOps.getContext().getString(R.string.name_error));    registerViewOps.hideProgress();}



        Connector connector = new Connector(registerViewOps.getContext(), registerViewOps);

        Users u1 = new Users();

        u1.setPassword(password);
        u1.setEmail(email);
        u1.setName(name);
        u1.setAge(age);
        u1.setService(service);
        u1.setPhone(phone);

        connector.RegisterService(u1);


    }


    @Override
    public void onDestroy() {registerViewOps=null;}
}
