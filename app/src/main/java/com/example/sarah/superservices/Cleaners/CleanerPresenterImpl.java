package com.example.sarah.superservices.Cleaners;

/**
 * Created by Mah on 2/17/2017.
 */

public class CleanerPresenterImpl implements CleanerPresenterProviderOps {
    private CleanersViewOps followersViewOps;

    public CleanerPresenterImpl(CleanersViewOps followersViewOps){
        this.followersViewOps = followersViewOps;
    }

    @Override
    public void onDestroy() {
        followersViewOps = null;
    }
}
