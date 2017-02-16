import java.sql.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Jacob Stewart on 2/15/2017.
 */
public class Methods
{

    public double val;

    public static String getTime() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String time = sdf.format(cal.getTime());
        return time;
    }


    public static void recordEndTime(String time, String time2) throws ClassNotFoundException   // Records start time and end time
    {                                                                                            // displays recorded entry data in console
        // load the sqlite-JDBC driver using the current class loader
        Class.forName("org.sqlite.JDBC");

        Connection connection = null;
        try {
            // create a database connection
            connection = DriverManager.getConnection("jdbc:sqlite:time.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.
            statement.executeUpdate(        // create table
                    "CREATE TABLE IF NOT EXISTS time " +
                            "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                            " username TEXT,taskName TEXT, " +
                            "startTime TEXT, endTime TEXT)"
            );

            statement.executeUpdate("INSERT INTO time(username, taskName, startTime, endTime) " +
                    "VALUES('', '', '" + time + "', '" + time2 + "')");  // insert into table

        } catch (SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e);
            }
        }
    }

    public static String consoleOut() throws ClassNotFoundException   // Records start time and end time
    {                                                                                            // displays recorded entry data in console
        // load the sqlite-JDBC driver using the current class loader
        String start = "Start Time: ";
        String end = "End Time: ";

        Class.forName("org.sqlite.JDBC");
        Connection connection = null;
        try {
            // create a database connection
            connection = DriverManager.getConnection("jdbc:sqlite:time.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.


            ResultSet rs = statement.executeQuery("SELECT * FROM time");   // select from table variable
            while (rs.next()) {
                System.out.println("id = " + rs.getInt("id"));
                System.out.println("Start = " + rs.getString("startTime"));   // read the result set
                System.out.println("End   = " + rs.getString("endTime"));
                start = start + rs.getString("startTime") + "\n End Time: " +
                        rs.getString("endTime") + "\n";
            }

        } catch (SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e);
            }
        }
        return start;
    }

}
