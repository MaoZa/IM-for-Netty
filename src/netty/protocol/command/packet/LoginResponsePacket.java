package netty.protocol.command.packet;

import lombok.Data;
import netty.protocol.command.Command;
import netty.protocol.command.Packet;

/**
 * @author Cap_Sub
 */
@Data
public class LoginResponsePacket extends Packet {

    private String reason;

    private Boolean success;

    private Byte version;

    private String userId;

    @Override
    public Byte getCommand() {
        return Command.LOGIN_RESPONSE;
    }
}
