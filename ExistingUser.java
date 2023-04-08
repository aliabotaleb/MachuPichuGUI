import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

public class ExistingUser {
    private JPanel panel;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton enterButton;
    private JButton clearButton;
    private JCheckBox showPasswordCheckBox;
    private JLabel passwordLabel1;
    private JLabel label2;

    private ArrayList<String[]> data = new ArrayList<>();

    private static final String DATA_FILE = "data/data.csv";
    private boolean isPasswordVisible;

    public ExistingUser() {
        // Load the data from the file
        loadData();

        // Add ItemListener to the showPasswordCheckBox
        showPasswordCheckBox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                togglePasswordVisibility();
            }
        });

        // Add ActionListener to the enterButton
        enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = textField1.getText();
                char[] password = passwordField1.getPassword();

                boolean isValidUser = validateUser(username, password);

                if (isValidUser) {
                    // Launch the game form
                GameForm gameForm = new GameForm();
                    gameForm.setVisible(true);
                } else {
                    int option = JOptionPane.showConfirmDialog(null,
                            "Invalid username or password. Do you want to create a new user?", "Create new user?",
                            JOptionPane.YES_NO_OPTION);
                    if (option == JOptionPane.YES_OPTION) {
                        // Launch the NewUser form
                        NewUser newUserForm = new NewUser();
                        newUserForm.setVisible(true);
                    }
                }
            }
        });

        // Add ActionListener to the clearButton
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField1.setText("");
                passwordField1.setText("");
                showPasswordCheckBox.setSelected(false);
                passwordField1.setEchoChar('*');
                isPasswordVisible = false;
            }
        });
        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.add(usernameLabel);
        panel.add(textField1);
        panel.add(Box.createVerticalStrut(10)); // Add some vertical space between the components
        panel.add(passwordLabel);
        panel.add(passwordField1);
        panel.add(Box.createVerticalStrut(10)); // Add some vertical space between the components
        panel.add(enterButton);
        panel.add(clearButton);
        panel.add(showPasswordCheckBox);




        // Create a JFrame and add the JPanel to it
        JFrame frame = new JFrame("Existing User");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        frame.add(panel);

        // Pack the JFrame to fit its contents and make it visible
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void setVisible(boolean visible) {
        JFrame frame = new JFrame("Existing User");
        frame.setContentPane(this.panel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private boolean validateUser(String username, char[] password) {
        for (String[] record : data) {
            if (record[0].equals(username) && record[1].equals(String.valueOf(password))) {
                return true;
            }
        }
        return false;
    }

    private void loadData() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(DATA_FILE));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] record = line.split(",");
                data.add(record);
            }
            reader.close();
        } catch (IOException e) {
            // The file doesn't exist or cannot be read
            JOptionPane.showMessageDialog(null, "Data file not found or cannot be read. Creating a new data file.");
            try {

                FileWriter writer = new FileWriter(DATA_FILE);
                writer.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void togglePasswordVisibility() {
        if (!isPasswordVisible) {
            passwordField1.setEchoChar((char) 0);
            isPasswordVisible = true;
        } else {
            passwordField1.setEchoChar('*');
            isPasswordVisible = false;
            passwordLabel1 = new JLabel("Password:");
        }
    }

    public static void main(String[] args) {
        ExistingUser existingUser = new ExistingUser();
    }
}


