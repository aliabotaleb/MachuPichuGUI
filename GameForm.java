import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GameForm {
    private JPanel panel1;
    private JButton rollDiceButton;
    private JButton newGameButton;
    private JButton saveGameButton;
    private JLabel rollsLabelField;
    private JLabel scoreLabelField;
    private JLabel statusLabelField;
    private JLabel highScoreLabelField;
    private JLabel userNameLabelField;

    private Player player;

    private Dice dice = new Dice();
    /**
     * constructor for game form
     * @param player
     */
   public GameForm(Player player)
    {
        this.player = player;
        setUserNameLabelField(player.getName());
        setHighScoreLabelField(String.valueOf(player.getHighScore()));

        setGameStatusLabelField("Waiting for game to start");
        setScoreLabelField(String.valueOf(player.getScore()));
         // Add ActionListener to the rollDiceButton
         rollDiceButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    handleRollDice();
                }
         });
        // Add ActionListener to the newGameButton
        /**
         * Add ActionListener to the saveGameButton
         */
        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleNewGame();
            }
        });

        saveGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSaveGame();
            }
        });

    }
    /**
     * handle roll dice button
     */
    public void handleRollDice() {
        Integer[] diceList = dice.getDiceList();
        StringBuilder sb = new StringBuilder();
        for (Integer i : diceList) {
            sb.append(i.toString());
        }
        String concatenatedDiceList = sb.toString();

        setrollsLabelField(concatenatedDiceList);

        GameLogic.checkMachuPichu(diceList, this.player);
        setGameStatusLabelField(player.getStatusStr());

        setScoreLabelField(String.valueOf(player.getScore()));

        if (player.getScore() > player.getHighScore()){
            player.setHighScore();
            setHighScoreLabelField(String.valueOf(player.getHighScore()));
        }
    }
    /**
     * handle new game button
     */
    public void handleNewGame() {
        player.resetScore();
        setScoreLabelField(String.valueOf(player.getScore()));
        setGameStatusLabelField("Waiting for game to start");
        setrollsLabelField("Roll dice to start the game");
    }
    /**
     * handle save game button
     */
    public void handleSaveGame() {
        ArrayList<Player> players = Players.readPlayerDataFromFile();

        Players.writePlayerDataToFile(this.player, players);
    }
    /**
     * set rolls label field
     * @param text
     */
    private void setrollsLabelField(String text) {
        rollsLabelField.setText(text);
    }
    /**
     * set game status label field
     * @param text
     */
    private void setGameStatusLabelField(String text) {
        statusLabelField.setText(text);
    }
    /**
     * set score label field
     * @param text
     */
    private void setScoreLabelField(String text) {
        scoreLabelField.setText(text);
    }
    /**
     * set high score label field
     * @param text
     */
    private void setHighScoreLabelField(String text) {
        highScoreLabelField.setText(text);
    }
    /**
     * set user name label field
     * @param text
     */
    private void setUserNameLabelField(String text) {
        userNameLabelField.setText(text);
    }
    /**
     * set visible
     * @param visible
     */
    public void setVisible(boolean visible) {
        JFrame frame = new JFrame("Game Form");
        frame.setContentPane(this.panel1);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(visible);
    }
}
