package netty.protocol.command.packet;

import lombok.Data;
import netty.protocol.command.Command;
import netty.protocol.command.Packet;

import java.util.List;

/**
 * @author Cap_Sub
 */
@Data
public class CreateGroupResponsePacket extends Packet {

    private Boolean success;
    private String groupId;
    private List<String> userNameList;

    @Override
    public Byte getCommand() {
        return Command.CREATEGROUP_RESPONSE;
    }
}
