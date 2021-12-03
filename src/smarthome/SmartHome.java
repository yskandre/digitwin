package smarthome;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class SmartHome implements Serializable {
    public static final long serialVersionUID = 1L;
    public static String path = "data/smarthome.ser";
    private Map<String, TwinRoom> rooms = new HashMap<>();

    public void add(String roomID) {
        rooms.put(roomID, new TwinRoom(roomID));
    }

    public void remove(String roomID) {
        rooms.remove(roomID);
    }

    public void add(String roomID, Twinnable twin) {
        rooms.get(roomID).addTwin(twin);
    }

    public void remove(String roomID, String twinID) {
        rooms.get(roomID).removeTwin(twinID);
    }

    public String getStatus() {
        StringBuilder output = new StringBuilder("Status of smarthome:\n");
        rooms.forEach((k,v) -> output.append(v.getStatus()));
        return output.toString();
    }

    public void sendCommand(String roomID, String twinID, String... cmds) {
        if(!rooms.containsKey(roomID)) {
            System.out.println("No room with the name " + roomID + " was found.");
            return;
        }
        rooms.get(roomID).sendCommand(twinID, cmds);
    }

    public void save() {
        try {
            FileOutputStream fileOut = new FileOutputStream(path);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public static SmartHome load() {
        try {
            File directory = new File(path);
            if(!directory.exists()) {
                directory.getParentFile().mkdirs();
            }
            FileInputStream fileIn = new FileInputStream(directory);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            SmartHome home = (SmartHome) in.readObject();
            in.close();
            fileIn.close();
            return home;
        } catch (IOException i) {
            System.out.println("No file could be loaded, a new smart home will be created.");
        } catch (ClassNotFoundException c) {
            System.out.println("SmartHome class not found.");
        }
        return new SmartHome();
    }

    @Override
    public String toString() {
        return "SmartHome{" +
                "rooms=" + rooms +
                '}';
    }
}
