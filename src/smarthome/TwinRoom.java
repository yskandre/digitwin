package smarthome;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class TwinRoom implements Serializable {
    public static final long serialVersionUID = 1L;
    private final String roomID;
    private final Map<String, Twinnable> twins;

    public TwinRoom(String roomID) {
        this.roomID = roomID;
        this.twins = new HashMap<>();
    }

    public void addTwin(Twinnable twin) {
        twins.put(twin.getID(), twin);
    }

    public void removeTwin(String twinID) {
        twins.remove(twinID);
    }

    public boolean containsTwin(String twinID) {
        return twins.containsKey(twinID);
    }

    public String getID() {
        return roomID;
    }

    public String getStatus() {
        StringBuilder output = new StringBuilder("Room: " + roomID + "\n");
        twins.forEach((k,v) -> output.append("\t" + v.getStatus() + "\n"));
        return output.toString();
    }

    public void sendCommand(String twinID, String... cmds) {
        if(!containsTwin(twinID)) {
            System.out.println("No twin with the name " + twinID + " was found in the room with the name " + roomID + ".");
            return;
        }
        twins.get(twinID).sendCommand(cmds);
    }

    @Override
    public String toString() {
        return "TwinRoom{" +
                "roomID='" + roomID + '\'' +
                ", twins=" + twins +
                '}';
    }
}
