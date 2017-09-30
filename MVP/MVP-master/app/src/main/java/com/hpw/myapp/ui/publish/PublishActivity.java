package com.hpw.myapp.ui.publish;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hpw.mvpframe.base.CoreBaseActivity;
import com.hpw.mvpframe.utils.StatusBarUtil;
import com.hpw.mvpframe.utils.StringUtils;
import com.hpw.mvpframe.widget.recyclerview.BaseQuickAdapter;
import com.hpw.mvpframe.widget.recyclerview.BaseViewHolder;
import com.hpw.mvpframe.widget.recyclerview.CoreRecyclerView;
import com.hpw.mvpframe.widget.recyclerview.listener.OnItemClickListener;
import com.hpw.myapp.R;
import com.hpw.myapp.ui.publish.utils.PublishUtils;
import com.hpw.myapp.widget.emoticonskeyboard.adpater.PageSetAdapter;
import com.hpw.myapp.widget.emoticonskeyboard.interfaces.EmoticonClickListener;
import com.hpw.myapp.widget.emoticonskeyboard.widget.EmoticonsEditText;
import com.hpw.myapp.widget.emoticonskeyboard.widget.FuncLayout;
import com.hpw.myapp.widget.imageselector.model.LocalMedia;
import com.hpw.myapp.widget.imageselector.view.ImagePreviewActivity;
import com.hpw.myapp.widget.imageselector.view.ImageSelectorActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by hpw on 16/11/22.
 */

public class PublishActivity extends CoreBaseActivity implements FuncLayout.OnFuncKeyBoardListener {

    @BindView(R.id.tv_publish)
    TextView tvPublish;
    @BindView(R.id.et_content)
    EmoticonsEditText etContent;
    @BindView(R.id.et_limit)
    TextView tvLimit;
    @BindView(R.id.ek_bar)
    DefEmoticonsKeyBoard ekBar;
    private static int inputLength = 300;
    StringBuilder publishString;
    private static final int REQUEST_IMAGE = 2;
    private static ArrayList<String> mSelectPath = new ArrayList<>();
    private static Activity activity;
    @BindView(R.id.result_recycler)
    CoreRecyclerView recyclerView;

    @Override
    public int getLayoutId() {
        return R.layout.activity_publish;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        mSelectPath.clear();
        StatusBarUtil.setTransparent(this);
        activity = this;
        tvPublish.setEnabled(false);
        initEmoticonsKeyBoardBar();
        initEmoticonsEditText();
        recyclerView.init(new GridLayoutManager(mContext, 5), new BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_photo_result) {
            @Override
            protected void convert(BaseViewHolder helper, String item) {
                Glide.with(mContext).load(item).crossFade().centerCrop().into((ImageView) helper.getView(R.id.image));
            }
        }).addOnItemClickListener(new OnItemClickListener() {
            @Override
            public void SimpleOnItemClick(BaseQuickAdapter adapter, View view, int position) {
                ArrayList<LocalMedia> mSelect = new ArrayList<>();
                for (String string : mSelectPath) {
                    mSelect.add(new LocalMedia(string));
                }
                ImagePreviewActivity.startPreview(activity, mSelect, mSelect, mSelect.size(), position);
            }
        });
    }

    private void initEmoticonsEditText() {
        publishString = new StringBuilder();
        PublishUtils.initEmoticonsEditText(etContent);
        etContent.setFocusable(true);
        etContent.setFocusableInTouchMode(true);
        etContent.requestFocus();
        etContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                publishString.setLength(0);
                publishString.append(s);
                if (s == null) return;
                String str = s.toString().trim();
                if (isInputLengthOutOfRange(str) || str.equals("")) {
                    tvPublish.setEnabled(false);
                    tvPublish.setTextColor(getResources().getColor(R.color.font_dark_gery));
                    setTextInputCount(str, true);
                } else {
                    tvPublish.setEnabled(true);
                    tvPublish.setTextColor(getResources().getColor(R.color.white));
                    setTextInputCount(str, false);
                }
            }
        });
    }

    /**
     * 设置当前输入框中文字的颜色
     *
     * @param input
     */
    private void setTextInputCount(String input, boolean isOutOfRange) {
        int count = (int) Math.ceil((double) StringUtils.getChineseCount(input) / 2d);
        tvLimit.setText(" 还可以输入" + (inputLength - count) + "字");
        if (isOutOfRange) {
            if (input.equals("")) {
                tvLimit.setTextColor(getResources().getColor(R.color.font_dark_gery));
            } else {
                tvLimit.setTextColor(getResources().getColor(R.color.md_red_500));
            }
        } else {
            tvLimit.setTextColor(getResources().getColor(R.color.font_dark_gery));
        }
    }

    /**
     * 判断当前输入框中文字是否超过规定长度
     *
     * @param input
     * @return
     */
    private boolean isInputLengthOutOfRange(String input) {
        return Math.ceil((double) StringUtils.getChineseCount(input) / 2d) > inputLength;
    }

    private void initEmoticonsKeyBoardBar() {
        EmoticonClickListener emoticonClickListener = PublishUtils.getCommonEmoticonClickListener(etContent);
        PageSetAdapter pageSetAdapter = new PageSetAdapter();
        PublishUtils.addXhsPageSetEntity(pageSetAdapter, this, emoticonClickListener);
        ekBar.setAdapter(pageSetAdapter);
        ekBar.addOnFuncKeyBoardListener(this);
    }

    public static void startActivity(Context mContext) {
        Intent intent = new Intent(mContext, PublishActivity.class);
        mContext.startActivity(intent);
    }

    @OnClick({R.id.tv_cancel, R.id.tv_publish})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_cancel:
                finish();
                break;
            case R.id.tv_publish:
                publish();
                break;
        }
    }

    private void publish() {

    }

    @Override
    public void OnFuncPop(int height) {

    }

    @Override
    public void OnFuncClose() {

    }

    @Override
    public void onPause() {
        super.onPause();
        ekBar.reset();
    }

    public static void pickImage() {
        ImageSelectorActivity.start(activity, 9 - mSelectPath.size(), ImageSelectorActivity.MODE_MULTIPLE, true, true, false);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == ImageSelectorActivity.REQUEST_IMAGE) {
            mSelectPath.addAll((ArrayList<String>) data.getSerializableExtra(ImageSelectorActivity.REQUEST_OUTPUT));
        } else if (resultCode == RESULT_OK && requestCode == ImagePreviewActivity.REQUEST_PREVIEW) {
            mSelectPath.clear();
            for (LocalMedia localMedia : (ArrayList<LocalMedia>) data.getSerializableExtra(ImageSelectorActivity.REQUEST_OUTPUT)) {
                mSelectPath.add(localMedia.getPath());
            }
        }
        recyclerView.getAdapter().setNewData(mSelectPath);
    }
}
