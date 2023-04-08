import java.util.Objects;

public class Player {
    public static final int NO_ELEMENTS = 0;
    public static final int HAS_BASE = 1;
    public static final int HAS_MIDDLE = 2;
    public static final int HAS_TOP = 3;
    private String name;
    private String password;
    private int score;
    private int mpStatus;
    private int highScore;

    /**
     * Useful method to check for a valid integer
     * @param numStr
     * @return null is not valid otherwise an int
     */

    public Player(String data) {
        //data = "hana,password,46"
        String[] playerData = data.split(",");


        this.name = playerData[0];
        this.password = playerData[1];
        this.highScore = Integer.parseInt(playerData[2]);
        this.score = 0;
        this.mpStatus = 0;
    }

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

    public boolean validatePassword(String password) {
        return Objects.equals(password, this.password);
    }


    public void incScore(int score) {
        this.score += score;
    }

    public int getMpStatus() {
        return mpStatus;
    }

    public void setMpStatus(int mpStatus) {
        this.mpStatus = mpStatus;
    }

    public int getHighScore() {
        return highScore;
    }

    public void setHighScore() {
        if (score > highScore) {
            highScore = score;
        }
    }

    private String getStatusStr() {
        String status = "";
        switch (mpStatus) {
            case NO_ELEMENTS -> status = "No elements";
            case HAS_BASE -> status = "Has base";
            case HAS_MIDDLE -> status = "Has base. Has middle";
            case HAS_TOP -> status = "Has all elements";
        }
        return status;
    }

    public String toCSVString() {
        return name + "," + password + "," + highScore;
    }
    @Override
    public String toString() {
        return "Player: " + name + ". Score: " + score + ". " + getStatusStr() +
                ". High score: " + highScore;
    }
}
