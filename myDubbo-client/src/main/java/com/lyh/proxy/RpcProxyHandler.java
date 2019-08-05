package com.lyh.proxy;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author 633805 LYH
 * @version V1.0
 * @description 对类的描述
 * @create 2019-08-05 9:56
 * @since 1.7
 */
public class RpcProxyHandler extends ChannelInboundHandlerAdapter {

    private Object response;
    public Object getResponse() {
        return  response;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // ctx向服务器写数据   msg读取服务端传来的数据
        response = msg;
    }
}
