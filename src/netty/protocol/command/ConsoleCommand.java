package netty.protocol.command;

import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @author Cap_Sub
 */
public interface ConsoleCommand {

    void exec(Scanner scanner, Channel channel);

}
