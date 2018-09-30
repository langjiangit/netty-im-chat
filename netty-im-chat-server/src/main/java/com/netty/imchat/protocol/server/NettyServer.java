package com.netty.imchat.protocol.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

/**
 * @author Kevin
 * @Title: NettyServer
 * @ProjectName studyjava
 * @Description: TODO  netty服务器
 * @date 2018/9/30 9:32
 */
public class NettyServer {
    /**
     * 启动类
     */
    public static void run() {
        //启动类
        ServerBootstrap bootstrap = new ServerBootstrap();

        //两大线程
        //连接线程
        NioEventLoopGroup connectGroup = new NioEventLoopGroup();
        //处理消息线程
        NioEventLoopGroup messageGroup = new NioEventLoopGroup();

        bootstrap.
                group(connectGroup, messageGroup).
                //制定服务端的IO模型 -- 当然，这里也有其他的选择，如果你想指定 IO 模型为 BIO，那么这里配置上OioServerSocketChannel.class类型即可，当然通常我们也不会这么做，因为Netty的优势就在于NIO。
                channel(NioServerSocketChannel.class).
                childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                        nioSocketChannel.pipeline().addLast(new ServerHandler());
                    }
                });

        bind(bootstrap, 8000);
    }

    private static void bind(ServerBootstrap boot, final int port){
        boot.bind(port).addListener(new GenericFutureListener<Future<? super Void>>() {
            @Override
            public void operationComplete(Future<? super Void> future) throws Exception {
                if(future.isSuccess()){
                    System.out.println("成功绑定端口:"+ port);
                }else{
                    System.err.println("失败绑定端口:" + port);
                    //端口 是 0~65535 [可以相应设计白名单/黑名单]
                    if(port >= 65535){
                        System.out.println("所有端口都绑定不成功");
                        return;
                    }
                    bind(boot, port+1);
                }
            }
        });
    }
}