package com.hpw.myapp.ui.publish;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.hpw.myapp.R;
import com.hpw.myapp.widget.emoticonskeyboard.XhsEmoticonsKeyBoard;
import com.hpw.myapp.widget.emoticonskeyboard.utils.EmoticonsKeyboardUtils;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by hpw on 16/10/20.
 */

public class DefEmoticonsKeyBoard extends XhsEmoticonsKeyBoard {
    public final int APPS_HEIGHT = 120;

    public DefEmoticonsKeyBoard(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void inflateKeyboardBar() {
        View view = mInflater.inflate(R.layout.layout_emoticonskeyboard, this);
        ButterKnife.bind(this, view);
    }

    @Override
    protected View inflateFunc() {
        return mInflater.inflate(R.layout.view_func_emoticon_def, null);
    }

    @Override
    public void reset() {
        EmoticonsKeyboardUtils.closeSoftKeyboard(getContext());
        mLyKvml.hideAllFuncView();
        mBtnFace.setImageResource(R.drawable.emoticontabicon);
    }

    @Override
    public void onFuncChange(int key) {
        if (FUNC_TYPE_EMOTION == key) {
            mBtnFace.setImageResource(R.mipmap.chatting_softkeyboard);
        } else {
            mBtnFace.setImageResource(R.drawable.emoticontabicon);
        }
        checkVoice();
    }

    @Override
    public void OnSoftClose() {
        super.OnSoftClose();
        if (mLyKvml.getCurrentFuncKey() == FUNC_TYPE_APPPS) {
            setFuncViewHeight(EmoticonsKeyboardUtils.dip2px(getContext(), APPS_HEIGHT));
        }
    }

    @Override
    protected void showText() {
        mBtnFace.setVisibility(VISIBLE);
    }

    @OnClick({R.id.btn_capture})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_face:
                toggleFuncView(FUNC_TYPE_EMOTION);
                break;
            case R.id.btn_capture:
                PublishActivity.pickImage();
                break;
        }
    }
}
