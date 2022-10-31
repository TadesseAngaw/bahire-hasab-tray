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

import java.util.Calendar;

/**
 *
 * Ethiopian Calendar the official calendar of Ethiopia.<br>
 *
 * The calculations are based on the books.
 * <ul>
 * <li>'Abushaher'</li>
 * <li>The Book Of 'Henok' and</li>
 * <li>'Bahire Hasab'</li>
 * </ul>
 * <p>
 * This Calendar is mainly used in Ethiopian Orthodox Tewahido Church.</p>
 * <p>
 * With almost the same months arrangement it seems like the Julian Calendar.
 * Here there are 13 months with 12 of them have equal numbers of days (which
 * are 30 days), but 13<sup>th</sup> month has 5 days in normal. The last month
 * may have 6 days in 4 years gap and may have 7 days in 600 years duration.</p>
 *
 * <p>
 * The class EthiopianCalendar has more than 38 methods those can be used to
 * find the fasting days and holidays in the given year. Each methods have high
 * relationship between them.
 * </p>
 *
 * <p>
 * Most of the methods returns the day result in day of the year format
 * (Integer). So to get the name of the day or the month of the day we have to
 * call the methods getDayName(int day, int year) and getMonthFormat(int day,
 * int year).
 * </p>
 *
 * @author Tadesse Angaw
 */
public class EthiopianCalendar {

    /**
     * The years before the birth of Christ
     */
    public static final int AMETE_ALEM = 5500;

    /**
     * Amharic Names for 13 months in a year.
     */
    public static final String MONTHS[] = {"መስከረም", "ጥቅምት", "ኅዳር", "ታህሳስ", "ጥር",
        "የካቲት", "መጋቢት", "ሚያዝያ", "ግንቦት", "ሰኔ", "ሐምሌ", "ነሐሴ", "ጳጉሜ"};

    /**
     * Amharic Names for 7 days in a week.
     */
    public static final String DAYS[] = {"ሰኞ", "ማክሰኞ", "ረቡዕ", "ሐሙስ", "አርብ", "ቅዳሜ", "እሑድ"};

    /**
     * Geez (Ethiopic) numbers from 1-9<br>
     * <u>Remember</u>:- There is no any number in Geez (Ethiopic) that
     * represent {@code Zero (0)}
     */
    public static final String NUMBERS1[] = {"፩", "፪", "፫", "፬", "፭", "፮", "፯", "፰", "፱"};

    /**
     * Geez (Ethiopic) numbers from 10-90<br>
     * <u>Remember</u>:- This Geez (Ethiopic) {@link java.lang.String String}
     * array only contains 10, 20, 30, ..., 90.
     */
    public static final String NUMBERS2[] = {"፲", "፳", "፴", "፵", "፶", "፷", "፸", "፹", "፺"};

    /**
     * Geez (Ethiopic) number representation of 100<br>
     */
    public static final String NUMBERS3[] = {"፻"};

    /**
     * Geez (Ethiopic) number representation of 10000<br>
     */
    public static final String NUMBERS4[] = {"፼"};

    /**
     * Name of some saints memorial.<br>
     */
    public static final String SAINTS[] = {"ልደታ ለማርያም፣ ቅዱስ ራጉኤል", "አባ ጉባ", "በዓታ ለማርያም", "ቅዱስ ዮሐንስ ወንጌላዊ", "አቡነ ገብረመንፈስቅዱስ", "ቁስቋም ማርያም፣ ቅድስት አርሴማ", "ቅድስት ሥላሴ", "አቡነ ኪሮስ፣ አርባዕቱ እንስሳ",
        "ቶማስ", "መስቀለ ኢየሱስ", "ቅድስት ሐና", "ቅዱስ ሚካኤል፣ ቅድስት ክርስቶስ ሰምራ", "ቅዱስ ሩፋኤል", "አቡነ  አረጋዊ፣ ገብረ ክርስቶስ", "ቅዱስ ቂርቆስ", "ኪዳነ ምህረት", "ቅዱስ እስጢፋኖስ", "ቅዱስ ፊሊጶስ", "ቅዱስ ገብርኤል",
        "ሕንፀተ ማርያም", "ቅድስት ማርያም", "ቅዱስ ዑራኤል", "ቅዱስ ጊዮርጊስ", "አቡነ ተክለሃይማኖት", "ቅዱስ መርቆሬዎስ", "ቅዱስ ዮሴፍ፣ አቡነ ሀብተ ማርያም", "መድኀኔዓለም፣ አቡነ መብዓ ጽዮን", "አማኑኤል", "በዓለ ወልድ ", "ቅዱስ ዮሐንስ መጥምቅ"};

    //<editor-fold defaultstate="collapsed" desc="Numeric values for 13 months in a year">
    //</editor-fold>
    public static final int MESKEREM = 0;
    public static final int TIKEMT = 1;
    public static final int HIDAR = 2;
    public static final int TAHESAS = 3;
    public static final int TIR = 4;
    public static final int YEKATIT = 5;
    public static final int MEGABIT = 6;
    public static final int MIYAZYA = 7;
    public static final int GINBOT = 8;
    public static final int SENE = 9;
    public static final int HAMLE = 10;
    public static final int NEHASE = 11;
    public static final int PUAGME = 12;

    //<editor-fold defaultstate="collapsed" desc="Numeric values for 7 days in a week">
    //</editor-fold>
    public static final int MONDAY = 0;
    public static final int TUESDAY = 1;
    public static final int WEDNESDAY = 2;
    public static final int THURSDAY = 3;
    public static final int FRIDAY = 4;
    public static final int SATURDAY = 5;
    public static final int SUNDAY = 6;

    //<editor-fold defaultstate="collapsed" desc="Numeric values for 'Wengelawiyan'">
    //</editor-fold>
    public static final int MATEWOS = 1;
    public static final int MARKOS = 2;
    public static final int LUKAS = 3;
    public static final int YOHANNES = 0;

    /**
     * Returns the 'Wengelawi' of the year.
     *
     * @param year the given year
     * @return 'Wengelawi' of the year.
     * @see #MATEWOS
     * @see #MARKOS
     * @see #LUKAS
     * @see #YOHANNES
     */
    public int getWengelawi(int year) {
        return (AMETE_ALEM + year) % 4;
    }

    /**
     * Returns the name of 'Wengelawi' in the given year.
     *
     * @param wengelawi the given year
     * @return name of 'Wengelawi' in the given year.
     * @see #getWengelawi(int year)
     */
    public String getWengelawiName(int wengelawi) {
        String name[] = {"ዮሐንስ", "ማቴዎስ", "ማርቆስ", "ሉቃስ"};
        return wengelawi < name.length && wengelawi >= 0 ? name[wengelawi] : "";
    }

    /**
     * Returns the first day of the year.
     *
     * @param year the given year.
     * @return the first day of the year from the seven days.
     * @see #MONDAY
     * @see #TUESDAY
     * @see #WEDNESDAY
     * @see #THURSDAY
     * @see #FRIDAY
     * @see #SATURDAY
     * @see #SUNDAY
     */
    public int getFirstDay(int year) {
        return (getRabit(year) + AMETE_ALEM + year) % DAYS.length;
    }

    /**
     * Return the number of days in the given year
     *
     * @param year the given year
     * @return 366 if the 'Wengelawi' is {@link #LUKAS LUKAS} else 365.
     */
    public int getDaysLength(int year) {
        return getWengelawi(year) == LUKAS ? 366 : 365;
    }

    /**
     * Checks whether the given year is leap year or not.
     *
     * @param year the given year.
     * @return true if the year is leap year else false.
     */
    public boolean isLeapYear(int year) {
        return getDaysLength(year) != 365;
    }

    /**
     * Gets the 'Rabit' (one fourth of total years)<br>
     * {@link #getRabit(int) rabit}= ({@link #AMETE_ALEM AMETE_ALEM}+ {@code year})/4
     *
     * @param year the given year
     * @return one fourth of total years(from AD to BC).
     */
    public int getRabit(int year) {
        return (AMETE_ALEM + year) / 4;
    }

    /**
     * Gets 'Wenber' for the given year<br>
     * 'Wenber' is used to find {@link #getAbekte(int) Abekte} and
     * {@link #getMetke(int) Metke}
     *
     * @param year the given year.
     * @return 'Wenber' of the year.
     * @see #getAbekte(int year)
     * @see #getMetke(int year)
     */
    public int getWenber(int year) {
        int qemer[] = {228, 76, 19};
        int wenber = (year + AMETE_ALEM) % 532;

        for (int i = 0; i < qemer.length; i++) {
            while (wenber > qemer[i]) {
                wenber %= qemer[i];
            }
        }
        return (wenber != 0) ? wenber - 1 : 18;
    }

    /**
     * Gets 'Abekte' for the given year.We can get 'Abekte' by<br>
     * The Modular of 'Wenber' times 11 by 30.
     *
     * @param year the given year.
     * @return 'Abekte' of the year.
     * @see #getMetke(int year)
     * @see #getWenber(int year)
     */
    public int getAbekte(int year) {
        return (getWenber(year) * 11) % 30;
    }

    /**
     * Gets 'Metke' for the given year.We can get 'Metke' by<br>
     * The Modular of 'Wenber' times 19 by 30.
     *
     * @param year the given year.
     * @return 'Metke' of the year.
     * @see #getAbekte(int year)
     * @see #getWenber(int year)
     */
    public int getMetke(int year) {
        return getWenber(year) == 0 ? 30 : (getWenber(year) * 19) % 30;
    }

    /**
     * Gets 'Mebaja Hamer' and call {@link #tewsakMatch(int) tewsakMatch(int)}
     * 'Mebaja Hamer' is used to find the starting date of the fasting 'Nenewe'.
     *
     * @param year the given year.
     * @param month month either {@link #MESKEREM MESKEREM} or
     * {@link #TIKEMT TIKEMT}.
     * @return 'Mebaja Hamer' for the given year.
     */
    public int getMebajaHamer(int month, int year) {
        int first = getFirstDay(year);
        int rem = (getMetke(year) + (month * 30) - (1)) % DAYS.length;

        if ((first + rem) <= 6) {
            return tewsakMatch(first + rem);
        } else if ((first + rem) == 7 || (first + rem) == 0) {
            return tewsakMatch(0);
        } else {
            return tewsakMatch(first + rem - 7);
        }
    }

    /**
     * Finds the 'Tewsak' match for days from class {@link Tewsak Tewsak}.
     *
     * @param day the day
     * @return equivalent 'Tewsak'.
     * @see #getTewsak(int year, int month)
     */
    public int tewsakMatch(int day) {
        if (day == MONDAY) {
            return Tewsak.MONDAY;
        } else if (day == TUESDAY) {
            return Tewsak.TUESDAY;
        } else if (day == WEDNESDAY) {
            return Tewsak.WEDNESDAY;
        } else if (day == THURSDAY) {
            return Tewsak.THURSDAY;
        } else if (day == FRIDAY) {
            return Tewsak.FRIDAY;
        } else if (day == SATURDAY) {
            return Tewsak.SATURDAY;
        } else {
            return Tewsak.SUNDAY;
        }
    }

    /**
     * Return the day of holiday 'Meskel' (The finding of true cross). Always it
     * lays on 'Meskerem' 17.
     *
     * @return the day of 'Meskel' (The finding of true cross).
     */
    public int getMeskel() {
        return 17;
    }

    /**
     * Return the starting day of the fast 'Tsge'. Always it lays on 'Meskerem'
     * 25.
     *
     * @return the starting day of the fast 'Tsge'.
     */
    public int getTsege() {
        return 26;
    }

    /**
     * Return the starting day of the fast of 'Nebiyat'. ('Yegena Tsome')Always
     * it lays on 'Hidar' 15.
     *
     * @return the starting day of the fast of 'Nebiyat'.
     */
    public int getTsomeNebiyat() {
        return 75;
    }

    /**
     * 'Sibket' is one week before 'Birhan'.
     *
     * @param year the year to find 'Sibket'
     * @return the day of 'Sibket'.
     */
    public int getSibket(int year) {
        return getBirhan(year) - 7;
    }

    /**
     * 'Birhan' is one week before 'Nolawi'.
     *
     * @param year the year to find 'Birhan'
     * @return the day of 'Birhan'.
     */
    public int getBirhan(int year) {
        return getNolawi(year) - 7;
    }

    /**
     * Returns the day of last Sunday before 'Gena'.
     *
     * @param year the year to find 'Nolawi'.
     * @return the day of last Sunday before 'Gena'.
     */
    public int getNolawi(int year) {
        return getGena(year) - getDayNumber(getGena(year), year) - 1;
    }

    /**
     * Return the day of holiday 'Gena' (Christmas).
     *
     * @param year the year to find 'Gena' (Christmas).
     * @return the day of 'Gena' (Christmas).
     */
    public int getGena(int year) {
        return getWengelawi(year) == YOHANNES ? 118 : 119;
    }

    /**
     * Return the day of the fast of 'Gad' if 'Timket' lays on Wednesday or
     * Friday else return -1.
     *
     * @param year the given year.
     * @return the starting day of the fast of 'Nebiyat'.
     */
    public int getGad(int year) {
        return (getDayName(getTimket(), year).equals(DAYS[2])) || (getDayName(getTimket(), year).equals(DAYS[4]))
                ? getTimket() - 1
                : -1;
    }

    /**
     * Return the day of holiday 'Timket' (Epiphani). Always it lays on 'Tir'
     * 11.
     *
     * @return the day of 'Timket' (Epiphani).
     */
    public int getTimket() {
        return 131;
    }

    /**
     * Gets starting day of the fasting 'Nenewe'
     *
     * @param year the year to find the fast 'Nenewe'.
     * @return the day 'Nenewe' starts.
     */
    public int getNenewe(int year) {
        int nenewe;
        if (getMetke(year) >= 15) {
            if ((getMebajaHamer(MESKEREM, year) + getMetke(year)) > 30) {
                nenewe = (getMebajaHamer(MESKEREM, year) + getMetke(year) % 30) + (YEKATIT * 30);
            } else {
                nenewe = getMebajaHamer(MESKEREM, year) + getMetke(year) + (TIR * 30);
            }
        } else {
            nenewe = getMebajaHamer(TIKEMT, year) + getMetke(year) + (YEKATIT * 30);
        }

        if (nenewe > 171) {
            return nenewe - 30;
        } else {
            return nenewe;
        }
    }

    /**
     * Return the day when fast 'Abey' start in the given year.
     *
     * @param year the year to find the starting day of the fast 'Abey'.
     * @return starting day of the fast 'Abey'.
     */
    public int getAbeyTsome(int year) {
        return getNenewe(year) + 14;
    }

    /**
     * Return the day of 'Debre Zeit' in the given year.
     *
     * @param year the year to find 'Debre Zeit'.
     * @return the day of 'Debre Zeit'.
     */
    public int getDebreZeit(int year) {
        return getAbeyTsome(year) + 27;
    }

    /**
     * Return the day of 'Hosaena' in the given year.
     *
     * @param year the year to find 'Hosaena'.
     * @return the day of 'Debre Zeit'.
     */
    public int getHosaena(int year) {
        return getAbeyTsome(year) + 48;
    }

    /**
     * Return the day of 'Siqlet' (Good Friday) in the given year.
     *
     * @param year the year to find 'Siqlet' (Good Friday).
     * @return the day of 'Siqlet'.
     */
    public int getSiqlet(int year) {
        return getAbeyTsome(year) + 53;
    }

    /**
     * Return the day of 'Tinsae' (Easter) in the given year.
     *
     * @param year the year to find 'Tinsae' (Easter).
     * @return the day of 'Tinsae'.
     */
    public int getTinsae(int year) {
        return getAbeyTsome(year) + 55;
    }

    /**
     * Return the day of 'Rekbe Kahinat' in the given year.
     *
     * @param year the year to find 'Rekbe Kahinat'.
     * @return the day of 'Rekbe Kahinat'.
     */
    public int getRekbeKahinat(int year) {
        return getTinsae(year) + 24;
    }

    /**
     * Return the day of 'Erget' in the given year.
     *
     * @param year the year to find 'Erget'.
     * @return the day of 'Erget'.
     */
    public int getErget(int year) {
        return getTinsae(year) + 39;
    }

    /**
     * Return the day of 'Peraklitos' ('Pentekoste') in the given year.
     *
     * @param year the year to find 'Peraklitos' ('Pentekoste').
     * @return the day of 'Peraklitos'.
     */
    public int getPeraklitos(int year) {
        return getErget(year) + 10;
    }

    /**
     * Return the day when fast of 'Hawariyat' start in the given year.
     *
     * @param year the year to find the starting day of the fast of 'Hawariyat'.
     * @return starting day of the fast of 'Hawariyat'.
     */
    public int getTsomeHawariyat(int year) {
        return getPeraklitos(year) + 1;
    }

    /**
     * Return the day when fast of 'Dihnet' (Wednesday and Friday) start in the
     * given year.
     *
     * @param year the year to find the starting day of the fast of 'Dihnet'.
     * @return starting day of the fast of 'Dihnet'.
     */
    public int getTsomeDihnet(int year) {
        return getTsomeHawariyat(year) + 2;
    }

    /**
     * Return the day when fast of 'Filseta' start in the given year. It always
     * lays on 'Nehase' 1.
     *
     * @return starting day of the fast of 'Dihnet'.
     */
    public int getFelseta() {
        return 331;
    }

    /**
     * Return the starting day of the month in the given year.
     *
     * @param month the given month
     * @param year the given year.
     * @return the starting day of the month.
     */
    public int getMonthStart(int month, int year) {
        return (month <= MONTHS.length)
                ? (getFirstDay(year) + (2 * month)) % 7
                : -1;
    }

    /**
     * If the given day is valid return the day name or a given number in
     * String, else return null.<br> {@code For Example}<br>
     * The 109<sup>th</sup> day of the year 1988 will be Monday.
     *
     * @param day the date in number.
     * @param year the given year.
     * @return date format in String like Monday.
     * @see #getMonthFormat(int day, int year) getMonthFormat(int day, int year)
     * @see #getDisplayName(int day, int year) getDisplayName(int day, int year)
     */
    public String getDayName(int day, int year) {
        int first = getFirstDay(year);
        int rem = (day - 1) % DAYS.length;
        if (day <= getDaysLength(year)) {
            if ((rem + first) <= 6) {
                return DAYS[rem + first];
            } else if ((rem + first) == 7 | (rem + first) == 0) {
                return DAYS[0];
            } else if ((rem + first) > 7) {
                return DAYS[rem + first - 7];
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * Return the number representation for given day of the year.
     * {@code For example:-} Monday is 0, Tuesday is 1 and so on...
     *
     * @param day the day of year.
     * @param year the year to find number representation of the day.
     * @return equivalence number representation for the given day.
     */
    public int getDayNumber(int day, int year) {
        int first = getFirstDay(year);
        int rem = (day - 1) % DAYS.length;
        if (day <= getDaysLength(year)) {
            if ((rem + first) <= 6) {
                return rem + first;
            } else if ((rem + first) == 7 | (rem + first) == 0) {
                return 0;
            } else {
                return rem + first - 7;
            }
        }
        return -1;
    }

    /**
     * If the given day is valid return the date format or a given number in
     * String, else return null.<br> {@code For Example}<br>
     * The 109<sup>th</sup> day of the year will be 'Tahesas' 19.
     *
     * @param day the date in number.
     * @param year the given year.
     * @return date format in String like 'Tahsas' 19.
     * @see #getDayName(int day, int year) getDayName(int day, int year)
     * @see #getDisplayName(int day, int year) getDisplayName(int day, int year)
     */
    public String getMonthFormat(int day, int year) {
        int month = day / 30;
        int days = day % 30;
        if (days == 0) {
            return String.format("%s, %s %d", getDayName(day, year), MONTHS[month - 1], 30);
        } else {
            return day <= getDaysLength(year) ? String.format("%s፣ %s %d", getDayName(day, year), MONTHS[month], days) : null;
        }
    }

    /**
     * If the given day is valid return the date format or a given number in
     * String, else return null.<br> {@code For Example}<br>
     * The Display name of 182<sup>nd</sup> day of the year 1988 will be Monday,
     * Megabit 2, 1988.
     *
     * @param day the date in number.
     * @param year the given year
     * @return date format in String like 'Megabit' 2.
     * @see #getDayName(int day, int year) getDayName(int day, int year)
     * @see #getMonthFormat(int year day, int year) getMonthFormat(int year day,
     * int year)
     */
    public String getDisplayName(int day, int year) {

        return day <= getDaysLength(year)
                ? String.format("%s, %s, %d", getDayName(day, year), getMonthFormat(day, year), year)
                : null;
    }

    /**
     * Return number format of the given date format if it is a valid date
     * format.<br> {@code For Example}<br> {@code toNumber(2, 7, 1988)} will
     * return 182. This means the given date is 182<sup>nd</sup> day of the
     * year.
     *
     * @param day the given day.
     * @param month the given month
     * @param year the given year.
     * @return day in number like 182.
     *
     */
    public int toNumberDay(int day, int month, int year) {

        return ((month - 1) * 30) + day <= getDaysLength(year)
                ? ((month - 1) * 30) + day
                : -1;
    }

    /**
     * Changes the Arabic number into Geez number format.
     *
     * @param num the number in Arabic style
     * @return the equivalent Geez number
     */
    public String toEthiopicNumber(int num) {
        if (num > 0) {
            String numString = String.format("%d", num);
            String ethiopic = "";
            for (int i = numString.length() - 1, a = 0; i >= 0; i--, a++) {
                int current = Integer.parseInt(String.format("%c", numString.charAt(i)));
                int length = 0;
                if (current != 0) {

                    if (a == 4) {
                        ethiopic = NUMBERS1[current - 1] + "" + NUMBERS4[0] + ethiopic;
                    } else if (a == 3) {
                        if (Integer.parseInt(String.format("%c", numString.charAt(i + 1))) != 0) {
                            ethiopic = NUMBERS2[current - 1] + ethiopic;
                        } else {
                            ethiopic = NUMBERS2[current - 1] + NUMBERS3[0] + ethiopic;
                        }
                    } else if (a == 2) {
                        ethiopic = NUMBERS1[current - 1] + "" + NUMBERS3[0] + ethiopic;
                    } else if (a == 1) {
                        ethiopic = NUMBERS2[current - 1] + ethiopic;
                    } else if (a == 0) {
                        ethiopic = NUMBERS1[current - 1] + ethiopic;
                    }

                }
            }

            return ethiopic;
        } else {
            return "";
        }
    }

    /**
     * Converts the given Ethiopian date to Gregorian date.
     *
     * @param date Ethiopian date
     * @param month Ethiopian month
     * @param year Ethiopian year
     * @return {@link java.util.GregorianCalendar GregorianCalendar} for the
     * corresponding Ethiopian date.
     */
    public Calendar toGregorianDate(int date, int month, int year) {
        Calendar calendar = Calendar.getInstance();
        int day = toNumberDay(date, month, year);

        int start = isLeapYear(year - 1) ? 112 : 113;
        int gyear = day >= start ? year + 8 : year + 7;

        calendar.set(Calendar.YEAR, gyear);

        if (day > start) {
            calendar.set(Calendar.DAY_OF_YEAR, day - start + 1);
        } else if (day < start) {
            calendar.set(Calendar.DAY_OF_YEAR, getDaysLength(gyear - 1) - (start - day) + 1);
        } else {
            calendar.set(Calendar.DAY_OF_YEAR, 1);
        }
        return calendar;
    }

    /**
     * Converts the given Gregorian date to Ethiopian.
     *
     * @param gregorian Calendar that represents Gregorian Calendar.
     * @return An integer array that contains Ethiopic day and year.
     */
    public int[] toEthiopicDate(Calendar gregorian) {

        int gday = gregorian.get(Calendar.DAY_OF_YEAR);
        int gyear = gregorian.get(Calendar.YEAR);

        int start = isLeapYear(gyear) ? 255 : 254;
        int eyear = gday >= start ? gregorian.get(Calendar.YEAR) - 7 : gregorian.get(Calendar.YEAR) - 8;

        if (gday > start) {
            return new int[]{gday - start + 1, eyear};
        } else if (gday < start) {
            return new int[]{getDaysLength(eyear) - (start - gday) + 1, eyear};
        } else {
            return new int[]{1, eyear};
        }
    }

    /**
     * Check whether the given Ethiopian date is equals with Computer date.
     *
     * @param date Ethiopian date
     * @param month Ethiopian month
     * @param year Ethiopian year
     * @return true if the Ethiopian date is equals with Computer date else
     * false.
     */
    public boolean isToday(int date, int month, int year) {

        Calendar today = Calendar.getInstance();
        Calendar ethiopic = toGregorianDate(date, month, year);
        return (today.get(Calendar.YEAR) == ethiopic.get(Calendar.YEAR)
                && today.get(Calendar.MONTH) == ethiopic.get(Calendar.MONTH)
                && today.get(Calendar.DAY_OF_MONTH) == ethiopic.get(Calendar.DAY_OF_MONTH));

    }

    /**
     * Returns an array that contains day, month and year which is equivalent
     * for the given day of the year and year
     *
     * @param day day of the year
     * @param year given year to find the formattedDate
     * @return
     */
    public int[] formattedDate(int day, int year) {
        return (day % 30 == 0)
                ? new int[]{30, (day / 30), year}
                : new int[]{day % 30, (day / 30) + 1, year};

    }
}
