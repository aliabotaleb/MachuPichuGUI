import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu {
    private JPanel mainPanel;
    private JButton newUserButton;
    private JButton existingUserButton;
    private JLabel mainMenulabel;


    public MainMenu() {
        newUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleNewUserButtonClick();
            }
        });

        existingUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleExistingUserButtonClick();
            }
        });
    }

    private void handleNewUserButtonClick() {
        //make it go to new user when clicked
        NewUser newUser = new NewUser();
        newUser.setVisible(true);
    }

    private void handleExistingUserButtonClick() {
        //make it go to existing user when clicked
        ExistingUser existingUser = new ExistingUser();
//        existingUser.setVisible(true);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Main Menu");
        frame.setContentPane(new MainMenu().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
