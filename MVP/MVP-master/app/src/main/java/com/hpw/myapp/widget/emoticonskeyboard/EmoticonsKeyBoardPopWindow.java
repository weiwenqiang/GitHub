package com.hpw.myapp.widget.emoticonskeyboard;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;

import com.hpw.myapp.R;
import com.hpw.myapp.widget.emoticonskeyboard.adpater.PageSetAdapter;
import com.hpw.myapp.widget.emoticonskeyboard.data.PageSetEntity;
import com.hpw.myapp.widget.emoticonskeyboard.utils.EmoticonsKeyboardUtils;
import com.hpw.myapp.widget.emoticonskeyboard.widget.EmoticonsFuncView;
import com.hpw.myapp.widget.emoticonskeyboard.widget.EmoticonsIndicatorView;
import com.hpw.myapp.widget.emoticonskeyboard.widget.EmoticonsToolBarView;

import java.util.ArrayList;

public class EmoticonsKeyBoardPopWindow extends PopupWindow implements EmoticonsFuncView.OnEmoticonsPageViewListener, EmoticonsToolBarView.OnToolBarItemClickListener {

    private Context mContext;
    protected EmoticonsFuncView mEmoticonsFuncView;
    protected EmoticonsIndicatorView mEmoticonsIndicatorView;
    protected EmoticonsToolBarView mEmoticonsToolBarView;

    public EmoticonsKeyBoardPopWindow(Context context) {
        super(context, null);
        this.mContext = context;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View mConentView = inflater.inflate(R.layout.view_func_emoticon, null);

        setContentView(mConentView);
        setWidth(EmoticonsKeyboardUtils.getDisplayWidthPixels(mContext));
        setHeight(EmoticonsKeyboardUtils.getDefKeyboardHeight(mContext));
        setAnimationStyle(R.style.PopupAnimation);
        setOutsideTouchable(true);
        update();
        ColorDrawable dw = new ColorDrawable(0000000000);
        setBackgroundDrawable(dw);

        updateView(mConentView);
    }

    private void updateView(View view) {
        mEmoticonsFuncView = ((EmoticonsFuncView) view.findViewById(R.id.view_epv));
        mEmoticonsIndicatorView = (EmoticonsIndicatorView) view.findViewById(R.id.view_eiv);
        mEmoticonsToolBarView = (EmoticonsToolBarView) view.findViewById(R.id.view_etv);
        mEmoticonsFuncView.setOnIndicatorListener(this);
        mEmoticonsToolBarView.setOnToolBarItemClickListener(this);
    }

    public void setAdapter(PageSetAdapter pageSetAdapter) {
        if (pageSetAdapter != null) {
            ArrayList<PageSetEntity> pageSetEntities = pageSetAdapter.getPageSetEntityList();
            if (pageSetEntities != null) {
                for (PageSetEntity pageSetEntity : pageSetEntities) {
                    mEmoticonsToolBarView.addToolItemView(pageSetEntity);
                }
            }
        }
        mEmoticonsFuncView.setAdapter(pageSetAdapter);
    }

    public void showPopupWindow() {
        View rootView = EmoticonsKeyboardUtils.getRootView((Activity) mContext);
        if (this.isShowing()) {
            this.dismiss();
        } else {
            EmoticonsKeyboardUtils.closeSoftKeyboard(mContext);
            this.showAtLocation(rootView, Gravity.BOTTOM, 0, 0);
        }
    }

    @Override
    public void emoticonSetChanged(PageSetEntity pageSetEntity) {
        mEmoticonsToolBarView.setToolBtnSelect(pageSetEntity.getUuid());
    }

    @Override
    public void playTo(int position, PageSetEntity pageSetEntity) {
        mEmoticonsIndicatorView.playTo(position, pageSetEntity);
    }

    @Override
    public void playBy(int oldPosition, int newPosition, PageSetEntity pageSetEntity) {
        mEmoticonsIndicatorView.playBy(oldPosition, newPosition, pageSetEntity);
    }

    @Override
    public void onToolBarItemClick(PageSetEntity pageSetEntity) {
        mEmoticonsFuncView.setCurrentPageSet(pageSetEntity);
    }
}
