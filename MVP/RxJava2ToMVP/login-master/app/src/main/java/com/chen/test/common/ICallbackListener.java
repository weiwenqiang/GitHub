package com.chen.test.common;

/**
 * Created by long on 17-3-22.
 */

public interface ICallbackListener<T> {

    void onSuccess(T data);

    void onFail(String error);
}
