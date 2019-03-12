package netty.protocol.command.packet;

import lombok.Data;
import netty.protocol.command.Command;
import netty.protocol.command.Packet;

/**
 * @author Cap_Sub
 */
@Data
public class GroupMessageRequestPacket extends Packet {

    private String toGroupId;

    private String message;

    @Override
    public Byte getCommand() {
        return Command.GROUPMESSAGE_REQUEST;
    }
}
