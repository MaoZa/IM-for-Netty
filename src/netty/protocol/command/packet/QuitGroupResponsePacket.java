package netty.protocol.command.packet;

import lombok.Data;
import netty.protocol.command.Command;
import netty.protocol.command.Packet;

/**
 * @author Cap_Sub
 */
@Data
public class QuitGroupResponsePacket extends Packet {

    private String groupId;

    private Boolean success;



    @Override
    public Byte getCommand() {
        return Command.QUITGROUP_RESPONSE;
    }
}
