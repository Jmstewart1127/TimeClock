/**
 * Created by Jacob Stewart on 2/7/2017.
 */
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Jacob Stewart on 2/7/2017.
 */
public class TimeClockGUI {


    private JTextField textField1;
    private JTextField textField2;
    private JButton startTime;
    private JButton endTime;
    private JComboBox users;
    private JComboBox tasks;
    private JButton aJButton;
    private JButton addNewTask;
    private JButton addNewUser;
    private JTextArea console;
    private JPanel panel;


    public static String getTime() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String time = sdf.format(cal.getTime());
        return time;
    }

    public static long cumulativeTimeStart() {
        long start = System.currentTimeMillis( );
        return start;
    }

    public static long cumulativeTimeEnd() {
        long end = System.currentTimeMillis( );
        return end;
    }

    public static String cumulativeTime(long start, long end)
    {
        long time = end - start;
        long timeIntoHours = 0;
        String text = "Hours: ";
        if (time >= 3600000)
        {
            timeIntoHours = time / 3600000;
            String output = text + timeIntoHours;
            return output;
        }
        if (time < 3600000 && time > 60000)
        {
            text = "Minutes: ";
            timeIntoHours = time / 60000;
            String output = text + timeIntoHours;
            return output;
        }
        if (time < 60000)
        {
            text = "Seconds: ";
            timeIntoHours = time / 1000;
            String output = text + timeIntoHours;
            return output;
        }
        String output = text + timeIntoHours;

        return output;
    }

    private static void recordEndTime(String time, String time2) throws ClassNotFoundException   // Records start time and end time
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

    private static String consoleOut() throws ClassNotFoundException   // Records start time and end time
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

    public TimeClockGUI() {

        users.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        tasks.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        addNewUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        addNewTask.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        console.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {

            }
        });
        aJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField1.setText("");
                textField2.setText("");
            }
        });
        startTime.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String st = TimeClockGUI.getTime();
                textField1.setText(st);
                TimeClockGUI.cumulativeTimeStart();
            }
        });
        endTime.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String st = textField1.getText();  // start time variable
                String et = TimeClockGUI.getTime();  // end time variable
                try {
                    TimeClockGUI.recordEndTime(st, et);
                    TimeClockGUI.cumulativeTimeEnd();
                    console.setText(TimeClockGUI.cumulativeTime(cumulativeTimeStart(), cumulativeTimeEnd()));
                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                }
                textField2.setText(et);
            }
        });
    }

    public static void main(String[] args)
    {
        JFrame frame = new JFrame("TimeClockGUI");
        frame.setContentPane(new TimeClockGUI().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}




