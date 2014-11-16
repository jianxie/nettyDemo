package com.uluoli.nettydemo.config;

import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;

public class Config {
	public static final String IP = "127.0.0.1";
	public static final int PORT = 7878;
	public static final int BIZGROUPSIZE = Runtime.getRuntime().availableProcessors() * 2;
	public static final int BIZTHREADSIZE = 100;
	public static final EventLoopGroup bossGroup = new NioEventLoopGroup(BIZGROUPSIZE);
	public static final EventLoopGroup workerGroup = new NioEventLoopGroup(BIZTHREADSIZE);
	
}
