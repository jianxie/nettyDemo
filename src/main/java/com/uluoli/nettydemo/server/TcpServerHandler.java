package com.uluoli.nettydemo.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class TcpServerHandler extends ChannelInboundHandlerAdapter {

	//建立连接
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		 System.out.println("channelActive>>>>>>>>");
	}

	//收到 消息
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		System.out.println("server receive message" + msg);
		ctx.channel().writeAndFlush("yes server already accept your message" + msg);
		
		ctx.close();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		System.out.println("exception is general");
	}
}
