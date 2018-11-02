package com.jean.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.CharsetUtil;

import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

/**
 * TODO 少年，写点啥吧！
 *
 * @author jinshubao
 * @date 2018/11/01
 */
public class NettyClient {

    public static void main(String[] args) {

        //worker负责读写数据
        EventLoopGroup worker = new NioEventLoopGroup();


        //辅助启动类
        Bootstrap bootstrap = new Bootstrap()
                //设置线程池
                .group(worker)
                //设置socket工厂
                .channel(NioSocketChannel.class)
                //设置管道
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        //获取管道
                        ChannelPipeline pipeline = socketChannel.pipeline();

                        pipeline.addLast(new IdleStateHandler(0, 5, 0, TimeUnit.SECONDS));

                        //字符串解码器
                        pipeline.addLast(new StringDecoder());
                        //字符串编码器
                        pipeline.addLast(new StringEncoder());
                        //处理类
                        pipeline.addLast(new ClientHandler());
                        //心跳处理类
                        pipeline.addLast(new HeartBeatClientHandler());
                    }
                });
        try {
            //发起异步连接操作 等待客户端链路关闭
            ChannelFuture future = bootstrap.connect(new InetSocketAddress("127.0.0.1", 8866)).sync();
            System.out.println("client start...");
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //优雅的退出，释放NIO线程组
            worker.shutdownGracefully();
        }
    }

}

class ClientHandler extends SimpleChannelInboundHandler<String> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println("channelRead0 " + msg);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //给服务器发消息
        ctx.channel().writeAndFlush("i am client !");
        System.out.println("channelActive ");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelInactive ");
        ctx.channel().close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("exceptionCaught " + cause.getMessage());
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        System.out.println("userEventTriggered " + evt);
        super.userEventTriggered(ctx, evt);
    }
}


class HeartBeatClientHandler extends ChannelInboundHandlerAdapter {

    private static final ByteBuf HEARTBEAT_SEQUENCE = Unpooled.unreleasableBuffer(Unpooled.copiedBuffer("Heartbeat",
            CharsetUtil.UTF_8));

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            if (event.state() == IdleState.WRITER_IDLE) {
                ctx.channel().writeAndFlush(HEARTBEAT_SEQUENCE.duplicate());
            }
        }
        super.userEventTriggered(ctx, evt);
    }
}
