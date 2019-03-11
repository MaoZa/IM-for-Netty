package netty.protocol.command;

import io.netty.buffer.ByteBuf;

/**
 * @author Cap_Sub
 */
public interface Command {

    Byte LOGIN_REQUEST = 1;
    Byte LOGIN_RESPONSE = 2;
    Byte MESSAGE_REQUEST = 3;
    Byte MESSAGE_RESPONSE = 4;
    Byte CREATEGROUP_REQUEST = 5;
    Byte CREATEGROUP_RESPONSE = 6;
    Byte JOINGROUP_REQUEST = 7;
    Byte JOINGROUP_RESPONSE = 7;

    Packet getCommand(Byte command);

}
