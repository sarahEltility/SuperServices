package com.example.sarah.superservices.Laundary;

import android.content.Context;

/**
 * Created by Mah on 2/17/2017.
 */

public interface LaundariesViewOps {

    Context getContext();

    void showProgress();

    void hideProgress();

    void setRecyclerAdapter(AdapterUsers_laundary adapter);
}
