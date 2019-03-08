package netty.client.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import netty.protocol.command.LoginResponsePacket;
import netty.protocol.command.MessageResponsePacket;
import netty.protocol.command.Packet;
import netty.protocol.command.PacketCodeC;
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
                System.out.println(new Date() + ": 客户端登录成功");
            } else {
                System.out.println(new Date() + ": 客户端登录失败，原因：" + loginResponsePacket.getReason());
            }
            channelHandlerContext.channel();

    }

}
