/**
 * Created by Jacob Stewart on 2/7/2017.
 */

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.Calendar;

/**
 * Created by Jacob Stewart on 2/7/2017.
 */
public class TimeClockGUI {

    private static double start;
    private static double end;
    private static double shiftTime;
    public static double timeToHours;

    private JTextField textField1;
    private JTextField textField2;
    private JButton startTime;
    private JButton endTime;
    public JComboBox users;
    private JComboBox tasks;
    private JButton aJButton;
    private JButton addNewTask;
    private JButton addNewUser;
    private JTextArea console;
    public JPanel panel;

    Methods call = new Methods();

    public TimeClockGUI() {

        users.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    call.selectUser();
                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                }

                

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
                NewUser panel = new NewUser();
                JFrame frame = new JFrame("NewUser");
                frame.setContentPane(panel.panel1);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });
        addNewTask.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NewTask panel = new NewTask();
                JFrame frame = new JFrame("NewTask");
                frame.setContentPane(panel.panel1);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
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
                start = Calendar.getInstance().getTimeInMillis();
                String st = call.getTime();
                textField1.setText(st);
                System.out.println(start);
            }
        });
        endTime.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String st = textField1.getText();  // start time variable
                String et = call.getTime();  // end time variable
                end = Calendar.getInstance().getTimeInMillis();
                timeToHours = (end - start) / 3600000;
                String strStart = Double.toString(timeToHours);

                try {
                    call.recordEndTime(st, et);
                    console.setText(strStart);

                    System.out.println(end);
                    System.out.println(end - start);
                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                }
                textField2.setText(et);
            }
        });
    }

}




