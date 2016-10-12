

package eecs448_first_team.calender_app;

/**
 * author: Cara Fisher
 * date: 9-18-16
 * purpose: a mobile app page that displays the month decided by the user
 *
 * Many thanks to Android (https://developer.android.com/index.html) for its help
 *
 * Updated by "Team One" for Project 2.
 */

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Calendar;

public class MonthView extends AppCompatActivity implements View.OnClickListener {
    private int year;
    private int month;
    private Calendar cal;

    @Override
    /**
     * Load the Month View for android
     * @param saveInstanceState The previous state to use.
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_month_view);

        // listen for cilcks from any of these buttons
        findViewById(R.id.yearButton).setOnClickListener(this);
        findViewById(R.id.firstWeek).setOnClickListener(this);
        findViewById(R.id.secondWeek).setOnClickListener(this);
        findViewById(R.id.thirdWeek).setOnClickListener(this);
        findViewById(R.id.fourthWeek).setOnClickListener(this);
        findViewById(R.id.fifthWeek).setOnClickListener(this);
        findViewById(R.id.sixthWeek).setOnClickListener(this);
        findViewById(R.id.addDetailsButton2).setOnClickListener(this);

        // load the month state from another view.
        // Defaults to August, 2016
        Intent getToMonth = getIntent();
        year = getToMonth.getIntExtra("year", 2016);
        month = getToMonth.getIntExtra("month", 7);
        cal = new GregorianCalendar(year, month, 1);

        // set the month view text
        TextView textview = (TextView) findViewById(R.id.monthName);
        SimpleDateFormat format = new SimpleDateFormat("MMMM");
        textview.setText(format.format(cal.getTime()));

        // fill the days of the month with info on first day of week and number of days
        int day_of_month = cal.get(Calendar.DAY_OF_WEEK);
        int max_days = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        fillMonth(day_of_month, max_days);
    }

    @Override
    /**
     * Determine what was clicked and act appropriately.
     * @param view The view that was clicked.
     */
    public void onClick(View view) {
        // handle clicks to year button and add details
        // each goes to the correct view.
        if (view.getId() == R.id.yearButton) {
            Intent goToYear = new Intent(this, YearDisplay.class);
            goToYear.putExtra("year", cal.get(Calendar.YEAR));
            goToYear.putExtra("month", cal.get(Calendar.MONTH));
            startActivity(goToYear);
            return;
        } else if (view.getId() == R.id.addDetailsButton2) {
            Intent goToAdd = new Intent(this, AddDetails.class);
            startActivity(goToAdd);
            return;
        }

        Calendar newcal = (Calendar) cal.clone();

        // set the week of month based on which part of the layout was clicked
        // if none, just return
        switch (view.getId()) {
            case R.id.firstWeek:
                newcal.set(Calendar.WEEK_OF_MONTH, 1);
                break;
            case R.id.secondWeek:
                newcal.set(Calendar.WEEK_OF_MONTH, 2);
                break;
            case R.id.thirdWeek:
                newcal.set(Calendar.WEEK_OF_MONTH, 3);
                break;
            case R.id.fourthWeek:
                newcal.set(Calendar.WEEK_OF_MONTH, 4);
                break;
            case R.id.fifthWeek:
                newcal.set(Calendar.WEEK_OF_MONTH, 5);
                break;
            case R.id.sixthWeek:
                newcal.set(Calendar.WEEK_OF_MONTH, 6);
                break;

            default:
                return;
        }

        // pass the correct week and year to the week view
        Intent goToWeek = new Intent(this, WeekView.class);
        goToWeek.putExtra("year", year);
        goToWeek.putExtra("week", newcal.get(Calendar.WEEK_OF_YEAR));
        startActivity(goToWeek);
    }

    /**
     * Fill the text views with the current numbers for the months
     * precondition: start and end are valid integers
     * postcondition: the month in activity_month_view.xml is filled out correctly
     * @param first_day the day the month starts on (1 = Sunday, 2 = Monday, etc.)
     * @param number_of_days the number of days in the month (28 - 31 days)
     */
    public void fillMonth(int first_day, int number_of_days) {
        // Fill out the days startin at the first day of the first week in the month
        if (first_day == Calendar.SUNDAY) { // if Sunday was the 1st day of the month
            TextView t = (TextView) findViewById(R.id.w11);
            t.setText("01");
            t = (TextView) findViewById(R.id.w12);
            t.setText("02");
            t = (TextView) findViewById(R.id.w13);
            t.setText("03");
            t = (TextView) findViewById(R.id.w14);
            t.setText("04");
            t = (TextView) findViewById(R.id.w15);
            t.setText("05");
            t = (TextView) findViewById(R.id.w16);
            t.setText("06");
            t = (TextView) findViewById(R.id.w17);
            t.setText("07");
            t = (TextView) findViewById(R.id.w21);
            t.setText("08");
            t = (TextView) findViewById(R.id.w22);
            t.setText("09");
            t = (TextView) findViewById(R.id.w23);
            t.setText("10");
            t = (TextView) findViewById(R.id.w24);
            t.setText("11");
            t = (TextView) findViewById(R.id.w25);
            t.setText("12");
            t = (TextView) findViewById(R.id.w26);
            t.setText("13");
            t = (TextView) findViewById(R.id.w27);
            t.setText("14");
            t = (TextView) findViewById(R.id.w31);
            t.setText("15");
            t = (TextView) findViewById(R.id.w32);
            t.setText("16");
            t = (TextView) findViewById(R.id.w33);
            t.setText("17");
            t = (TextView) findViewById(R.id.w34);
            t.setText("18");
            t = (TextView) findViewById(R.id.w35);
            t.setText("19");
            t = (TextView) findViewById(R.id.w36);
            t.setText("20");
            t = (TextView) findViewById(R.id.w37);
            t.setText("21");
            t = (TextView) findViewById(R.id.w41);
            t.setText("22");
            t = (TextView) findViewById(R.id.w42);
            t.setText("23");
            t = (TextView) findViewById(R.id.w43);
            t.setText("24");
            t = (TextView) findViewById(R.id.w44);
            t.setText("25");
            t = (TextView) findViewById(R.id.w45);
            t.setText("26");
            t = (TextView) findViewById(R.id.w46);
            t.setText("27");
            t = (TextView) findViewById(R.id.w47);
            t.setText("28");
            if (number_of_days != 28) { // if more than 28 days in the month
                t = (TextView) findViewById(R.id.w51);
                t.setText("29");
                t = (TextView) findViewById(R.id.w52);
                t.setText("30");
                if (number_of_days == 31) { // if more than 30 days in the month
                    t = (TextView) findViewById(R.id.w53);
                    t.setText("31");
                }
            }
        } else if (first_day == Calendar.MONDAY) { // if Monday was the 1st of the month
            TextView t = (TextView) findViewById(R.id.w12);
            t.setText("01");
            t = (TextView) findViewById(R.id.w13);
            t.setText("02");
            t = (TextView) findViewById(R.id.w14);
            t.setText("03");
            t = (TextView) findViewById(R.id.w15);
            t.setText("04");
            t = (TextView) findViewById(R.id.w16);
            t.setText("05");
            t = (TextView) findViewById(R.id.w17);
            t.setText("06");
            t = (TextView) findViewById(R.id.w21);
            t.setText("07");
            t = (TextView) findViewById(R.id.w22);
            t.setText("08");
            t = (TextView) findViewById(R.id.w23);
            t.setText("09");
            t = (TextView) findViewById(R.id.w24);
            t.setText("10");
            t = (TextView) findViewById(R.id.w25);
            t.setText("11");
            t = (TextView) findViewById(R.id.w26);
            t.setText("12");
            t = (TextView) findViewById(R.id.w27);
            t.setText("13");
            t = (TextView) findViewById(R.id.w31);
            t.setText("14");
            t = (TextView) findViewById(R.id.w32);
            t.setText("15");
            t = (TextView) findViewById(R.id.w33);
            t.setText("16");
            t = (TextView) findViewById(R.id.w34);
            t.setText("17");
            t = (TextView) findViewById(R.id.w35);
            t.setText("18");
            t = (TextView) findViewById(R.id.w36);
            t.setText("19");
            t = (TextView) findViewById(R.id.w37);
            t.setText("20");
            t = (TextView) findViewById(R.id.w41);
            t.setText("21");
            t = (TextView) findViewById(R.id.w42);
            t.setText("22");
            t = (TextView) findViewById(R.id.w43);
            t.setText("23");
            t = (TextView) findViewById(R.id.w44);
            t.setText("24");
            t = (TextView) findViewById(R.id.w45);
            t.setText("25");
            t = (TextView) findViewById(R.id.w46);
            t.setText("26");
            t = (TextView) findViewById(R.id.w47);
            t.setText("27");
            t = (TextView) findViewById(R.id.w51);
            t.setText("28");
            if (number_of_days != 28) { // if more than 28 days in the month
                t = (TextView) findViewById(R.id.w52);
                t.setText("29");
                t = (TextView) findViewById(R.id.w53);
                t.setText("30");
                if (number_of_days == 31) { // if more than 30 days in the month
                    t = (TextView) findViewById(R.id.w54);
                    t.setText("31");
                }
            }
        } else if (first_day == Calendar.TUESDAY) { // if Tuesday was the 1st of the month
            TextView t = (TextView) findViewById(R.id.w13);
            t.setText("01");
            t = (TextView) findViewById(R.id.w14);
            t.setText("02");
            t = (TextView) findViewById(R.id.w15);
            t.setText("03");
            t = (TextView) findViewById(R.id.w16);
            t.setText("04");
            t = (TextView) findViewById(R.id.w17);
            t.setText("05");
            t = (TextView) findViewById(R.id.w21);
            t.setText("06");
            t = (TextView) findViewById(R.id.w22);
            t.setText("07");
            t = (TextView) findViewById(R.id.w23);
            t.setText("08");
            t = (TextView) findViewById(R.id.w24);
            t.setText("09");
            t = (TextView) findViewById(R.id.w25);
            t.setText("10");
            t = (TextView) findViewById(R.id.w26);
            t.setText("11");
            t = (TextView) findViewById(R.id.w27);
            t.setText("12");
            t = (TextView) findViewById(R.id.w31);
            t.setText("13");
            t = (TextView) findViewById(R.id.w32);
            t.setText("14");
            t = (TextView) findViewById(R.id.w33);
            t.setText("15");
            t = (TextView) findViewById(R.id.w34);
            t.setText("16");
            t = (TextView) findViewById(R.id.w35);
            t.setText("17");
            t = (TextView) findViewById(R.id.w36);
            t.setText("18");
            t = (TextView) findViewById(R.id.w37);
            t.setText("19");
            t = (TextView) findViewById(R.id.w41);
            t.setText("20");
            t = (TextView) findViewById(R.id.w42);
            t.setText("21");
            t = (TextView) findViewById(R.id.w43);
            t.setText("22");
            t = (TextView) findViewById(R.id.w44);
            t.setText("23");
            t = (TextView) findViewById(R.id.w45);
            t.setText("24");
            t = (TextView) findViewById(R.id.w46);
            t.setText("25");
            t = (TextView) findViewById(R.id.w47);
            t.setText("26");
            t = (TextView) findViewById(R.id.w51);
            t.setText("27");
            t = (TextView) findViewById(R.id.w52);
            t.setText("28");
            if (number_of_days != 28) { // if more than 28 days in the month
                t = (TextView) findViewById(R.id.w53);
                t.setText("29");
                t = (TextView) findViewById(R.id.w54);
                t.setText("30");
                if (number_of_days == 31) { // if more than 30 days in the month
                    t = (TextView) findViewById(R.id.w55);
                    t.setText("31");
                }
            }
        } else if(first_day == Calendar.WEDNESDAY) { // if Wednesday was the 1st of the month
            TextView t = (TextView) findViewById(R.id.w14);
            t.setText("01");
            t = (TextView) findViewById(R.id.w15);
            t.setText("02");
            t = (TextView) findViewById(R.id.w16);
            t.setText("03");
            t = (TextView) findViewById(R.id.w17);
            t.setText("04");
            t = (TextView) findViewById(R.id.w21);
            t.setText("05");
            t = (TextView) findViewById(R.id.w22);
            t.setText("06");
            t = (TextView) findViewById(R.id.w23);
            t.setText("07");
            t = (TextView) findViewById(R.id.w24);
            t.setText("08");
            t = (TextView) findViewById(R.id.w25);
            t.setText("09");
            t = (TextView) findViewById(R.id.w26);
            t.setText("10");
            t = (TextView) findViewById(R.id.w27);
            t.setText("11");
            t = (TextView) findViewById(R.id.w31);
            t.setText("12");
            t = (TextView) findViewById(R.id.w32);
            t.setText("13");
            t = (TextView) findViewById(R.id.w33);
            t.setText("14");
            t = (TextView) findViewById(R.id.w34);
            t.setText("15");
            t = (TextView) findViewById(R.id.w35);
            t.setText("16");
            t = (TextView) findViewById(R.id.w36);
            t.setText("17");
            t = (TextView) findViewById(R.id.w37);
            t.setText("18");
            t = (TextView) findViewById(R.id.w41);
            t.setText("19");
            t = (TextView) findViewById(R.id.w42);
            t.setText("20");
            t = (TextView) findViewById(R.id.w43);
            t.setText("21");
            t = (TextView) findViewById(R.id.w44);
            t.setText("22");
            t = (TextView) findViewById(R.id.w45);
            t.setText("23");
            t = (TextView) findViewById(R.id.w46);
            t.setText("24");
            t = (TextView) findViewById(R.id.w47);
            t.setText("25");
            t = (TextView) findViewById(R.id.w51);
            t.setText("26");
            t = (TextView) findViewById(R.id.w52);
            t.setText("27");
            t = (TextView) findViewById(R.id.w53);
            t.setText("28");
            if(number_of_days != 28) { // if more than 28 days in the month
                t = (TextView) findViewById(R.id.w54);
                t.setText("29");
                t = (TextView) findViewById(R.id.w55);
                t.setText("30");
                if (number_of_days == 31) { // if more than 30 days in the month
                    t = (TextView) findViewById(R.id.w56);
                    t.setText("31");
                }
            }
        } else if(first_day == Calendar.THURSDAY) { // if Thursday was the 1st of the month
            TextView t = (TextView) findViewById(R.id.w15);
            t.setText("01");
            t = (TextView) findViewById(R.id.w16);
            t.setText("02");
            t = (TextView) findViewById(R.id.w17);
            t.setText("03");
            t = (TextView) findViewById(R.id.w21);
            t.setText("04");
            t = (TextView) findViewById(R.id.w22);
            t.setText("05");
            t = (TextView) findViewById(R.id.w23);
            t.setText("06");
            t = (TextView) findViewById(R.id.w24);
            t.setText("07");
            t = (TextView) findViewById(R.id.w25);
            t.setText("08");
            t = (TextView) findViewById(R.id.w26);
            t.setText("09");
            t = (TextView) findViewById(R.id.w27);
            t.setText("10");
            t = (TextView) findViewById(R.id.w31);
            t.setText("11");
            t = (TextView) findViewById(R.id.w32);
            t.setText("12");
            t = (TextView) findViewById(R.id.w33);
            t.setText("13");
            t = (TextView) findViewById(R.id.w34);
            t.setText("14");
            t = (TextView) findViewById(R.id.w35);
            t.setText("15");
            t = (TextView) findViewById(R.id.w36);
            t.setText("16");
            t = (TextView) findViewById(R.id.w37);
            t.setText("17");
            t = (TextView) findViewById(R.id.w41);
            t.setText("18");
            t = (TextView) findViewById(R.id.w42);
            t.setText("19");
            t = (TextView) findViewById(R.id.w43);
            t.setText("20");
            t = (TextView) findViewById(R.id.w44);
            t.setText("21");
            t = (TextView) findViewById(R.id.w45);
            t.setText("22");
            t = (TextView) findViewById(R.id.w46);
            t.setText("23");
            t = (TextView) findViewById(R.id.w47);
            t.setText("24");
            t = (TextView) findViewById(R.id.w51);
            t.setText("25");
            t = (TextView) findViewById(R.id.w52);
            t.setText("26");
            t = (TextView) findViewById(R.id.w53);
            t.setText("27");
            t = (TextView) findViewById(R.id.w54);
            t.setText("28");
            if (number_of_days != 28) { // if more than 28 days in the month
                t = (TextView) findViewById(R.id.w55);
                t.setText("29");
                t = (TextView) findViewById(R.id.w56);
                t.setText("30");
                if (number_of_days == 31) { // if more than 30 days in the month
                    t = (TextView) findViewById(R.id.w57);
                    t.setText("31");
                }
            }
        } else if (first_day == Calendar.FRIDAY) { // if Friday was the 1st of the month
            TextView t = (TextView) findViewById(R.id.w16);
            t.setText("01");
            t = (TextView) findViewById(R.id.w17);
            t.setText("02");
            t = (TextView) findViewById(R.id.w21);
            t.setText("03");
            t = (TextView) findViewById(R.id.w22);
            t.setText("04");
            t = (TextView) findViewById(R.id.w23);
            t.setText("05");
            t = (TextView) findViewById(R.id.w24);
            t.setText("06");
            t = (TextView) findViewById(R.id.w25);
            t.setText("07");
            t = (TextView) findViewById(R.id.w26);
            t.setText("08");
            t = (TextView) findViewById(R.id.w27);
            t.setText("09");
            t = (TextView) findViewById(R.id.w31);
            t.setText("10");
            t = (TextView) findViewById(R.id.w32);
            t.setText("11");
            t = (TextView) findViewById(R.id.w33);
            t.setText("12");
            t = (TextView) findViewById(R.id.w34);
            t.setText("13");
            t = (TextView) findViewById(R.id.w35);
            t.setText("14");
            t = (TextView) findViewById(R.id.w36);
            t.setText("15");
            t = (TextView) findViewById(R.id.w37);
            t.setText("16");
            t = (TextView) findViewById(R.id.w41);
            t.setText("17");
            t = (TextView) findViewById(R.id.w42);
            t.setText("18");
            t = (TextView) findViewById(R.id.w43);
            t.setText("19");
            t = (TextView) findViewById(R.id.w44);
            t.setText("20");
            t = (TextView) findViewById(R.id.w45);
            t.setText("21");
            t = (TextView) findViewById(R.id.w46);
            t.setText("22");
            t = (TextView) findViewById(R.id.w47);
            t.setText("23");
            t = (TextView) findViewById(R.id.w51);
            t.setText("24");
            t = (TextView) findViewById(R.id.w52);
            t.setText("25");
            t = (TextView) findViewById(R.id.w53);
            t.setText("26");
            t = (TextView) findViewById(R.id.w54);
            t.setText("27");
            t = (TextView) findViewById(R.id.w55);
            t.setText("28");
            if (number_of_days != 28) { // if more than 28 days in the month
                t = (TextView) findViewById(R.id.w56);
                t.setText("29");
                t = (TextView) findViewById(R.id.w57);
                t.setText("30");
                if (number_of_days == 31) { // if more than 30 days in the month
                    t = (TextView) findViewById(R.id.w61);
                    t.setText("31");
                }
            }
        } else if(first_day == Calendar.SATURDAY) { // if Saturday was the 1st of the month
            TextView t = (TextView) findViewById(R.id.w17);
            t.setText("01");
            t = (TextView) findViewById(R.id.w21);
            t.setText("02");
            t = (TextView) findViewById(R.id.w22);
            t.setText("03");
            t = (TextView) findViewById(R.id.w23);
            t.setText("04");
            t = (TextView) findViewById(R.id.w24);
            t.setText("05");
            t = (TextView) findViewById(R.id.w25);
            t.setText("06");
            t = (TextView) findViewById(R.id.w26);
            t.setText("07");
            t = (TextView) findViewById(R.id.w27);
            t.setText("08");
            t = (TextView) findViewById(R.id.w31);
            t.setText("09");
            t = (TextView) findViewById(R.id.w32);
            t.setText("10");
            t = (TextView) findViewById(R.id.w33);
            t.setText("11");
            t = (TextView) findViewById(R.id.w34);
            t.setText("12");
            t = (TextView) findViewById(R.id.w35);
            t.setText("13");
            t = (TextView) findViewById(R.id.w36);
            t.setText("14");
            t = (TextView) findViewById(R.id.w37);
            t.setText("15");
            t = (TextView) findViewById(R.id.w41);
            t.setText("16");
            t = (TextView) findViewById(R.id.w42);
            t.setText("17");
            t = (TextView) findViewById(R.id.w43);
            t.setText("18");
            t = (TextView) findViewById(R.id.w44);
            t.setText("19");
            t = (TextView) findViewById(R.id.w45);
            t.setText("20");
            t = (TextView) findViewById(R.id.w46);
            t.setText("21");
            t = (TextView) findViewById(R.id.w47);
            t.setText("22");
            t = (TextView) findViewById(R.id.w51);
            t.setText("23");
            t = (TextView) findViewById(R.id.w52);
            t.setText("24");
            t = (TextView) findViewById(R.id.w53);
            t.setText("25");
            t = (TextView) findViewById(R.id.w54);
            t.setText("26");
            t = (TextView) findViewById(R.id.w55);
            t.setText("27");
            t = (TextView) findViewById(R.id.w56);
            t.setText("28");
            if (number_of_days != 28) { // if more than 28 days in the month
                t = (TextView) findViewById(R.id.w57);
                t.setText("29");
                t = (TextView) findViewById(R.id.w61);
                t.setText("30");
                if (number_of_days == 31) { // if more than 30 days in the month
                    t = (TextView) findViewById(R.id.w62);
                    t.setText("31");
                }
            }
        }
    }

    /**
     * Event called when a saved instance is saved. This uses the same year, month as in onCreate.
     * @param savedInstanceState Bundles the values of of data and array and saves them once the activity is left
     * @see   : https://developer.android.com/training/basics/activity-lifecycle/recreating.html
     */
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt("year", year);
        savedInstanceState.putInt("month", month);
        super.onSaveInstanceState(savedInstanceState);
    }
}
