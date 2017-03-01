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
    private int mAudioResourceId;

    public Words(String defaultTranslation, String miwokTranslation, int audio) {
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mAudioResourceId =audio;

    }

    public Words(String defaultTranslation, String miwokTranslation, int asset,int audio) {
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mImageResourceId = asset;
        mAudioResourceId = audio;
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
    public int getmAudioResourceId(){
        return mAudioResourceId;
    }
}
