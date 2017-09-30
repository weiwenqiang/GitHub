package com.hpw.myapp.ui.publish.utils;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.hpw.myapp.R;
import com.hpw.myapp.ui.publish.DefXhsEmoticons;
import com.hpw.myapp.widget.emoticonskeyboard.adpater.EmoticonsAdapter;
import com.hpw.myapp.widget.emoticonskeyboard.adpater.PageSetAdapter;
import com.hpw.myapp.widget.emoticonskeyboard.data.EmoticonEntity;
import com.hpw.myapp.widget.emoticonskeyboard.data.EmoticonPageEntity;
import com.hpw.myapp.widget.emoticonskeyboard.data.EmoticonPageSetEntity;
import com.hpw.myapp.widget.emoticonskeyboard.interfaces.EmoticonClickListener;
import com.hpw.myapp.widget.emoticonskeyboard.interfaces.EmoticonDisplayListener;
import com.hpw.myapp.widget.emoticonskeyboard.interfaces.PageViewInstantiateListener;
import com.hpw.myapp.widget.emoticonskeyboard.utils.imageloader.ImageBase;
import com.hpw.myapp.widget.emoticonskeyboard.utils.imageloader.ImageLoader;
import com.hpw.myapp.widget.emoticonskeyboard.widget.EmoticonPageView;
import com.hpw.myapp.widget.emoticonskeyboard.widget.EmoticonsEditText;

import java.io.IOException;
import java.lang.reflect.Constructor;

/**
 * Created by hpw on 16/10/19.
 */

public class PublishUtils {
    public static void initEmoticonsEditText(EmoticonsEditText etContent) {
        etContent.addEmoticonFilter(new XhsFilter());
    }

    public static EmoticonClickListener getCommonEmoticonClickListener(final EditText editText) {
        return new EmoticonClickListener() {
            @Override
            public void onEmoticonClick(Object o, int actionType, boolean isDelBtn) {
                if (isDelBtn) {
                    PublishUtils.delClick(editText);
                } else {
                    if (o == null) {
                        return;
                    }
                    if (actionType == Constants.EMOTICON_CLICK_TEXT) {
                        String content = null;
//                        if (o instanceof EmojiBean) {
//                            content = ((EmojiBean) o).emoji;
//                        } else
                        if (o instanceof EmoticonEntity) {
                            content = ((EmoticonEntity) o).getContent();
                        }

                        if (TextUtils.isEmpty(content)) {
                            return;
                        }
                        int index = editText.getSelectionStart();
                        Editable editable = editText.getText();
                        editable.insert(index, content);
                    }
                }
            }
        };
    }

    /**
     * 插入xhs表情集
     *
     * @param pageSetAdapter
     * @param context
     * @param emoticonClickListener
     */
    public static void addXhsPageSetEntity(PageSetAdapter pageSetAdapter, Context context, EmoticonClickListener emoticonClickListener) {
        EmoticonPageSetEntity xhsPageSetEntity
                = new EmoticonPageSetEntity.Builder()
                .setLine(3)
                .setRow(7)
                .setEmoticonList(ParseDataUtils.ParseXhsData(DefXhsEmoticons.xhsEmoticonArray, ImageBase.Scheme.ASSETS))
                .setIPageViewInstantiateItem(getDefaultEmoticonPageViewInstantiateItem(getCommonEmoticonDisplayListener(emoticonClickListener, Constants.EMOTICON_CLICK_TEXT)))
                .setShowDelBtn(EmoticonPageEntity.DelBtnStatus.LAST)
                .setIconUri(ImageBase.Scheme.ASSETS.toUri("01bless.gif"))
                .build();
        pageSetAdapter.add(xhsPageSetEntity);
    }

    public static PageViewInstantiateListener<EmoticonPageEntity> getDefaultEmoticonPageViewInstantiateItem(final EmoticonDisplayListener<Object> emoticonDisplayListener) {
        return getEmoticonPageViewInstantiateItem(EmoticonsAdapter.class, null, emoticonDisplayListener);
    }

    public static PageViewInstantiateListener<EmoticonPageEntity> getEmoticonPageViewInstantiateItem(final Class _class, final EmoticonClickListener onEmoticonClickListener, final EmoticonDisplayListener<Object> emoticonDisplayListener) {
        return new PageViewInstantiateListener<EmoticonPageEntity>() {
            @Override
            public View instantiateItem(ViewGroup container, int position, EmoticonPageEntity pageEntity) {
                if (pageEntity.getRootView() == null) {
                    EmoticonPageView pageView = new EmoticonPageView(container.getContext());
                    pageView.setNumColumns(pageEntity.getRow());
                    pageEntity.setRootView(pageView);
                    try {
                        EmoticonsAdapter adapter = (EmoticonsAdapter) newInstance(_class, container.getContext(), pageEntity, onEmoticonClickListener);
                        if (emoticonDisplayListener != null) {
                            adapter.setOnDisPlayListener(emoticonDisplayListener);
                        }
                        pageView.getEmoticonsGridView().setAdapter(adapter);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                return pageEntity.getRootView();
            }
        };
    }

    public static EmoticonDisplayListener<Object> getCommonEmoticonDisplayListener(final EmoticonClickListener onEmoticonClickListener, final int type) {
        return new EmoticonDisplayListener<Object>() {
            @Override
            public void onBindView(int position, ViewGroup parent, EmoticonsAdapter.ViewHolder viewHolder, Object object, final boolean isDelBtn) {

                final EmoticonEntity emoticonEntity = (EmoticonEntity) object;
                if (emoticonEntity == null && !isDelBtn) {
                    return;
                }
                viewHolder.ly_root.setBackgroundResource(R.drawable.bg_emoticon);

                if (isDelBtn) {
                    viewHolder.iv_emoticon.setImageResource(R.drawable.icon_del);
                } else {
                    try {
                        ImageLoader.getInstance(viewHolder.iv_emoticon.getContext()).displayImage(emoticonEntity.getIconUri(), viewHolder.iv_emoticon);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                viewHolder.rootView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (onEmoticonClickListener != null) {
                            onEmoticonClickListener.onEmoticonClick(emoticonEntity, type, isDelBtn);
                        }
                    }
                });
            }
        };
    }

    @SuppressWarnings("unchecked")
    public static Object newInstance(Class _Class, Object... args) throws Exception {
        return newInstance(_Class, 0, args);
    }

    @SuppressWarnings("unchecked")
    public static Object newInstance(Class _Class, int constructorIndex, Object... args) throws Exception {
        Constructor cons = _Class.getConstructors()[constructorIndex];
        return cons.newInstance(args);
    }

    public static void delClick(EditText editText) {
        int action = KeyEvent.ACTION_DOWN;
        int code = KeyEvent.KEYCODE_DEL;
        KeyEvent event = new KeyEvent(action, code);
        editText.onKeyDown(KeyEvent.KEYCODE_DEL, event);
    }
}
