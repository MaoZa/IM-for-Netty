package netty.client.handler;

import lombok.Data;
import netty.protocol.command.Command;
import netty.protocol.command.Packet;

@Data
public class MessageRequestPacket extends Packet {

    private String toUserId;

    private String message;

    @Override
    public Byte getCommand() {
        return Command.MESSAGE_REQUEST;
    }
}
