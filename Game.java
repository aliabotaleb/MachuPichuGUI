import java.util.*;

public class Game {
    private final int MAX_TURN = 5;

    private static final int BASE = 6;
    private static final int MIDDLE = 3;
    private static final int TOP = 1;

    /**
     * Complete the method that calculates the accurate score for each set of 5 dice
     * Ensure you follow the rules specified in the brief
     * @param currentRoll
     * @param player
     */
    public static void checkMachuPichu(Integer[] currentRoll,  Player player) {
        // Set hasBase hasMiddle and hasTop from player class
        int mpStatus = player.getMpStatus();
        boolean hasBase = mpStatus>0;
        boolean hasMiddle = mpStatus>1;
        boolean hasTop = mpStatus>2;

        // If player doesnt have Base, and it exists in currenRoll,
        // set hasBase to true and remove first instance of base from currentRoll
        if (!hasBase){
            hasBase = Arrays.asList(currentRoll).contains(BASE);
            if(hasBase){
                mpStatus = 1;
                player.setMpStatus(mpStatus);
                int index = Arrays.asList(currentRoll).indexOf(BASE);
                currentRoll[index] = currentRoll[currentRoll.length - 1];
                currentRoll = Arrays.copyOf(currentRoll, currentRoll.length - 1);
            }
        }
        // If player has Base and doesnt have Middle, and Middle exists in currenRoll,
        // set hasMiddle  to true and remove first instance of Middle from currentRoll
        if (hasBase && !hasMiddle){
            hasMiddle = Arrays.asList(currentRoll).contains(MIDDLE);
            if(hasMiddle){
                mpStatus = 2;
                player.setMpStatus(mpStatus);
                int index = Arrays.asList(currentRoll).indexOf(MIDDLE);
                currentRoll[index] = currentRoll[currentRoll.length - 1];
                currentRoll = Arrays.copyOf(currentRoll, currentRoll.length - 1);
            }
        }
        // If player has Middle and doesnt have Top, and Top exists in currenRoll,
        // set hasTop  to true and remove first instance of Top from currentRoll
        if (hasMiddle && !hasTop){
            hasTop = Arrays.asList(currentRoll).contains(TOP);
            if(hasTop){
                mpStatus = 3;
                player.setMpStatus(mpStatus);
                int index = Arrays.asList(currentRoll).indexOf(TOP);
                currentRoll[index] = currentRoll[currentRoll.length - 1];
                currentRoll = Arrays.copyOf(currentRoll, currentRoll.length - 1);
            }
        }

        // If has base middle and top, claculate sum and add it to player score
        if (hasTop){
            int sum = 0;
            for (int i = 0; i < currentRoll.length; i++)
                sum += currentRoll[i];
            player.incScore(sum);
        }
    }


    /**
     * Complete the method to play a single turn of the game
     * Print out the current player details
     * Pause for a click at each turn
     * @param player
     */
    private void playTurn(Player player) {
        // Roll dice and calculate player score
        Dice dice = new Dice();
        Integer[] currentRoll = dice.getDiceList();
        checkMachuPichu(currentRoll,player);

        System.out.println(currentRoll);
        System.out.println(player);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Press Enter to continue...");
        scanner.nextLine();
    }

    /**
     * Complete the method to read the Player file
     * Set up the player using the getPlayer method
     * Set up a computer player
     * Use an appropriate loop to loop until they no loner want to play the game
     * play an entire game between a human player and a computer player
     * Write the players data and the player data to the data file
     */
    public void playGame() {
        Scanner scanner = new Scanner(System.in);
        Players players = new Players();
        ArrayList<Player> playersData = players.readPlayerDataFromFile();

        // While player wants to play
        while(true){
            // Get players
            Player selectedPlayer = players.getPlayer(playersData);
            Player computerPLayer = new Player("Computer,0");

            // Play 5 turns each then set HighScore for selectedPlayer and to CSV
            for (int i = 0; i < 5; i++) {
                playTurn(selectedPlayer);
                playTurn(computerPLayer);
            }
            selectedPlayer.setHighScore();
            players.writePlayerDataToFile(selectedPlayer,playersData);

            // Display who won
            if (selectedPlayer.getScore() > computerPLayer.getScore()){
                System.out.println("You Won!");
            } else if ( selectedPlayer.getScore() < computerPLayer.getScore()){
                System.out.println("You lost!");
            } else {
                System.out.println("Draw");
            }

            // Check if player wants to play another game
            System.out.println("Do you want to play another game?(y/n)");
            String answer = scanner.nextLine();
            if (!answer.equals("y")){
                break;
            }
        }
    }

    public Integer[]  getDiceListGui(){
        Dice dice = new Dice();
        return dice.getDiceList();
    }

    public Player playTurnGui(Player player, Integer[] diceList) {
        checkMachuPichu(diceList,player);
        return(player);
    }
}
