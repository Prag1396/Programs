//Name: Pragun Sharma
//UserName: psharma5
//$Id$

//NAME
//    jcal
//USAGE
//      jcal[[-locale][month][year]]
//DESCRIPTION
//      prints out the month or year in the locale specified by the user
//
import  java.util.GregorianCalendar;
import java.util.Locale;
import static java.lang.System.*;
import static java.util.Calendar.*;
import java.text.DateFormatSymbols;

class jcal {
    static final int MONTHS_IN_YEAR = 12;
    static final int WEEKS_IN_MONTH =  6;
    static final int DAYS_IN_WEEK   =  7;
    public static Locale locale = null;
    public static DateFormatSymbols dfs;
    static final GregorianCalendar CHANGE_DATE =
    new GregorianCalendar (1752, SEPTEMBER, 14);
    public static GregorianCalendar cal = new GregorianCalendar();
    public static int calmonth;
    public static int calyear;
    public static String[] DAY_OF_WEEK=
    {"Su","Mo","Tu","We","Th","Fr","Sa"};



    public static void main (String[] args) {
        //Create a Gregorian Calendar with British Empire changeover
        locale = new Locale ("en");
        dfs = new DateFormatSymbols(locale);

        cal.setGregorianChange (CHANGE_DATE.getTime());
        int [][] month=
        new int[WEEKS_IN_MONTH][DAYS_IN_WEEK];
        int[][][] year =
        new int[MONTHS_IN_YEAR][WEEKS_IN_MONTH][DAYS_IN_WEEK];

        if(args.length == 0) {
            //Uses the current month and year
            //if not specified by the user
            calmonth = cal.get(GregorianCalendar.MONTH);
            calyear = cal.get(GregorianCalendar.YEAR);
            cal.set(calyear,calmonth, 1);
            //To Constructs a month
            month = make_month(calmonth, calyear);
            //To print a month
            print_month(month);
        }

        else if (args.length == 1) {
            //Checks if args length is equal to 1
            try {
                if(Integer.parseInt(args[0]) > 0
                   && Integer.parseInt(args[0])<10000)
                    // Checks if the input is in range
                    calyear = Integer.parseInt(args[0]);

            } catch (NumberFormatException e) {

              System.out.println("jcal: year " + calyear
                            + " is not in range 1..9999");
                //Prints out error message if input is out of range

            }
            //Sets the Calendar to the beginning of the year
            cal.set (calyear, JANUARY, 1);
            year = make_year(year); //To Construct a year
            print_year(year); //To print a year

        }

        else if (args.length == 2) {
            //Checks if length is equal to 2
            try {
                if(Integer.parseInt(args[1]) > 0
                   && Integer.parseInt(args[1])<10000)
                    //Checks range of input

                    calyear = Integer.parseInt(args[1]);

            } catch (NumberFormatException e) {

                System.out.println("jcal: year " + calyear
                            + " is not in range 1..9999");
                //Prints out error message if input is out of range
            }

            try {
                if(Integer.parseInt(args[0]) > 0
                   && Integer.parseInt(args[0])<13)
                    //Checks range of input for month

                    calmonth = Integer.parseInt(args[0]) - 1;

            } catch (NumberFormatException e) {

                System.out.println("jcal: " + calmonth
                            + " is neither a month number (1..12) nor a name");
                //Prints out error message if input is out of range
            }

            //Sets the calendar to the month and year specified by user
            cal.set (calyear, calmonth, 1);
            month = make_month(calmonth, calyear); //To Constructs a month
            print_month(month); //To print a month
        }

        else if (args.length == 3) {
            //Checks if length is 3
            try {
                if(Integer.parseInt(args[2]) > 0 &&
                   Integer.parseInt(args[2])<10000)
                    //Checks range of input for year
                    calyear = Integer.parseInt(args[2]);

            } catch(NumberFormatException e) {

                System.out.println("jcal: year " + calyear
                            + " is not in range 1..9999");
                //Prints out error message if input is out of range
            }


            try {
                if(Integer.parseInt(args[1]) > 0
                   && Integer.parseInt(args[1])<13)
                    //Checks range of inout for month

                    calmonth = Integer.parseInt(args[1]) - 1;

            } catch(NumberFormatException e) {

                System.out.println("jcal: " + calmonth
                            + " is neither a month number (1..12) nor a name");
                //Prints out error message if input is out of range
            }
            //Assigns args[0] as the new locale
            locale = new Locale(args[0].substring(1,args[0].length()));

            //DateFormatSymbols object gets associated with the locale
            dfs = new DateFormatSymbols(locale);
            //Sets the calendar to the month,year specified by the user
            cal.set (calyear, calmonth, 1);
            //To Constructs the month
            month = make_month(calmonth, calyear);
            //To print the month
            print_month(month);
        }
        else {
            System.out.println("jcal: year " + calyear
                        + " is not in range 1..9999");
            //Prints out this message if args.length is not 1,2 or 3
        }
    }

    public static int[][][] make_year(int[][][] yearArray) {
        //Constructs a year by calling make_month 12 times
        for(int month= 0; month<yearArray.length; month++)
            yearArray[month] = make_month(month,calyear);
        return yearArray;
    }

    public static String getMonthName(int monthparameter) {
        //Returns the appropriate monthname to print
        String[] monthNames = dfs.getMonths();
        return monthNames[monthparameter-1];
    }
    public static int[][] make_month(int calmonth, int calyear) {
        //Constructs a 2D month
        int weekCount = 0;
        int[][] month1 = new int[WEEKS_IN_MONTH][DAYS_IN_WEEK];
        //New Month inistialized

        while (calmonth == cal.get (GregorianCalendar.MONTH)) {
            //The loop will continue till it is
            //iterating over the same month
            int weekday = (cal.get (GregorianCalendar.DAY_OF_WEEK))-1;
            int calday = cal.get (GregorianCalendar.DAY_OF_MONTH);
            //Assigining the positions in the array
            month1[weekCount][weekday] = calday;


            if (weekday == 6) {
                weekCount++; //New week initialized
            }
            cal.add (GregorianCalendar.DAY_OF_MONTH, 1);
            //Adds one day to the current day
        }
        return month1;
        //returns the data structure
    }

    public static void print_day_week() {
        //Prints the days for the week passed onto it
        String[] weekDayNames = dfs.getWeekdays();
        String shortDayNames = "";
        for(int i=1; i<=DAYS_IN_WEEK; i++) {
            shortDayNames =
            shortDayNames.concat(weekDayNames[i].substring(0,2)+" ");
        }
        out.printf("%s",shortDayNames);
        //Prints out the first two letters
        //for the day in the specified locale

    }




    public static void print_week(int[][] month, int week) {
        //Prints week x for month x
        for(int day=0;day<month[week].length; day++) {
            if(month[week][day] != 0)
                out.printf(day==0 ?"%2d ":"%2d ", month[week][day]);
            //Prints out the number stored if position is not 0
            else
                out.printf(day == 0 ? "%2s " : "%2s "," ");
            //Prints out a space if locations is 0
        }
    }

    public static void print_month(int[][] month) {
        //Prints the month specified by the user
        alignMonth(calmonth);
        //Helps in aligning a month in a row
        //and the white-spaces
        out.printf("%3s %4d%n", getMonthName(calmonth+1), calyear);
        //Prints out month name and year
        print_day_week();
        //Calls the function to print the days
        out.printf("%n");
        for(int week =0; week < month.length; week++) {
            print_week(month,week);
            //Calls the function to print
            //the calendar with the numbers
            out.printf("%n");
        }
    }



    public static void print_year(int[][][] yearArray) {
        //Prints a year specified by the user
        int monthpointer=0;
        out.printf("%36d%n%n",calyear);
        //Prints out the year
        for(int month=1; month<yearArray.length+1;month++) {
            alignmonthYear(month-1);
            //Helps in aligning a year and the white-spaces
            out.printf("%2s ",getMonthName(month));
            //Calls the functions which returns the month name
            //corresponding to the monthparameter
            if(month%3 == 0) {
                //Generates a new line if 3 months have already
                //been printed in the same line
                out.printf("%n");
                for(int d=0;d<3;d++) {
                    print_day_week();
                    //Calls the function to print the days
                    //in the specified locale
                    out.printf("%2s ","");
                }
                out.printf("%n");
                for(int week=0; week<6; week++) {
                    for(monthpointer = month -3;
                        monthpointer<month ; monthpointer++) {
                        print_week(yearArray[monthpointer],week);
                        //Calls the function to print the
                        //calendar with numbers
                        out.printf("%2s ","");
                    }
                    out.printf("%n");
                }
            }
        }
    }

    public static void alignMonth(int monthparameter) {
        //Controls the alignment on the
        //screen ie the whitespaces for a month
        if(monthparameter == 8)
            out.printf("%3s","");
        else if(monthparameter == 0 || monthparameter == 1 ||
                monthparameter == 9 || monthparameter == 10 || monthparameter == 11)
            out.printf("%4s","");
        else if(monthparameter == 2
                || monthparameter == 3 || monthparameter == 7)
            out.printf("%5s","");
        else if(monthparameter == 4 || monthparameter == 5
                || monthparameter == 6)
            out.printf("%6s","");
    }



    public static void alignmonthYear(int  monthparameter) {
        //Controls the alignment on the screen
        //ie the whitespaces for the whole year
        if(monthparameter == 0 || monthparameter == 9)
            out.printf("%6s","");
        else if(monthparameter == 1 || monthparameter == 10
                || monthparameter == 11)
            out.printf(" %15s","");
        else if(monthparameter == 2)
            out.printf("%16s","");
        else if(monthparameter == 3 || monthparameter == 6)
            out.printf("%6s","");
        else if(monthparameter == 4 || monthparameter == 5)
            out.printf("%19s","");
        else if(monthparameter == 7)
            out.printf("%19s","");
        else if(monthparameter == 8)
            out.printf("%17s","");
    }


}
