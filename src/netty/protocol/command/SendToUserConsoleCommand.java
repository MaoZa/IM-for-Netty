package netty.protocol.command;

import io.netty.channel.Channel;
import netty.protocol.command.packet.MessageRequestPacket;

import java.util.Scanner;

/**
 * @author Cap_Sub
 */
public class SendToUserConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.println("开始发送消息");
        System.out.print("请输入目标userId: ");
        String toUserId = scanner.next();
        System.out.print("请输入内容: ");
        String message = scanner.next();
        channel.writeAndFlush(new MessageRequestPacket(toUserId, message));
    }
}
