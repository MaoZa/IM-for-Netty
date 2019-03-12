package netty.protocol.command;

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
    Byte JOINGROUP_RESPONSE = 8;
    Byte QUITGROUP_REQUEST = 9;
    Byte QUITGROUP_RESPONSE = 10;
    Byte LISTGROUP_REQUEST = 11;
    Byte LISTGROUP_RESPONSE = 12;
    Byte GROUPMESSAGE_REQUEST = 13;
    Byte GROUPMESSAGE_RESPONSE = 14;
    Byte HEARTBEAT_REQUEST = 15;
    Byte HEARTBEAT_RESPONSE = 16;

    Packet getCommand(Byte command);
}
