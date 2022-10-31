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
public class Holiday {
    EthiopianCalendar ec;
    public String[] HOLYDAYS_NAME = {"ፅንሰት", "ልደት", "ጥምቀት", "ሆሳዕና", "ስቅለት", "ትንሳኤ", "ዕርገት", "ጰራቅሊጦስ", "ደብረ ታቦር"};
    int[] HOLYDAYS = new int[9];
    public Holiday() {
        
    }


    public boolean isHolyday(int date, int month, int year) {
        ec = new EthiopianCalendar();
        int day = ec.toNumberDay(date, month, year);
        getHolydays(year);
        for (int i = 0; i < HOLYDAYS.length; i++) {
            if (day == HOLYDAYS[i]) {
                return true;
            }
        }
        return false;
    }

    public String getHolydayDescription(int date, int month, int year) {
        ec = new EthiopianCalendar();
        if (isHolyday(date, month, year)) {
            String description = "";
            String another = "፣ ";
            int day = ec.toNumberDay(date, month, year);
            for (int i = 0; i < HOLYDAYS.length; i++) {
                if (day == HOLYDAYS[i]) {
                    if (description.equals("")) {
                        description = HOLYDAYS_NAME[i];
                    } else {
                        description += another + HOLYDAYS_NAME[i];
                    }
                }
            }
            return description;
        } else {
            return "";
        }
    }

    public void getHolydays(int year) {
        ec = new EthiopianCalendar();
        HOLYDAYS[0] = 209;
        HOLYDAYS[1] = ec.getGena(year);
        HOLYDAYS[2] = ec.getTimket();
        HOLYDAYS[3] = ec.getHosaena(year);
        HOLYDAYS[4] = ec.getSiqlet(year);
        HOLYDAYS[5] = ec.getTinsae(year);
        HOLYDAYS[6] = ec.getErget(year);
        HOLYDAYS[7] = ec.getPeraklitos(year);
        HOLYDAYS[8] = 343;
    }
}
