/**
 * Created by Jacob Stewart on 2/7/2017.
 */
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

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
    public JPanel panel;

    Methods call = new Methods();

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
                String st = call.getTime();
                textField1.setText(st);
                call.cumulativeTimeStart();
            }
        });
        endTime.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String st = textField1.getText();  // start time variable
                String et = call.getTime();  // end time variable
                try {
                    call.recordEndTime(st, et);
                    call.cumulativeTimeEnd();
                    console.setText(call.cumulativeTime(call.cumulativeTimeStart(), call.cumulativeTimeEnd()));
                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                }
                textField2.setText(et);
            }
        });
    }

}




