package com.chen.mvp.injector;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by long on 17-4-10.
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PreFragment {
}
