package com.uluoli.nettydemo.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

import com.uluoli.nettydemo.config.Config;

public class HelloServer {
	
	public static void main(String[] args) throws Exception{
		System.out.println("开始启动TCP服务器...");
		//server 服务启动器
		HelloServer.service();
	
	}
	
	public static void service() throws Exception{
		ServerBootstrap bootstrap = new ServerBootstrap();
		bootstrap.group(Config.bossGroup, Config.workerGroup);
		bootstrap.channel(NioServerSocketChannel.class);
		bootstrap.childHandler(new ChannelInitializer<Channel>(){
			@Override
			protected void initChannel(Channel ch) throws Exception {
				ChannelPipeline pipeline = ch.pipeline();
				pipeline.addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
				pipeline.addLast(new LengthFieldPrepender(4));
				//字符串解码和编码
				pipeline.addLast(new StringDecoder(CharsetUtil.UTF_8));
				pipeline.addLast(new StringEncoder(CharsetUtil.UTF_8));
				//自定义handler
				pipeline.addLast(new TcpServerHandler());
			}
		});
		
		//绑定端口
		ChannelFuture f = bootstrap.bind(Config.IP, Config.PORT).sync();
		//临时服务器关闭监听
		f.channel().closeFuture().sync();
		System.out.println("TCP服务器已启动");
	}
	
	protected static void shutdown(){
		Config.bossGroup.shutdownGracefully();
		Config.workerGroup.shutdownGracefully();
	}
	
}
