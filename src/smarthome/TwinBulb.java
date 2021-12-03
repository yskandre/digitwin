package smarthome;

public class TwinBulb implements Twinnable {
    public static final long serialVersionUID = 1L;
    private final String bulbID;
    private boolean lightOn = false;
    private int color = 0x000000;

    public TwinBulb(String bulbID) {
        this.bulbID = bulbID;
    }

    public boolean isLightOn() {
        return lightOn;
    }

    public void toggleLight() {
        this.lightOn = !lightOn;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    @Override
    public String getID() {
        return bulbID;
    }

    @Override
    public String getStatus() {
        return "> " + bulbID + " (lightbulb)\n\t Turned on: " + lightOn + "\n\t Color: #" + String.format("%06X", color);
    }

    @Override
    public void sendCommand(String... cmds) {
        switch (cmds[0].toLowerCase()) {
            case "toggle":
                toggleLight();
                break;
            case "color":
                setColor(Integer.parseInt(cmds[1], 16));
                break;
            default:
                throw new UnsupportedOperationException("Command " + cmds[0] + " was not found.");
        }
    }

    @Override
    public String toString() {
        return "TwinBulb{" +
                "bulbID='" + bulbID + '\'' +
                ", lightOn=" + lightOn +
                ", color=#" + String.format("%06X", color) +
                '}';
    }
}