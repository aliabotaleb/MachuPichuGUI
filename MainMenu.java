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
        // Implement your logic for handling new user button click here
        System.out.println("New user button clicked");
    }

    private void handleExistingUserButtonClick() {
        // Implement your logic for handling existing user button click here
        System.out.println("Existing user button clicked");
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Main Menu");
        frame.setContentPane(new MainMenu().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
