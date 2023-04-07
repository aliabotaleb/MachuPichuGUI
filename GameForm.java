import javax.swing.*;

public class GameForm {
    private JPanel panel1;
    private JLabel diceRollLabel1;
    private JTextField textField1;
    private JButton rollDiceButton;
    private JTextArea darrenTextArea;
    private JTextArea example2TextArea;
    private JTextArea example3TextArea;
    private JButton newGameButton;
    private JButton saveGameButton;
    private JTextArea example1TextArea;


    public void setVisible(boolean visible) {
        JFrame frame = new JFrame("Game Form");
        frame.setContentPane(this.panel1);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
