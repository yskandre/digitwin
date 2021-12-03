package smarthome;

public class TwinSpeaker implements Twinnable {
    public static final long serialVersionUID = 1L;
    private final String speakerID;
    private String currentSong = "";
    private String currentAlarm = "";

    public TwinSpeaker(String speakerID) {
        this.speakerID = speakerID;
    }

    public void playSong(String query) {
        String result = ""; // Interface to music service here
        setCurrentSong(result);
    }

    public String getCurrentSong() {
        return currentSong;
    }

    public void setCurrentSong(String currentSong) {
        this.currentSong = currentSong;
    }

    public String getCurrentAlarm() {
        return currentAlarm;
    }

    public void setCurrentAlarm(String currentAlarm) {
        this.currentAlarm = currentAlarm;
    }

    @Override
    public String getID() {
        return speakerID;
    }

    @Override
    public String getStatus() {
        return "> " + speakerID + " (speaker)\n\t Currently playing: " + currentSong + "\n\t Alarm: " + currentAlarm == "" ? "Not set" : currentAlarm;
    }

    @Override
    public void sendCommand(String... cmds) {
        switch (cmds[0].toLowerCase()) {
            case "play":
                playSong(cmds[1]);
                break;
            case "alarm":
                switch (cmds[1].toLowerCase()) {
                    case "set":
                        setCurrentAlarm(cmds[2]);
                        break;
                    case "clear":
                        setCurrentAlarm("");
                        break;
                    default:
                        throw new UnsupportedOperationException("Command " + cmds[1] + " was not found.");
                }
                break;
            default:
                throw new UnsupportedOperationException("Command " + cmds[0] + " was not found.");
        }
    }

    @Override
    public String toString() {
        return "TwinSpeaker{" +
                "speakerID='" + speakerID + '\'' +
                ", currentSong='" + currentSong + '\'' +
                ", currentAlarm='" + currentAlarm + '\'' +
                '}';
    }
}