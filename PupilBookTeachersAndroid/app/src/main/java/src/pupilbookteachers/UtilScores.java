package src.pupilbookteachers;

import android.graphics.Color;

/**
 * Created by KastanNotas on 5.12.2014.
 */
public class UtilScores {
    public static final int getColor(int score){
        switch (score){
            case 0 : return Color.rgb(204,0,0);
            case 1 : return Color.rgb(204,0,0);
            case 2 : return Color.rgb(255,68,68);
            case 3 : return Color.rgb(255,68,68);
            case 4 : return Color.rgb(255, 136, 00);
            case 5 : return Color.rgb(255, 136, 00);
            case 6 : return Color.rgb(102, 153, 00);
            case 7 : return Color.rgb(102, 153, 00);
            case 8 : return Color.rgb(153, 204, 00);
            default: return Color.rgb(153, 204, 00);

        }
    }
    public static final String getText(int score){
        switch (score){
            case 0:return "5";
            case 1:return "4-";
            case 2:return "4";
            case 3:return "3-";
            case 4:return "3";
            case 5:return "2-";
            case 6:return "2";
            case 7:return "1-";
            case 8:return "1";

            default: return "1*";
        }
    }
}
