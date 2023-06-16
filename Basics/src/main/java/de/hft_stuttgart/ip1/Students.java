package de.hft_stuttgart.ip1;

import java.util.Arrays;

public class Students {
    public final static String [] NAMES = new String[] {
            "01ngph1bif", "02sovi1bif", "11pose1bif", "12xule1bif",
            "21adpa1bif", "21geem1bif", "21keli1bif", "21meaa1bif",
            "22abmu1bif", "22alyu1bif", "22autr1bif", "22coga1bif",
            "22dalu1bif", "22davu1bif", "22dupa1bif", "22faro1bif",
            "22frma1bif", "22grdo1bif", "22hith1bif", "22homa1bif",
            "22kiju1bif", "22laet1bif", "22rach1bif", "22roer1bif",
            "22scda1bif", "22sero1bif", "22stlu1bif", "22stmo1bif",
            "22weti1bif", "22wile1bif", "22zode1bif", "42hepe1bif",
            "@@@@@@@@@@", "@@@@@@@@@@", "@@@@@@@@@@", "@@@@@@@@@@",
            "@@@@@@@@@@", "@@@@@@@@@@", "@@@@@@@@@@", "@@@@@@@@@@",
            "@@@@@@@@@@", "@@@@@@@@@@", "@@@@@@@@@@", "@@@@@@@@@@",
            "@@@@@@@@@@", "@@@@@@@@@@", "@@@@@@@@@@", "@@@@@@@@@@",
            "@@@@@@@@@@", "@@@@@@@@@@", "@@@@@@@@@@", "@@@@@@@@@@",
            "@@@@@@@@@@", "@@@@@@@@@@", "@@@@@@@@@@", "@@@@@@@@@@",
            "@@@@@@@@@@", "@@@@@@@@@@", "@@@@@@@@@@", "@@@@@@@@@@",
            "@@@@@@@@@@", "@@@@@@@@@@", "@@@@@@@@@@", "@@@@@@@@@@",
            "@@@@@@@@@@", "heusch",
            "gruppe_01",  "gruppe_02",  "gruppe_03",  "gruppe_04",
            "gruppe_05",  "gruppe_06",  "gruppe_07",  "gruppe_08",
            "gruppe_09",  "gruppe_10",  "gruppe_11",  "gruppe_12",
    };

    public static int getIndex(String name) {
        for(int i=0;i<NAMES.length; i++) {
            if ( NAMES[i].equals(name) ) {
                return i;
            }
        }
        return -1;
    }

    public static int getPort(String name) {
        return 28700 + Math.max(-1, getIndex(name));
    }

    public static void main(String[] args) {
        for(int i=0;i<NAMES.length;i++) {
            int index = Math.abs(NAMES[i].hashCode())%3;
            String tasks[] = {
                    "DeHondt",
                    "HareNiemeyer",
                    "SaintLagueSchepers"
            };
            System.out.printf("%s muss Projekt %s bearbeiten.%n",
                    NAMES[i], tasks[index]);
        }
    }
}
