/*
 * Copyright 2017 Hannes Dorfmann.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.hannesdorfmann.mosby3.mvp.delegate;

import com.hannesdorfmann.mosby3.mvp.MvpPresenter;
import com.hannesdorfmann.mosby3.mvp.MvpView;
import com.hannesdorfmann.mosby3.mvp.viewstate.ViewState;

public abstract class PartialViewGroupMvpViewStateDelegateCallbackImpl implements
    ViewGroupMvpViewStateDelegateCallback<MvpView, MvpPresenter<MvpView>, ViewState<MvpView>> {

  MvpPresenter<MvpView> presenter;
  ViewState<MvpView> viewState;

  @Override public MvpPresenter<MvpView> getPresenter() {
    return presenter;
  }

  @Override public void setPresenter(MvpPresenter<MvpView> presenter) {
    this.presenter = presenter;
  }

  @Override public ViewState<MvpView> getViewState() {
    return viewState;
  }

  @Override public void setViewState(ViewState<MvpView> viewState) {
    this.viewState = viewState;
  }
}