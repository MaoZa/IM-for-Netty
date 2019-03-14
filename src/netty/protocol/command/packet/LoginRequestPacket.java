package netty.protocol.command.packet;

import lombok.Data;
import netty.protocol.command.Command;
import netty.protocol.command.Packet;

@Data
public class LoginRequestPacket extends Packet {

    private String userId;

    private String email;

    private String password;

    @Override
    public Byte getCommand() {
        return Command.LOGIN_REQUEST;
    }
}