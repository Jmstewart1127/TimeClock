import javax.swing.*;

/**
 * Created by Jacob Stewart on 2/15/2017.
 */
public class Main
{
    public static void main(String[] args)
    {
        TimeClockGUI panel = new TimeClockGUI();
        JFrame frame = new JFrame("TimeClockGUI");
        frame.setContentPane(panel.panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
