import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Players {
    private static final String DATA_FILE = "data/data.csv";

    /**
     * Reads the player data from a file and returns it as an ArrayList of Player objects.
     *
     * @return ArrayList of Player objects
     */
    public static ArrayList<Player> readPlayerDataFromFile() {
        ArrayList<Player> players = new ArrayList<>();

        try {
            try (BufferedReader reader = new BufferedReader(new FileReader(DATA_FILE))) {
                String line = reader.readLine();

                // loop over each line in DATA_FILE and append to players as new player
                while (line != null) {
                    players.add(new Player(line));
                    // read next line
                    line = reader.readLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {

        }


        return players;
    }

    /**
     * Updates the specific player data in the list if it already exists, or adds the player to the list if it doesn't already exist.
     * Then writes the whole list back to the file.
     *
     * @param player  The player object to update or add to the list
     * @param players The list of Player objects to update or add the player to
     */
    public static void writePlayerDataToFile(Player player, ArrayList<Player> players) {
        boolean playerExists = false;
        // Loop over each player in players
        for (int i = 0; i < players.size(); i++) {
            Player p = players.get(i);
            // If the given player exists in the array
            if (p.getName().equals(player.getName())) {
                // Update existing player data
                players.set(i, player);
                playerExists = true;
                break;
            }
        }

        if (!playerExists) {
            // Add new player to list
            players.add(player);
        }

        // Write list back to file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DATA_FILE))) {
            for (Player p : players) {
                writer.write(p.toCSVString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Checks if a player with the given name already exists in the list of players.
     *
     * @param players The list of Player objects to check
     * @param name    The name of the player to check for
     * @return The Player object with the given name if it exists in the list, or null if not found
     */
    public static boolean checkNameExists(ArrayList<Player> players, String name) {
        for (Player p : players) {
            // If given player exists in Array
            if (p.getName().equals(name)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Asks the user for a player name, checks if the player already exists in the list, and returns the relevant Player object.
     * If the name is found, asks the user if they want to load the existing data.
     * If they do want to load the data, returns the relevant player object.
     * Otherwise returns a new Player object.
     *
     * @param players The list of Player objects to check
     * @return The Player object that was either found or created
     */
    public static Player getPlayer(ArrayList<Player> players) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter player name: ");
        String name = scanner.nextLine();

        Player existingPlayer = checkNameExists(players, name);
        // If player exits and wants to load data return saved player
        // Else create new player
        if (existingPlayer != null) {
            System.out.print("Player already exists. Load existing data? (y/n) ");
            String response = scanner.nextLine();

            if (response.equalsIgnoreCase("y")) {
                return existingPlayer;
            }

        }
        //create new player
        Player newPlayer = new Player(name + ",0");
        return newPlayer;
    }
}
