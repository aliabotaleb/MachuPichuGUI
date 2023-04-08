import java.util.Arrays;
import java.util.Collections;
/**
 * Handles the game logic for the MachuPichu game
 */
public class GameLogic {
    /**
     * Correctly calculates the score based on a die roll and the player status
     * @param currentRoll Integer array storing current dice roll
     * @param player player object stores score and status
     */
    public static void checkMachuPichu(Integer[] currentRoll, Player player) {
        //Used for clarity in code and to avoid hardcoded ints
        final int BASE = 6;
        final int MIDDLE = 3;
        final int TOP = 1;
        //If the code is sorted the calculation is significantly easier
        Arrays.sort(currentRoll, Collections.reverseOrder());
        //We only need to do this check if the player doesn't have all the elements
        if (player.getMpStatus() != Player.HAS_TOP) {
            //Check all the dice
            for (int i = 0; i < currentRoll.length; i++) {
                int dice = currentRoll[i];
                switch (dice) {
                    case BASE -> {
                        if (player.getMpStatus() == Player.NO_ELEMENTS) {
                            player.setMpStatus(Player.HAS_BASE);
                            //If this dice counts towards the status set it to 0 in the array
                            currentRoll[i] = 0;
                        }
                    }
                    case MIDDLE -> {
                        if (player.getMpStatus() == Player.HAS_BASE) {
                            player.setMpStatus(Player.HAS_MIDDLE);
                            currentRoll[i] = 0;
                        }
                    }
                    case TOP -> {
                        if (player.getMpStatus() == Player.HAS_MIDDLE) {
                            player.setMpStatus(Player.HAS_TOP);
                            currentRoll[i] = 0;
                        }
                    }
                }
            }
        }

        //Calculates the correct score
        if (player.getMpStatus() == Player.HAS_TOP) {
            for (int n : currentRoll) {
                player.incScore(n);
            }
        }
    }
}