package netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import netty.coder.PacketDecoder;
import netty.coder.PacketEncoder;
import netty.server.handler.*;

/**
 * @author Cap_Sub
 */
public class NettyServer {

    public static void main(String[] args) {
        ServerBootstrap serverBootstrap = new ServerBootstrap();

        /**
         * 用于监听新链接的线程组
         */
        NioEventLoopGroup boos = new NioEventLoopGroup();

        /**
         * 用于处理每一条链接读写的线程组
         */
        NioEventLoopGroup worker = new NioEventLoopGroup();
        serverBootstrap
                /**
                 * 配置线程模型
                 */
                .group(boos, worker)
                /**
                 * 配置io模型为NIO
                 */
                .channel(NioServerSocketChannel.class)
                /**
                 * 这里主要就是定义后续每条连接的数据读写，业务处理逻辑
                 */
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) {
//                        ch.handler().addLast(new ServerHandler());
//                        // inBound，处理读数据的逻辑链
//                        ch.handler().addLast(new InBoundHandlerA());
//                        ch.handler().addLast(new InBoundHandlerB());
//                        ch.handler().addLast(new InBoundHandlerC());
//
//                        // inBound，处理写数据的逻辑链
//                        ch.handler().addLast(new OutBoundHandlerA());
//                        ch.handler().addLast(new OutBoundHandlerB());
//                        ch.handler().addLast(new OutBoundHandlerC());
                        //通用粘包拆包处理器
                        ch.pipeline().addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 7, 4));
                        //Handler生命周期测试
//                        ch.pipeline().addLast(new LifeCyCleTestHandler());
                        //消息解码处理器
                        ch.pipeline().addLast(new PacketDecoder());
                        //登录请求处理器
                        ch.pipeline().addLast(new LoginRequestHandler());
                        //新增加用户认证handler
                        ch.pipeline().addLast(new AuthHandler());
                        // 添加一个 handler
                        ch.pipeline().addLast(new CreateGroupRequestHandler());
                        //消息请求处理器
                        ch.pipeline().addLast(new MessageRequestHandler());
                        //消息编码处理器
                        ch.pipeline().addLast(new PacketEncoder());
                    }
                })
                /**
                 * 设置开启TCP心跳
                 */
                .childOption(ChannelOption.SO_KEEPALIVE, true);

        /**
         * 绑定方法
         */
        bind(serverBootstrap, 8000);

        /**
         * 初始化方法,用于服务启动时逻辑
         */
        serverBootstrap.handler(new ChannelInitializer<NioServerSocketChannel>() {
            @Override
            protected void initChannel(NioServerSocketChannel ch) {
                System.out.println("服务端启动中");
            }
        });

    }

    private static void bind(final ServerBootstrap serverBootstrap, final int port){
        serverBootstrap.bind(8000).addListener(new GenericFutureListener<Future<? super Void>>() {
            @Override
            public void operationComplete(Future<? super Void> future) throws Exception {
                if (future.isSuccess()) {
                    System.out.println("绑定" + port + "端口成功!");
                } else {
                    System.out.println("绑定" + port + "端口失败!");
                    if(port + 1 <= 65535){
                        bind(serverBootstrap, port + 1);
                    }
                }
            }
        });
    }

}
