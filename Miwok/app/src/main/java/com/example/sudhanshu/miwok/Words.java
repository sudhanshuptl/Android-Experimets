package com.example.sudhanshu.miwok;

import java.util.ArrayList;

/**
 * Created by sudhanshu on 27/2/17.
 */



public class Words {

    public static final int NO_IMAGE_PROVIDED = -1;
    private String mDefaultTranslation;
    private String mMiwokTranslation;
    private int mImageResourceId = NO_IMAGE_PROVIDED;

    public Words(String defaultTranslation, String miwokTranslation) {
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;

    }

    public Words(String defaultTranslation, String miwokTranslation, int asset) {
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mImageResourceId = asset;
    }

    public String getDefaultTranslation() {
        return mDefaultTranslation;
    }

    public String getMiwokTranslation() {
        return mMiwokTranslation;
    }

    public int getImageAsset() {
        return mImageResourceId;
    }

    public boolean hasImage(){
        return NO_IMAGE_PROVIDED != mImageResourceId;
    }
}
