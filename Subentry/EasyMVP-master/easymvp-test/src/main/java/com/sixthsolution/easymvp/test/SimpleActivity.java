package com.sixthsolution.easymvp.test;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.RelativeLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import easymvp.annotation.ActivityView;
import easymvp.annotation.Presenter;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

/**
 * @author Saeed Masoumi (saeed@6thsolution.com)
 */

@ActivityView(presenter = TestPresenter.class, layout = R.layout.activity_main)
public class SimpleActivity extends Activity implements View1 {

    @Presenter
    TestPresenter testPresenter;

    @BindView(R.id.base_layout)
    RelativeLayout view;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        assertNotNull(view);
    }

    @Override
    protected void onStart() {
        super.onStart();
        assertNotNull(testPresenter);
        assertTrue(testPresenter.isOnViewAttachedCalled());
    }

    @Override
    protected void onStop() {
        super.onStop();
        assertTrue(testPresenter.isOnViewDetachedCalled());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //        assertNull(testPresenter);
        //TODO OnLoaderReset will be called after onDestroy, so Presenter#onDestroyed will not be called here.
    }

}
