/*
 * Copyright (C) 2016 Tadesse Angaw
 *
 * This program is written by Tadesse Angaw.
 * You can use and redistribute it without modification.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 * You can contact me at tadesseangaw@gmail.com
 */
package com.tadesse.bahirehasab.tray.tools;

/**
 *
 * @author Tadesse Angaw
 */
public class Fasting {

    String FAST_NAMES[] = {"ጽጌ ጾም", "ጾመ ነቢያት (የገና ጾም)", "ጾመ ጋድ", "ጾመ ነነዌ", "ዐቢይ ጾም (ጾመ ሁዳዴ)", "ጾመ ሐዋርያት (የሰኔ ጾም)", "ጾመ ድኅነት (ረቡዕ እና አርብ)", "ጾመ ፍልሰታ"};
    EthiopianCalendar ec;
    int Year;
    int[] FAST = new int[8];

    public boolean isFastStarting(int day, int year) {
        getFasting(year);
        for (int i = 0; i < FAST.length; i++) {
            if (day == FAST[i]) {
                return true;
            }
        }
        return false;
    }

    public void getFasting(int year) {
        ec = new EthiopianCalendar();
        FAST[0] = ec.getTsege();
        FAST[1] = ec.getTsomeNebiyat();
        FAST[2] = ec.getGad(year);
        FAST[3] = ec.getNenewe(year);
        FAST[4] = ec.getAbeyTsome(year);
        FAST[5] = ec.getTsomeHawariyat(year);
        FAST[6] = ec.getTsomeDihnet(year);
        FAST[7] = ec.getFelseta();
    }

    public String getFastingDescription(int day, int year) {
        getFasting(year);
        for (int i = 0; i < FAST.length; i++) {
            if (day == FAST[i]) {
                return FAST_NAMES[i];
            }
        }
        return "";
    }
}
