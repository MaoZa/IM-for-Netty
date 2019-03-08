package netty.server.pipeline;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import netty.protocol.command.MessageRequestPacket;
import netty.protocol.command.MessageResponsePacket;
import netty.protocol.command.PacketCodeC;

import java.util.Date;

/**
 * @author Cap_Sub
 */
public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MessageRequestPacket messageRequestPacket) throws Exception {
        System.out.println(new Date() + ": 收到客户端消息: " + messageRequestPacket.getMessage());

        MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
        messageResponsePacket.setMessage("服务端回复【" + messageRequestPacket.getMessage() + "】");
        ByteBuf responseByteBuf = PacketCodeC.INSTANCE.encode(channelHandlerContext.alloc(), messageResponsePacket);
        channelHandlerContext.channel().writeAndFlush(responseByteBuf);
    }

}
