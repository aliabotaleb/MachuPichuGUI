import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;

public class NewUser {
    private JPanel panel1;
    private JPasswordField passwordField;
    private JTextField textField1;
    private JButton enterButton;
    private JButton clearButton;
    private JCheckBox showPasswordCheckBox;

    private boolean isPasswordVisible;

    private ArrayList<String> usernamesList = new ArrayList<>();

    public NewUser() {
        // Add ActionListener to the showPasswordButton
        showPasswordCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                togglePasswordVisibility();
            }
        });

        // Add ActionListener to the enterButton
        enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = textField1.getText();
                char[] password = passwordField.getPassword();

                boolean isValidUsername = validateUsername(username);
                boolean isValidPassword = validatePassword(password);

                if (isValidUsername && isValidPassword) {
                    usernamesList.add(username);
                    JOptionPane.showMessageDialog(null, "User created successfully!");
                    // Launch the game form
                    GameForm gameForm = new GameForm();
                    gameForm.setVisible(true);
                }
            }

        });

        // Add ActionListener to the clearButton
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField1.setText("");
                passwordField.setText("");
            }
        });

    }

    private boolean validateUsername(String username) {
        if (usernamesList.contains(username)) {
            JOptionPane.showMessageDialog(null, "This username is already taken");
            return false;
        }
        return true;
    }

    private boolean validatePassword(char[] password) {
        if (password.length < 8) {
            JOptionPane.showMessageDialog(null, "Password must be at least 8 characters long");
            return false;
        }

        boolean containsUpperCase = false;
        boolean containsNumber = false;
        boolean containsSpecialChar = false;

        for (char c : password) {
            if (Character.isUpperCase(c)) {
                containsUpperCase = true;
            } else if (Character.isDigit(c)) {
                containsNumber = true;
            } else if (!Character.isLetter(c) && !Character.isDigit(c)) {
                containsSpecialChar = true;
            }
        }

        if (!containsUpperCase) {
            JOptionPane.showMessageDialog(null, "Password must contain an upper case character");
            return false;
        }

        if (!containsNumber) {
            JOptionPane.showMessageDialog(null, "Password must contain a numerical character");
            return false;
        }

        if (!containsSpecialChar) {
            JOptionPane.showMessageDialog(null, "Password must contain a special character");
            return false;
        }

        return true;
    }

    private void togglePasswordVisibility() {
        if (!isPasswordVisible) {
            passwordField.setEchoChar((char) 0);
            isPasswordVisible = true;
        } else {
            passwordField.setEchoChar('*');
            isPasswordVisible = false;
        }
    }

    public JPanel getMainPanel() {
        return panel1;
    }

    public static void main(String[] args) {
        // Create a JFrame, set its content pane to the panel1, and display the form
        JFrame frame = new JFrame("New User");
        frame.setContentPane(new NewUser().getMainPanel());
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }


    public void setVisible(boolean visible) {
        JFrame frame = new JFrame("New User");
        frame.setContentPane(this.panel1);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}

