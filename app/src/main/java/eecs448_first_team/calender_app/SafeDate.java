package eecs448_first_team.calender_app;

/**
 * @Author Paul
 * @Date 20160916
 */
public class SafeDate {
    private int date = 0;

    private SafeDate( int date)
    {
        this.date = date;
    }

    public int getDate() {
        return date;
    }

    /**
     * Constant months to use as variables.
     */
    public static final SafeDate January = new SafeDate(1);
    public static final SafeDate Febuary = new SafeDate(2);
    public static final SafeDate March = new SafeDate(3);
    public static final SafeDate April = new SafeDate(4);
    public static final SafeDate May = new SafeDate(5);
    public static final SafeDate June = new SafeDate(6);
    public static final SafeDate July = new SafeDate(7);
    public static final SafeDate August = new SafeDate(8);
    public static final SafeDate September = new SafeDate(9);
    public static final SafeDate October = new SafeDate(10);
    public static final SafeDate November = new SafeDate(11);
    public static final SafeDate December = new SafeDate(12);

    /**
     * Constant years to use as variables.
     */
    public static final SafeDate fallYear = new SafeDate(2016);
    public static final SafeDate springYear = new SafeDate(2017);

    /**
     * Constant day number to use as variables.
     */
    public static final SafeDate day00 = new SafeDate(0);
    public static final SafeDate day01 = new SafeDate(1);
    public static final SafeDate day02 = new SafeDate(2);
    public static final SafeDate day03 = new SafeDate(3);
    public static final SafeDate day04 = new SafeDate(4);
    public static final SafeDate day05 = new SafeDate(5);
    public static final SafeDate day06 = new SafeDate(6);
    public static final SafeDate day07 = new SafeDate(7);
    public static final SafeDate day08 = new SafeDate(8);
    public static final SafeDate day09 = new SafeDate(9);
    public static final SafeDate day10 = new SafeDate(10);
    public static final SafeDate day28 = new SafeDate(28);
    public static final SafeDate day29 = new SafeDate(29);
    public static final SafeDate day30 = new SafeDate(30);
    public static final SafeDate day31 = new SafeDate(31);
}
