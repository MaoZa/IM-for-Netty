package netty.protocol.command;

import lombok.Data;

/**
 * @author Cap_Sub
 */
@Data
public class MessageResponsePacket extends Packet {

    private String message;

    @Override
    public Byte getCommand() {
        return Command.MESSAGE_RESPONSE;
    }
}
