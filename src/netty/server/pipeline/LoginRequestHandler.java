package netty.server.pipeline;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import netty.protocol.command.LoginRequestPacket;
import netty.protocol.command.LoginResponsePacket;
import netty.protocol.command.PacketCodeC;

/**
 * @author Cap_Sub
 */
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, LoginRequestPacket loginRequestPacket) throws Exception {
        /**
         * 登录校验
         */
        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
        loginResponsePacket.setVersion(loginRequestPacket.getVersion());
        if (valid(loginRequestPacket)) {
            loginResponsePacket.setSuccess(true);
            System.out.println("登录成功");
        } else {
            loginResponsePacket.setReason("账号密码校验失败");
            loginResponsePacket.setSuccess(false);
            System.out.println("登录失败");
        }
        // 编码
        ByteBuf responseByteBuf = PacketCodeC.INSTANCE.encode(channelHandlerContext.alloc(), loginResponsePacket);
        channelHandlerContext.channel().writeAndFlush(responseByteBuf);
    }

    private boolean valid(LoginRequestPacket loginRequestPacket) {
        return true;
    }

}
