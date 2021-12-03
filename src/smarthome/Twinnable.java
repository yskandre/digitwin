package smarthome;

import java.io.Serializable;

public interface Twinnable extends Serializable {
    String getID();
    String getStatus();
    void sendCommand(String... cmds);
}
