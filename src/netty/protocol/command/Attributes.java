package netty.protocol.command;

import io.netty.util.AttributeKey;

/**
 * @author Cap_Sub
 */
public interface Attributes {

    AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("login");

}
