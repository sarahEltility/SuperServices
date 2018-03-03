package com.example.sarah.superservices.Requests;

import android.content.Context;

/**
 * Created by Mah on 2/20/2017.
 */

public interface RequestViewOps {
    Context getContext();

    void showProgress();

    void hideProgress();
}
