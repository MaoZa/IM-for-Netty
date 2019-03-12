package netty.protocol.command.packet;

import lombok.Data;
import netty.model.Session;
import netty.protocol.command.Command;
import netty.protocol.command.Packet;

import java.util.List;

/**
 * @author Cap_Sub
 */
@Data
public class ListGroupMembersResponsePacket extends Packet {

    private String groupId;

    private List<Session> sessionList;

    @Override
    public Byte getCommand() {
        return Command.LISTGROUP_RESPONSE;
    }
}
