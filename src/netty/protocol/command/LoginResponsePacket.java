package netty.protocol.command;

import lombok.Data;

/**
 * @author Cap_Sub
 */
@Data
public class LoginResponsePacket extends Packet {

    private String reason;

    private Boolean success;

    private Byte version;

    @Override
    public Byte getCommand() {
        return Command.LOGIN_RESPONSE;
    }
}
