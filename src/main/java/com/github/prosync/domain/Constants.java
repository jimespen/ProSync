/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.prosync.domain;

import java.util.ArrayList;

/**
 * @author Rubenhag
 */
public final class Constants {

    public static final ArrayList<String> modes = new ArrayList<>();
    public static final ArrayList<String> videoResolutions = new ArrayList<>();
    public static final ArrayList<String> photoResolutions = new ArrayList<>();
    public static final ArrayList<String> fpsList = new ArrayList<>();

    public static final String FOUR_K = "4K";
    public static final String FOUR_K_SEVENTEEN_NINE = "4K 17:9";
    public static final String TWO_POINT_SEVEN_K = "2.7K";
    //public static final String TWO_POINT_SEVEN_K_SEVENTEEN_NINE = "2.7K 17:9";
    public static final String FOURTEEN_FOURTY_P = "1440p";
    public static final String TEN_EIGHTY_P = "1080p";
    public static final String TEN_EIGHTY_SV = "1080p SuperView";
    public static final String NINE_SIXTY_P = "960p";
    public static final String SEVEN_TWENTY_P = "720p";
    public static final String SEVEN_TWENTY_SV = "720p SuperView";
    //public static final String WVGA = "WVGA";

    public static final String TWELVE_MP = "12MP";
    public static final String SEVEN_MP = "7MP";
    public static final String FIVE_MP = "5MP";

    public static final String VIDEO_MODE = "Video";
    public static final String PHOTO_MODE = "Foto, singel";
    public static final String BURST_MODE = "Foto, burst mode";

    public static final String TWO_HUNDRED_FOURTY_FPS = "240";
    public static final String ONE_HUNDRED_FPS = "100";
    public static final String FIFTY_FPS = "50";
    public static final String FOURTY_EIGHT_FPS = "48";
    public static final String TWENTY_FIVE_FPS = "25";
    public static final String TWENTY_FOUR_FPS = "24";
    public static final String TWELVE_POINT_FIVE_FPS = "12.5";
    public static final String TWELVE_FPS = "12";

    public final static int NAME_COLUMN = 0;
    public final static int GROUP_COLUMN = 1;
    public final static int MODE_COLUMN = 2;
    public final static int DATE_COLUMN = 3;
    public final static int DOWNLOAD_COLUMN = 4;

    public static ArrayList getVideoResolutions() {
        videoResolutions.clear();
        videoResolutions.add(FOUR_K);
        videoResolutions.add(FOUR_K_SEVENTEEN_NINE);
        videoResolutions.add(TWO_POINT_SEVEN_K);
        //videoResolutions.add(TWO_POINT_SEVEN_K_SEVENTEEN_NINE);
        videoResolutions.add(FOURTEEN_FOURTY_P);
        videoResolutions.add(TEN_EIGHTY_P);
        videoResolutions.add(TEN_EIGHTY_SV);
        videoResolutions.add(NINE_SIXTY_P);
        videoResolutions.add(SEVEN_TWENTY_P);
        videoResolutions.add(SEVEN_TWENTY_SV);
        //videoResolutions.add(WVGA);
        return videoResolutions;
    }

    public static ArrayList getPhotoResolutions() {
        photoResolutions.clear();
        photoResolutions.add(TWELVE_MP);
        photoResolutions.add(SEVEN_MP);
        photoResolutions.add(FIVE_MP);
        return photoResolutions;
    }

    public static ArrayList getModeList() {
        modes.clear();
        modes.add(VIDEO_MODE);
        modes.add(PHOTO_MODE);
        modes.add(BURST_MODE);
        return modes;
    }

    public static ArrayList getFpsList(String resolutionSelected) {
        fpsList.clear();
        switch (resolutionSelected) {
            case FOUR_K:
                fpsList.add(TWELVE_POINT_FIVE_FPS);
                break;
            case FOUR_K_SEVENTEEN_NINE:
                fpsList.add(TWELVE_FPS);
                break;
            case TWO_POINT_SEVEN_K:
                fpsList.add(TWENTY_FIVE_FPS);
                break;
            // case TWO_POINT_SEVEN_K_SEVENTEEN_NINE:
            //   fpsList.add(TWENTY_FOUR_FPS);
            // break;
            case FOURTEEN_FOURTY_P:
                fpsList.add(FOURTY_EIGHT_FPS);
                fpsList.add(TWENTY_FIVE_FPS);
                fpsList.add(TWENTY_FOUR_FPS);
                break;
            case TEN_EIGHTY_P:
                fpsList.add(FIFTY_FPS);
                fpsList.add(FOURTY_EIGHT_FPS);
                fpsList.add(TWENTY_FIVE_FPS);
                fpsList.add(TWENTY_FOUR_FPS);
                break;
            case TEN_EIGHTY_SV:
                fpsList.add(FIFTY_FPS);
                fpsList.add(FOURTY_EIGHT_FPS);
                fpsList.add(TWENTY_FIVE_FPS);
                fpsList.add(TWENTY_FOUR_FPS);
                break;
            case NINE_SIXTY_P:
                fpsList.add(ONE_HUNDRED_FPS);
                fpsList.add(FIFTY_FPS);
                fpsList.add(FOURTY_EIGHT_FPS);
                break;
            case SEVEN_TWENTY_P:
                fpsList.add(ONE_HUNDRED_FPS);
                fpsList.add(FIFTY_FPS);
                break;
            case SEVEN_TWENTY_SV:
                fpsList.add(ONE_HUNDRED_FPS);
                fpsList.add(FIFTY_FPS);
                fpsList.add(FOURTY_EIGHT_FPS);
                break;
            /*case WVGA:
                fpsList.add(TWO_HUNDRED_FOURTY_FPS);
                break;*/
        }

        return fpsList;
    }
}
