package netty.protocol.command.packet;

import lombok.Data;
import netty.protocol.command.Command;
import netty.protocol.command.Packet;

/**
 * @author Cap_Sub
 */
@Data
public class JoinGroupResponsePacket extends Packet {

    private Boolean success;

    private String groupId;

    @Override
    public Byte getCommand() {
        return Command.JOINGROUP_RESPONSE;
    }
}
