import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class NewUser {
    private JPanel panel1;
    private JPasswordField passwordField;
    private JTextField textField1;
    private JButton enterButton;
    private JButton clearButton;
    private JCheckBox showPasswordCheckBox;

    private static final String DATA_FILE = "data/data.csv";
    private boolean isPasswordVisible;

    private ArrayList<String> usernamesList = new ArrayList<>();

    public NewUser() {
        // populate usernamesList from data/data.csv
        usernamesList = readPlayerDataFromFile();

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
               handleEnterButton();
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

    private void handleEnterButton() {
        // make username small case
        String username = textField1.getText();
        String password = new String(passwordField.getPassword());

        boolean isValidUsername = validateUsername(username);
        boolean isValidPassword = validatePassword(password);
        if (isValidUsername && isValidPassword) {
            ArrayList<Player> playersArrayList = Players.readPlayerDataFromFile();
            // loop over each player in playersArrayList and check if username is already taken
            Player playerExists =  Players.checkNameExists(playersArrayList, username);
            if (playerExists != null) {
                JOptionPane.showMessageDialog(null, "This username is already taken");
                return;
            }

            // create new player
            Player player = new Player(username + "," + password + ",0");

            Players.writePlayerDataToFile(player, playersArrayList);
            JOptionPane.showMessageDialog(null, "User created successfully!");
            // Launch the game form
            GameForm gameForm = new GameForm(player);
            gameForm.setVisible(true);
        }
    }

    // Read the data from csv
    private ArrayList<String> readPlayerDataFromFile() {
        ArrayList<String> usernames = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(DATA_FILE))) {
            String line = reader.readLine();

            // loop over each line in DATA_FILE and append to players as new player
            while (line != null) {
                String[] data = line.split(",");
                usernames.add(data[0]);
                // read next line
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return usernames;
    }
    private boolean validateUsername(String username) {
        System.out.println(usernamesList);
        // ignore capitalization
        // make minimum length 3

        if (username.length() < 3) {
            JOptionPane.showMessageDialog(null, "Username must be at least 3 characters long");
            return false;
        }

        // make usernamesList small case
        for (int i = 0; i < usernamesList.size(); i++) {
            usernamesList.set(i, usernamesList.get(i).toLowerCase());
        }
         String usernameSmallCase = username.toLowerCase();
        if (usernamesList.contains(usernameSmallCase)) {
            JOptionPane.showMessageDialog(null, "This username is already taken");
            return false;
        }
        return true;
    }

    private boolean validatePassword(String password) {
        if (password.length() < 8) {
            JOptionPane.showMessageDialog(null, "Password must be at least 8 characters long");
            return false;
        }

        boolean containsUpperCase = false;
        boolean containsNumber = false;
        boolean containsSpecialChar = false;

        for (int i = 0; i < password.length(); i++) {
            if (Character.isUpperCase(password.charAt(i))) {
                containsUpperCase = true;
            } else if (Character.isDigit(password.charAt(i))) {
                containsNumber = true;
            } else if (!Character.isLetter(password.charAt(i)) && !Character.isDigit(password.charAt(i))) {
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
        frame.setVisible(visible);
    }
}

