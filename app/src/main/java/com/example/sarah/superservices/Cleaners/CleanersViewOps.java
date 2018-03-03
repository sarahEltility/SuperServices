package com.example.sarah.superservices.Cleaners;

import android.content.Context;

/**
 * Created by Mah on 2/17/2017.
 */

public interface CleanersViewOps {

    Context getContext();

    void showProgress();

    void hideProgress();

    void setRecyclerAdapter(AdapterUsers_cleaners adapter);
}
