package netty.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import netty.model.SessionUtil;
import netty.protocol.command.packet.LoginResponsePacket;
import netty.utils.LoginUtil;

import java.util.Date;

/**
 * @author Cap_Sub
 */
public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, LoginResponsePacket loginResponsePacket) throws Exception {

            if (loginResponsePacket.getSuccess()) {
                LoginUtil.markAsLogin(channelHandlerContext.channel());
                System.out.println(new Date() + ": 客户端登录成功, 你的userId为:" + loginResponsePacket.getUserId());
            } else {
                System.out.println(new Date() + ": 客户端登录失败，原因：" + loginResponsePacket.getReason());
            }
            channelHandlerContext.channel();

    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        System.out.println("客户端连接被关闭!");
    }

}
