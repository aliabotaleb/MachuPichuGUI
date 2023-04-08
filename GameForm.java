import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameForm {
    private JPanel panel1;
    private JLabel diceRollLabel1;
    private JTextField textField1;
    private JButton rollDiceButton;
    private JButton newGameButton;
    private JLabel scoreLabelField;
    private JLabel statusLabelField;
    private JLabel highScoreLabelField;
    private JLabel userNameLabelField;
    private JButton saveGameButton;

    private Player player;

    private Dice dice = new Dice();

   public GameForm(Player player)
    {
        this.player = player;
        setUserNameTextArea(player.getName());
        setHighScoreTextArea(String.valueOf(player.getHighScore()));

        setGameStatusTextArea("Roll dice to start the game");
        setScoreTextArea(String.valueOf(player.getScore()));
         // Add ActionListener to the rollDiceButton
         rollDiceButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    handleRollDice();
                }
         });
    }

    public void handleRollDice() {
        Integer[] diceList = dice.getDiceList();
        textField1.setText(diceList[0] + " " + diceList[1] + " " + diceList[2] + " " + diceList[3] + " " + diceList[4]);

        GameLogic.checkMachuPichu(diceList, this.player);
        setGameStatusTextArea(player.getStatusStr());

        setScoreTextArea(String.valueOf(player.getScore()));

        if (player.getScore() > player.getHighScore()){
            player.setHighScore();
            setHighScoreTextArea(String.valueOf(player.getHighScore()));
        }
    }

    private void setGameStatusTextArea(String text) {
        statusLabelField.setText(text);
    }

    private void setScoreTextArea(String text) {
        scoreLabelField.setText(text);
    }

    private void setHighScoreTextArea(String text) {
        highScoreLabelField.setText(text);
    }

    private void setUserNameTextArea(String text) {
        userNameLabelField.setText(text);
    }

    public void setVisible(boolean visible) {
        JFrame frame = new JFrame("Game Form");
        frame.setContentPane(this.panel1);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(visible);
    }
}
