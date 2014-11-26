/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.github.prosync.domain;

import java.util.ArrayList;

/**
 *
 * @author Rubenhag
 */
public final class Constants {
    public static final ArrayList<String> modes = new ArrayList<>();
    public static final ArrayList<String> videoResolutions = new ArrayList<>();
    public static final ArrayList<String> photoResolutions = new ArrayList<>();
    
    
    public static final String FOUR_K = "4K";
    public static final String FOUR_K_SEVENTEEN_NINE = "4K 17:9";
    public static final String TWO_POINT_SEVEN_K = "2.7K";
    public static final String TWO_POINT_SEVEN_K_SEVENTEEN_NINE = "2.7K 17:9";
    public static final String FOURTEEN_FOURTY_P = "1440p";
    public static final String TEN_EIGHTY_P = "1080p";
    public static final String TEN_EIGHTY_SV = "1080p SuperView";
    public static final String NINE_SIXTY_P = "960p";
    public static final String SEVEN_TWENTY_P = "720p";
    public static final String SEVEN_TWENTY_SV ="720p SuperView";
    public static final String WVGA = "WVGA";
    
    public static final String TWELVE_MP = "12MP";
    public static final String SEVEN_MP = "7MP";
    public static final String FIVE_MP = "5MP";
    
    public static final String VIDEO_MODE = "Video";
    public static final String PHOTO_MODE = "Foto, singel";
    public static final String BURST_MODE = "Foto, burst mode";
    
    
    
    public static ArrayList getVideoResolutions(){
        videoResolutions.clear();
        videoResolutions.add(FOUR_K);
        videoResolutions.add(FOUR_K_SEVENTEEN_NINE);
        videoResolutions.add(TWO_POINT_SEVEN_K);
        videoResolutions.add(TWO_POINT_SEVEN_K_SEVENTEEN_NINE);
        videoResolutions.add(FOURTEEN_FOURTY_P);
        videoResolutions.add(TEN_EIGHTY_P);
        videoResolutions.add(TEN_EIGHTY_SV);
        videoResolutions.add(NINE_SIXTY_P);
        videoResolutions.add(SEVEN_TWENTY_P);
        videoResolutions.add(SEVEN_TWENTY_SV);
        videoResolutions.add(WVGA);
        return videoResolutions;
    }
    
    public static ArrayList getPhotoResolutions(){
        photoResolutions.clear();
        photoResolutions.add(TWELVE_MP);
        photoResolutions.add(SEVEN_MP);
        photoResolutions.add(FIVE_MP);
        return photoResolutions;
    }
    
    public static ArrayList getModeList(){
        modes.clear();
        modes.add(VIDEO_MODE);
        modes.add(PHOTO_MODE);
        modes.add(BURST_MODE);
        return modes;
    }
}
