package netty.protocol.command.packet;

import lombok.Data;
import netty.protocol.command.Command;
import netty.protocol.command.Packet;

/**
 * @author Cap_Sub
 */
@Data
public class HeartBeatRequestPacket extends Packet {

    private Byte aByte = 1;

    @Override
    public Byte getCommand() {
        return Command.HEARTBEAT_REQUEST;
    }
}
