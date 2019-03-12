package netty.protocol.command;

import io.netty.channel.Channel;

import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * @author Cap_Sub
 */
public class MenuConsoleCommand implements ConsoleCommand {

    private Map<String, ConsoleCommand> consoleCommandMap;

    public MenuConsoleCommand(Map<String, ConsoleCommand> consoleCommandMap) {
        this.consoleCommandMap = consoleCommandMap;
    }

    @Override
    public void exec(Scanner scanner, Channel channel) {
        Set<Map.Entry<String, ConsoleCommand>> entries = consoleCommandMap.entrySet();
        int i = 1;
        for (Map.Entry<String, ConsoleCommand> entry : entries) {
            System.out.println(i + "." + entry.getKey());
            i++;
        }

    }
}
