package netty.server.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author Cap_Sub
 */
public class ConnectExceptionCloseHandler extends ChannelHandlerAdapter {



    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        Channel incoming = ctx.channel();
        if(!incoming.isActive()) {
            System.out.println("客户端:" + incoming.remoteAddress() + "异常断开");
        }

        cause.printStackTrace();
        ctx.close();
    }

}
