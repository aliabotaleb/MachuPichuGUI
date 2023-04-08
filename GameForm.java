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

   public GameForm(Player player)
    {
         // Add ActionListener to the rollDiceButton
         rollDiceButton.addActionListener(new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
                // Roll the dice
//               Dice dice = new Dice();
//               Integer[] diceList = dice.getDiceList();
//               String word = Game.checkMachuPichu(diceList,player);
//                darrenTextArea.setText(word);
//                score.setText(String.valueOf(player.getScore()));
//                diceRollLabel1.setText(String.valueOf(diceRoll));
//                // Display the dice roll in the text area
//                darrenTextArea.append(String.valueOf(diceRoll) + " ");
                }
            });
    }

    public void setVisible(boolean visible) {
        JFrame frame = new JFrame("Game Form");
        frame.setContentPane(this.panel1);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
