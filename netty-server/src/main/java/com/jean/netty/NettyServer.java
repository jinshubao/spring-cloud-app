package com.jean.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * TODO 少年，写点啥吧！
 *
 * @author jinshubao
 * @date 2018/11/01
 */
public class NettyServer {

    public static void main(String[] args) {
        //boss线程监听端口，worker线程负责数据读写
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup worker = new NioEventLoopGroup();

        //辅助启动类
        ServerBootstrap bootstrap = new ServerBootstrap()
                //设置线程池
                .group(boss, worker)
                //设置socket工厂
                .channel(NioServerSocketChannel.class)
                .handler(new LoggingHandler(LogLevel.DEBUG))
                //链接缓冲池的大小（ServerSocketChannel的设置）
                .option(ChannelOption.SO_BACKLOG, 1024)
                //设置管道工厂
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        //获取管道
                        ChannelPipeline pipeline = socketChannel.pipeline();

//                        pipeline.addLast(new IdleStateHandler(3, 0, 0, TimeUnit.SECONDS));

                        //处理类
                        pipeline.addFirst(new OutboundHandler(6));
                        pipeline.addFirst(new OutboundHandler(7));
                        pipeline.addFirst(new OutboundHandler(8));
                        pipeline.addFirst(new OutboundHandler(9, true));
                        pipeline.addFirst(new StringEncoder());


                        pipeline.addLast(new StringDecoder());
                        pipeline.addLast(new InboundHandler(1));
                        pipeline.addLast(new InboundHandler(2));
                        pipeline.addLast(new InboundHandler(3));
                        pipeline.addLast(new InboundHandler(4));
                        pipeline.addLast(new InboundHandler(5, true));
                    }
                })
                //维持链接的活跃，清除死链接(SocketChannel的设置)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                //关闭延迟发送
                .childOption(ChannelOption.TCP_NODELAY, true);

        try {
            //绑定端口
            ChannelFuture future = bootstrap.bind(8866).sync();
            System.out.println("server start ...... ");
            //等待服务端监听端口关闭
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //优雅退出，释放线程池资源
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }
}

class InboundHandler extends ChannelInboundHandlerAdapter {
    private int index = 0;
    private boolean lastOne = false;

    public InboundHandler(int index) {
        this(index, false);
    }

    public InboundHandler(int index, boolean lastOne) {
        this.index = index;
        this.lastOne = lastOne;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println(index + ": " + this.getClass().getSimpleName() + ".channelRead" + ": " + msg);
        // 通知执行下一个InboundHandler
        if (lastOne) {
            ctx.writeAndFlush(msg + "[" + index + "],");
        } else {
            ctx.fireChannelRead(msg + "[" + index + "],");
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println(index + ": " + this.getClass().getSimpleName() + ".channelReadComplete" + ": ");
        ctx.flush();
    }
}

class OutboundHandler extends ChannelOutboundHandlerAdapter {
    private int index = 0;

    private boolean flush = false;

    public OutboundHandler(int index) {
        this(index, false);
    }

    public OutboundHandler(int index, boolean flush) {
        this.index = index;
        this.flush = flush;
    }

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        System.out.println(index + ": " + this.getClass().getSimpleName() + ".write" + ": " + msg);
        // 执行下一个OutboundHandler
        if (flush) {
            ctx.writeAndFlush(msg + "[" + index + "],", promise);
            ctx.flush();
        } else {
            super.write(ctx, msg + "[" + index + "],", promise);
        }
    }
}

