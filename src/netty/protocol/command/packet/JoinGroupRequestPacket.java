package netty.protocol.command.packet;

import lombok.Data;
import netty.protocol.command.Command;
import netty.protocol.command.Packet;

/**
 * @author Cap_Sub
 */
@Data
public class JoinGroupRequestPacket extends Packet {

    private String groupId;

    @Override
    public Byte getCommand() {
        return Command.JOINGROUP_REQUEST;
    }
}
