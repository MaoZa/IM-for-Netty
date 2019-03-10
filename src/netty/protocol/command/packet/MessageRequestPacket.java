package netty.protocol.command.packet;

import lombok.Data;
import netty.protocol.command.Command;
import netty.protocol.command.Packet;

/**
 * @author Cap_Sub
 */
@Data
public class MessageRequestPacket extends Packet {

    private String message;

    private String toUserId;

    @Override
    public Byte getCommand() {
        return Command.MESSAGE_REQUEST;
    }

    public MessageRequestPacket(String toUserId, String message) {
        this.toUserId = toUserId;
        this.message = message;
    }

    public MessageRequestPacket() {
    }
}
