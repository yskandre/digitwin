import smarthome.SmartHome;
import smarthome.TwinBulb;
import smarthome.TwinHeater;
import smarthome.TwinSpeaker;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        SmartHome smartHome = SmartHome.load();

        switch (args[0].toLowerCase()) {
            case "add":
                switch (args[1].toLowerCase()) {
                    case "room":
                        smartHome.add(args[2]);
                        break;
                    case "bulb":
                        smartHome.add(args[2], new TwinBulb(args[3]));
                        break;
                    case "heater":
                        smartHome.add(args[2], new TwinHeater(args[3]));
                        break;
                    case "speaker":
                        smartHome.add(args[2], new TwinSpeaker(args[3]));
                        break;
                    default:
                        throw new UnsupportedOperationException("The type " + args[1] + " is not yet supported.");
                }
                break;
            case "remove":
                switch (args[1].toLowerCase()) {
                    case "room":
                        smartHome.remove(args[2]);
                        break;
                    case "bulb":
                    case "heater":
                    case "speaker":
                        smartHome.remove(args[2], args[3]);
                        break;
                    default:
                        throw new UnsupportedOperationException("The type " + args[1] + " is not yet supported.");
                }
                break;
            case "send":
                smartHome.sendCommand(args[1], args[2], Arrays.copyOfRange(args, 3, args.length));
                break;
            case "status":
                System.out.println(smartHome.getStatus());
                break;
            case "help":
                switch (args[1]) {
                    case "add":
                        System.out.println("Add a room to the smart home.\n Usage: add [room] <roomID>\nAdd a device to a room.\n Usage: add [bulb|heater|speaker] <roomID> <deviceID>");
                        break;
                    case "remove":
                        System.out.println("Remove a room from the smart home.\n Usage: remove [room] <roomID>\n Remove a device from a room.\n Usage: remove [bulb|heater|speaker] <roomID> <deviceID>");
                        break;
                    case "send":
                        System.out.println("Send a command to a device. Find available commands under the help entry for each device.\n Usage: send <roomID> <deviceID> <command> <optional parameter>");
                        break;
                    case "status":
                        System.out.println("Print the status of the smart home with it's registered rooms and devices.\n Usage: status");
                        break;
                    case "help":
                        System.out.println("Get help on how to use the available commands and what parameters are available for them.\n Usage: help [add|remove|send|status|help|bulb|heater|speaker]");
                        break;
                    case "bulb":
                        System.out.println("Available commands for lightbulbs:\ntoggle: Toggle the light on or off.\n Usage: send <roomID> <bulbID> toggle\ncolor: Set the color of the light by providing a hexcode.\n Usage: send <roomID> <bulbID> color 000000");
                        break;
                    case "heater":
                        System.out.println("Available commands for heaters:\ntoggle: Toggle the heater on or off.\n Usage: send <roomID> <heaterID> toggle\ntemp: Set the temperature of the heater.\n Usage: send <roomID> <heaterID> temp 24");
                        break;
                    case "speaker":
                        System.out.println("Available commands for speakers:\nplay: Play a song on the speaker.\n Usage: send <roomID> <speakerID> play <song>\nalarm: Set or clear an alarm.\n Usage: send <roomID> <speakerID> alarm set 14:00\n        send <roomID> <speakerID> alarm clear");
                        break;
                }
                break;
                default:
                    throw new UnsupportedOperationException("The command " + args[0] + " is not yet supported.");
        }
        smartHome.save();
    }
}