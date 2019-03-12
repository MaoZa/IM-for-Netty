package netty.protocol.command.packet;

import lombok.Data;
import netty.model.Session;
import netty.protocol.command.Command;
import netty.protocol.command.Packet;

/**
 * @author Cap_Sub
 */
@Data
public class GroupMessageResponsePacket extends Packet {

    private String fromGroupId;

    private String message;

    private Session fromUser;

    @Override
    public Byte getCommand() {
        return Command.GROUPMESSAGE_RESPONSE;
    }
}
