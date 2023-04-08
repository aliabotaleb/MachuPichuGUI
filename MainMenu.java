import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu {
    private JPanel mainPanel;
    private JButton newUserButton;
    private JButton existingUserButton;
    private JLabel mainMenulabel;

    /**
     * constructor for main menu
     */
    public MainMenu() {
        newUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleNewUserButtonClick();
            }
        });

        /**
         * Add ActionListener to the existingUserButton
         */
        existingUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleExistingUserButtonClick();
            }
        });
    }

    /**
     * handle new user button click
     */
    private void handleNewUserButtonClick() {
        //make it go to new user when clicked
        NewUser newUser = new NewUser();
        newUser.setVisible(true);
    }

    /**
     * handle existing user button click
     */
    private void handleExistingUserButtonClick() {
        //make it go to existing user when clicked
        ExistingUser existingUser = new ExistingUser();
//        existingUser.setVisible(true);
    }

        /**
         * main method
         * @param args
         */
    public static void main(String[] args) {
        JFrame frame = new JFrame("Main Menu");
        frame.setContentPane(new MainMenu().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

        /**
         * set visible
         * @param visible
         */
    public void setVisible(boolean visible) {
        JFrame frame = new JFrame("Main Menu");
        frame.setContentPane(this.mainPanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(visible);
    }
}
