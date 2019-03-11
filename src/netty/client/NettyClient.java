package netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import netty.client.handler.CreateGroupResponseHandler;
import netty.client.handler.LoginResponseHandler;
import netty.client.handler.MessageResponseHandler;
import netty.model.SessionUtil;
import netty.protocol.command.ConsoleCommandManager;
import netty.protocol.command.LoginConsoleCommand;
import netty.protocol.command.packet.LoginRequestPacket;
import netty.protocol.command.packet.MessageRequestPacket;
import netty.protocol.command.PacketCodeC;
import netty.coder.PacketDecoder;
import netty.coder.PacketEncoder;
import netty.server.handler.CreateGroupRequestHandler;
import netty.utils.LoginUtil;

import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * @author Cap_Sub
 */
public class NettyClient {

    /**
     * 最大重连次数
     */
    private static int MAX_RETRY = 5;

    public static void main(String[] args) throws InterruptedException {
        Bootstrap bootstrap = new Bootstrap();
        NioEventLoopGroup group = new NioEventLoopGroup();

        bootstrap
                /**
                 * 指定线程模型
                 */
                .group(group)
                /**
                 * 指定IO类型为NIO
                 */
                .channel(NioSocketChannel.class)
                /**
                 * IO逻辑处理
                 */
                .handler(new ChannelInitializer<Channel>() {
                    @Override
                    protected void initChannel(Channel ch) {
                        //通用粘包拆包处理器
                        ch.pipeline().addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 7, 4));
                        //消息解码处理器
                        ch.pipeline().addLast(new PacketDecoder());
                        //初始化处理器
                        ch.pipeline().addLast(new ClientHandler());
                        //登录响应处理器
                        ch.pipeline().addLast(new LoginResponseHandler());
                        //创建群聊响应处理器
                        ch.pipeline().addLast(new CreateGroupResponseHandler());
                        //消息响应处理器
                        ch.pipeline().addLast(new MessageResponseHandler());
                        //消息编码处理器
                        ch.pipeline().addLast(new PacketEncoder());
                    }
                })
                /**
                 * 设置超时时间为5秒
                 */
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                /**
                 * 设置TCP心跳为开启
                 */
                .option(ChannelOption.SO_KEEPALIVE, true)
                /**
                 * 设置Nagle算法为关闭 treu关闭 false开启
                 */
                .option(ChannelOption.TCP_NODELAY, true);

                connect(bootstrap, "127.0.0.1", 8000, MAX_RETRY);

    }

    private static void connect(Bootstrap bootstrap, String host, int port, int retry){
        bootstrap.connect(host, port).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println("连接成功!");
                Channel channel = ((ChannelFuture) future).channel();
                startConsoleThread(channel);
            } else if (retry == 0) {
                System.err.println("重试次数已用完，放弃连接！");
            }else{
                // 第几次重连
                int order = (MAX_RETRY - retry) + 1;
                // 本次重连的间隔
                int delay = 1 << order;
                System.err.println(new Date() + ": 连接失败，第" + order + "次重连……");
                /**
                 * 设置定时任务
                 */
                bootstrap.config().group().schedule(() -> connect(bootstrap, host, port, retry - 1), delay, TimeUnit
                        .SECONDS);
            }
        });
    }

    private static void startConsoleThread(Channel channel) {
        Scanner sc = new Scanner(System.in);
        ConsoleCommandManager consoleCommandManager = new ConsoleCommandManager();
        LoginConsoleCommand loginConsoleCommand = new LoginConsoleCommand();

        new Thread(() -> {
            while (!Thread.interrupted()) {
                if (!LoginUtil.hasLogin(channel)) {
                    loginConsoleCommand.exec(sc, channel);
                } else {
                    consoleCommandManager.exec(sc, channel);
                }
            }
        }).start();
    }

}
