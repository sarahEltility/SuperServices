package com.example.sarah.superservices.Requests;

/**
 * Created by Mah on 2/20/2017.
 */

public class RequestPresenterImp implements RequestPresenterProviderOps {
    private RequestViewOps requestViewOps;
    public RequestPresenterImp(RequestViewOps requestViewOps){
        this.requestViewOps = requestViewOps;
    }
    @Override
    public void onDestroy() {
         requestViewOps = null;
    }
}
