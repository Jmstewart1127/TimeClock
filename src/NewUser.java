import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

/**
 * Created by Jacob Stewart on 2/16/2017.
 */
public class NewUser
{
    private JTextField textField1;
    public JPanel panel1;
    private JButton addButton;

    Methods call = new Methods();

    public NewUser() {
        textField1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("NewUser");
                String nu = textField1.getText();
                try {
                    call.addNewUser(nu);
                    frame.dispose();
                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }
}
