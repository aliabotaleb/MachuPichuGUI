import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameForm {
    private JPanel panel1;
    private JLabel diceRollLabel1;
    private JTextField textField1;
    private JButton rollDiceButton;
    private JTextArea darrenTextArea;
    private JTextArea gameStatusTextArea;
    private JTextArea scoreTextArea;
    private JButton newGameButton;
    private JButton saveGameButton;
    private JTextArea highScoreTextArea;

    private Player player;

    private Dice dice = new Dice();

   public GameForm(Player player)
    {
        this.player = player;
         // Add ActionListener to the rollDiceButton
         rollDiceButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    handleRollDice();
                }
            });
    }

    public void handleRollDice()
    {
        Integer[] diceList = dice.getDiceList();
        GameLogic.checkMachuPichu(diceList, this.player);
    }

    public void setVisible(boolean visible) {
        JFrame frame = new JFrame("Game Form");
        frame.setContentPane(this.panel1);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(visible);
    }
}
