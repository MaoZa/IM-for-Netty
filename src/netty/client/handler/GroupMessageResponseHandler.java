package netty.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import netty.protocol.command.packet.GroupMessageResponsePacket;

/**
 * @author Cap_Sub
 */
public class GroupMessageResponseHandler extends SimpleChannelInboundHandler<GroupMessageResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, GroupMessageResponsePacket groupMessageResponsePacket) throws Exception {
        String fromGroupId = groupMessageResponsePacket.getFromGroupId();
        String fromUserName = groupMessageResponsePacket.getFromUser().getUserName();
        String message = groupMessageResponsePacket.getMessage();
        System.out.println("来自群聊[" + fromGroupId + "]:" + fromUserName + " -> " + message);
    }
}
