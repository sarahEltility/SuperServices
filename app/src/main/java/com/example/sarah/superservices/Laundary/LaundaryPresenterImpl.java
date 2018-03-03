package com.example.sarah.superservices.Laundary;

/**
 * Created by Mah on 2/17/2017.
 */

public class LaundaryPresenterImpl implements LaundaryPresenterProviderOps {
    private LaundariesViewOps followersViewOps;

    public LaundaryPresenterImpl(LaundariesViewOps followersViewOps){
        this.followersViewOps = followersViewOps;
    }

    @Override
    public void onDestroy() {
        followersViewOps = null;
    }
}
