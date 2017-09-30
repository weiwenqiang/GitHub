package com.hpw.myapp.widget.imageselector.utils;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.text.TextUtils;

import com.hpw.myapp.widget.imageselector.model.LocalMedia;
import com.hpw.myapp.widget.imageselector.model.LocalMediaFolder;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class LocalMediaLoader {
    // load type
    public static final int TYPE_IMAGE = 1;
    public static final int TYPE_VIDEO = 2;
    private boolean hasFolderGened = false;
    private ArrayList<LocalMediaFolder> mResultFolder = new ArrayList<>();

    private final static String[] IMAGE_PROJECTION = {
            MediaStore.Images.Media.DATA,
            MediaStore.Images.Media.DISPLAY_NAME,
            MediaStore.Images.Media.DATE_ADDED,
            MediaStore.Images.Media._ID};

    private final static String[] VIDEO_PROJECTION = {
            MediaStore.Video.Media.DATA,
            MediaStore.Video.Media.DISPLAY_NAME,
            MediaStore.Video.Media.DATE_ADDED,
            MediaStore.Video.Media._ID,
            MediaStore.Video.Media.DURATION};

    private int type = TYPE_IMAGE;
    private FragmentActivity activity;

    public LocalMediaLoader(FragmentActivity activity, int type) {
        this.activity = activity;
        this.type = type;
    }

    HashSet<String> mDirPaths = new HashSet<String>();

    public void loadAllImage(final LocalMediaLoadListener imageLoadListener) {
        activity.getSupportLoaderManager().initLoader(type, null, new LoaderManager.LoaderCallbacks<Cursor>() {
            @Override
            public Loader<Cursor> onCreateLoader(int id, Bundle args) {
                CursorLoader cursorLoader = null;
                if (id == TYPE_IMAGE) {
                    cursorLoader = new CursorLoader(
                            activity, MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                            IMAGE_PROJECTION, MediaStore.Images.Media.SIZE + ">0 AND " + MediaStore.Images.Media.MIME_TYPE + "=? or "
                            + MediaStore.Images.Media.MIME_TYPE + "=?",
                            new String[]{"image/jpeg", "image/png"}, IMAGE_PROJECTION[2] + " DESC");
                } else if (id == TYPE_VIDEO) {
                    cursorLoader = new CursorLoader(
                            activity, MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                            VIDEO_PROJECTION, null, null, VIDEO_PROJECTION[2] + " DESC");
                }
                return cursorLoader;
            }

            @Override
            public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
//                ArrayList<LocalMediaFolder> imageFolders = new ArrayList<LocalMediaFolder>();
//                LocalMediaFolder allImageFolder = new LocalMediaFolder();
//                List<LocalMedia> allImages = new ArrayList<LocalMedia>();
//
//                while (data != null && data.moveToNext()) {
//                    // 获取图片的路径
//                    String path = data.getString(data
//                            .getColumnIndex(MediaStore.Images.Media.DATA));
//                    File file = new File(path);
//                    if (!file.exists())
//                        continue;
//                    // 获取该图片的目录路径名
//                    File parentFile = file.getParentFile();
//                    if (parentFile == null || !parentFile.exists())
//                        continue;
//
//                    String dirPath = parentFile.getAbsolutePath();
//                    // 利用一个HashSet防止多次扫描同一个文件夹
//                    if (mDirPaths.contains(dirPath)) {
//                        continue;
//                    } else {
//                        mDirPaths.add(dirPath);
//                    }
//
//                    if (parentFile.list() == null)
//                        continue;
//                    LocalMediaFolder localMediaFolder = getImageFolder(path, imageFolders);
//
//                    File[] files = parentFile.listFiles(new FilenameFilter() {
//                        @Override
//                        public boolean accept(File dir, String filename) {
//                            if (filename.endsWith(".jpg")
//                                    || filename.endsWith(".png")
//                                    || filename.endsWith(".jpeg"))
//                                return true;
//                            return false;
//                        }
//                    });
//                    ArrayList<LocalMedia> images = new ArrayList<>();
//                    for (int i = 0; i < files.length; i++) {
//                        File f = files[i];
//                        LocalMedia localMedia = new LocalMedia(f.getAbsolutePath());
//                        allImages.add(localMedia);
//                        images.add(localMedia);
//                    }
//                    if (images.size() > 0) {
//                        localMediaFolder.setImages(images);
//                        localMediaFolder.setImageNum(localMediaFolder.getImages().size());
//                        imageFolders.add(localMediaFolder);
//                    }
//                }
                if (data != null) {
                    if (data.getCount() > 0) {
                        List<LocalMedia> images = new ArrayList<>();
                        data.moveToFirst();
                        do {
                            String path = data.getString(data.getColumnIndexOrThrow(IMAGE_PROJECTION[0]));
                            if (!fileExist(path)) {
                                continue;
                            }
                            LocalMedia image = null;
                            if (!TextUtils.isEmpty(path)) {
                                image = new LocalMedia(path);
//                                if (images.size() < 530)
                                images.add(image);
                            }
                            if (!hasFolderGened) {
                                // get all folder data
                                File folderFile = new File(path).getParentFile();
                                if (folderFile != null && folderFile.exists()) {
                                    String fp = folderFile.getAbsolutePath();
                                    LocalMediaFolder f = getFolderByPath(fp);
                                    if (f == null) {
                                        LocalMediaFolder folder = new LocalMediaFolder();
                                        folder.setName(folderFile.getName());
                                        folder.setPath(fp);
                                        List<LocalMedia> imageList = new ArrayList<>();
                                        imageList.add(image);
                                        folder.setImages(imageList);
                                        folder.setFirstImagePath(imageList.get(0).getPath());
                                        folder.setImageNum(1);
                                        mResultFolder.add(folder);
                                    } else {
                                        f.getImages().add(image);
                                        f.setImageNum(f.getImages().size());
                                    }
                                }
                            }

                        } while (data.moveToNext());

                        if (!hasFolderGened) {
                            LocalMediaFolder folder = new LocalMediaFolder();
                            folder.setName("所有图片");
                            folder.setImages(images);
                            folder.setFirstImagePath(images.get(0).getPath());
                            folder.setImageNum(images.size());
                            mResultFolder.set(0, folder);
                            imageLoadListener.loadComplete(mResultFolder);
                            hasFolderGened = true;
                        }
                    }
                }
                if (data != null) data.close();
            }

            @Override
            public void onLoaderReset(Loader<Cursor> loader) {
            }
        });
    }

    private boolean fileExist(String path) {
        if (!TextUtils.isEmpty(path)) {
            return new File(path).exists();
        }
        return false;
    }

    private LocalMediaFolder getFolderByPath(String path) {
        if (mResultFolder != null) {
            for (LocalMediaFolder folder : mResultFolder) {
                if (TextUtils.equals(folder.getPath(), path)) {
                    return folder;
                }
            }
        }
        return null;
    }

    private void sortFolder(List<LocalMediaFolder> imageFolders) {
        // 文件夹按图片数量排序
        Collections.sort(imageFolders, new Comparator<LocalMediaFolder>() {
            @Override
            public int compare(LocalMediaFolder lhs, LocalMediaFolder rhs) {
                if (lhs.getImages() == null || rhs.getImages() == null) {
                    return 0;
                }
                int lsize = lhs.getImageNum();
                int rsize = rhs.getImageNum();
                return lsize == rsize ? 0 : (lsize < rsize ? 1 : -1);
            }
        });
    }

    private LocalMediaFolder getImageFolder(String path, List<LocalMediaFolder> imageFolders) {
        File imageFile = new File(path);
        File folderFile = imageFile.getParentFile();

        for (LocalMediaFolder folder : imageFolders) {
            if (folder.getName().equals(folderFile.getName())) {
                return folder;
            }
        }
        LocalMediaFolder newFolder = new LocalMediaFolder();
        newFolder.setName(folderFile.getName());
        newFolder.setPath(folderFile.getAbsolutePath());
        newFolder.setFirstImagePath(path);
        return newFolder;
    }

    public interface LocalMediaLoadListener {
        void loadComplete(List<LocalMediaFolder> folders);
    }

}
