package netty.protocol.command;

import io.netty.util.AttributeKey;
import netty.model.Session;

/**
 * @author Cap_Sub
 */
public interface Attributes {

    AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("login");

    AttributeKey<Session> SESSION = AttributeKey.newInstance("session");

}
