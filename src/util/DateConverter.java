package util;

public class DateConverter {
    private static final int farvardinDayDiff = 79;
    private static final int[] sumDaysOfGregorianMonths = {0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334};
    private static final int[] sumDaysOfGregorianMonthsLeap = {0, 31, 60, 91, 121, 152, 182, 213, 244, 274, 305, 335};
    private static int jalaliMonth = 0;
    private static int jalaliDayOfMonth = 0;
    private static int jalaliYear = 0;
    private static int gregorianYear = 0;
    private static int gregorianMonth = 0;
    private static int gregorianDayOfMonth = 0;
    private static final int[] gregorianMonths = {30, 31, 30, 31, 31, 30, 31, 30, 31, 31, 28, 31};
    private static final int[] gregorianMonthsLeap = {30, 31, 30, 31, 31, 30, 31, 30, 31, 31, 29, 31};

    public static String JalaliToGregorian(int y, int m, int d) {
        gregorianYear = y + 621;
        int marchDayDiff;
        if (isLeap(gregorianYear)) {
            marchDayDiff = 12;
        } else {
            marchDayDiff = 11;
        }
        int dayCount = 0;
        if (m >= 1 && m <= 6) {
            dayCount = (m - 1) * 31 + d;
        } else if (m >= 7 && m <= 12) {
            dayCount = (6 * 31) + (m - 7) * 30 + d;
        }
        if (dayCount < marchDayDiff) {
            gregorianDayOfMonth = dayCount + (31 - marchDayDiff);
            gregorianMonth = 3;
        } else {
            int remainDays = dayCount - marchDayDiff;
            int i = 0;
            if (isLeap(gregorianMonth + 1)) {
                while (remainDays > gregorianMonths[i]) {
                    remainDays -= gregorianMonthsLeap[i];
                    i++;
                }
            } else {
                while (remainDays > gregorianMonths[i]) {
                    remainDays -= gregorianMonths[i];
                    i++;
                }
            }

            gregorianDayOfMonth = remainDays;
            if (i > 8) {
                gregorianMonth = i - 8;
                gregorianYear += 1;
            } else {
                gregorianMonth = i + 4;
            }
        }
        return gregorianYear + "-" + gregorianMonth + "-" + gregorianDayOfMonth;

    }

    public static boolean isLeap(int y) {
        if ((y % 4) == 0) {
            if ((y % 100) == 0) {
                return (y % 400) == 0;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public static String GregorianToJalali(String year, String month, String day) {
        int y = Integer.parseInt(year);
        int m = Integer.parseInt(month);
        int d = Integer.parseInt(day);
        int dayCount;
        if (isLeap(y)) {
            dayCount = sumDaysOfGregorianMonthsLeap[m - 1] + d;
        } else {
            dayCount = sumDaysOfGregorianMonths[m - 1] + d;
        }
        int deyDayDiff;
        if (isLeap(y - 1)) {
            deyDayDiff = 11;
        } else {
            deyDayDiff = 10;
        }
        if (dayCount > farvardinDayDiff) {
            dayCount -= farvardinDayDiff;
            if (dayCount <= 186) {
                if (dayCount % 31 == 0) {
                    jalaliMonth = dayCount / 31;
                    jalaliDayOfMonth = 31;
                } else {
                    jalaliMonth = (dayCount / 31) + 1;
                    jalaliDayOfMonth = (dayCount % 31);
                }
            } else {
                dayCount -= 186;
                if (dayCount % 30 == 0) {
                    jalaliMonth = (dayCount / 30) + 6;
                    jalaliDayOfMonth = 30;
                } else {
                    jalaliMonth = (dayCount / 30) + 7;
                    jalaliDayOfMonth = (dayCount % 30);
                }
            }
            jalaliYear = y - 621;
        } else {
            dayCount += deyDayDiff;
            if (dayCount % 30 == 0) {
                jalaliMonth = (dayCount / 30) + 9;
                jalaliDayOfMonth = 30;
            } else {
                jalaliMonth = (dayCount / 30) + 10;
                jalaliDayOfMonth = (dayCount % 30);
            }
            jalaliYear = y - 622;
        }
        return jalaliYear + "/" + jalaliMonth + "/" + jalaliDayOfMonth;
    }
}