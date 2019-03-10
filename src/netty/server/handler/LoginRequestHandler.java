package netty.server.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.AttributeKey;
import netty.model.Session;
import netty.model.SessionUtil;
import netty.protocol.command.packet.LoginRequestPacket;
import netty.protocol.command.packet.LoginResponsePacket;
import netty.protocol.command.PacketCodeC;
import netty.utils.LoginUtil;

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
            String userId = LoginUtil.randomUserId();
            loginResponsePacket.setUserId(userId);
            SessionUtil.bindSession(new Session(userId, loginRequestPacket.getUsername()), channelHandlerContext.channel());
            LoginUtil.markAsLogin(channelHandlerContext.channel());
            System.out.print(loginRequestPacket.getUsername() + " 登录成功");
            System.out.println("  userId: " + loginResponsePacket.getUserId());
        } else {
            loginResponsePacket.setReason("账号密码校验失败");
            loginResponsePacket.setSuccess(false);
            System.out.println("登录失败");
        }
        // 编码
        ByteBuf responseByteBuf = PacketCodeC.INSTANCE.encode(channelHandlerContext.alloc(), loginResponsePacket);
        channelHandlerContext.channel().writeAndFlush(responseByteBuf);
    }

    // 用户断线之后取消绑定
    public void channelInactive(ChannelHandlerContext ctx) {
        System.out.println("用户下线， 取消Map绑定");
        SessionUtil.unBindSession(ctx.channel());
    }

    private boolean valid(LoginRequestPacket loginRequestPacket) {
        return true;
    }

}
