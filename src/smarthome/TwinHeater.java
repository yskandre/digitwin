package smarthome;

public class TwinHeater implements Twinnable {
    public static final long serialVersionUID = 1L;
    private final String heaterID;
    private boolean heaterOn = false;
    private int temp = 0;

    public TwinHeater(String heaterID) {
        this.heaterID = heaterID;
    }

    public boolean isHeaterOn() {
        return heaterOn;
    }

    public void toggleHeater() {
        this.heaterOn = !heaterOn;
    }

    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }

    @Override
    public String getID() {
        return heaterID;
    }

    @Override
    public String getStatus() {
        return "> " + heaterID + " (heater)\n\t Turned on: " + heaterOn + "\n\t Temperature: " + temp + "°C";
    }

    @Override
    public void sendCommand(String... cmds) {
        switch (cmds[0].toLowerCase()) {
            case "toggle":
                toggleHeater();
                break;
            case "temp":
                setTemp(Integer.parseInt(cmds[1]));
                break;
            default:
                throw new UnsupportedOperationException("Command " + cmds[0] + " was not found.");
        }
    }

    @Override
    public String toString() {
        return "TwinHeater{" +
                "heaterID='" + heaterID + '\'' +
                ", heaterOn=" + heaterOn +
                ", temp=" + temp + "°C" +
                '}';
    }
}