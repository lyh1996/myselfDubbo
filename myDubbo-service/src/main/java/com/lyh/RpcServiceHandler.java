package com.lyh;

import com.lyh.bean.RpcRequest;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 633805 LYH
 * @version V1.0
 * @description netty监听端口
 * @create 2019-08-03 15:30
 * @since 1.7
 */
public class RpcServiceHandler extends ChannelInboundHandlerAdapter {

    private Map<String, Object> handleMap = new HashMap<>();

    public RpcServiceHandler(Map<String, Object> handleMap) {
        this.handleMap = handleMap;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // ctx 写东西给客户端   msg  从客户端读取数据
        RpcRequest rpcRequest = (RpcRequest) msg;
        Object result = new Object();
        if (handleMap.containsKey((rpcRequest).getClassName())) {
            Object clazz = handleMap.get(rpcRequest.getClassName());
            Method method =clazz.getClass().getMethod(rpcRequest.getMethodName(), rpcRequest.getTypes());
            result = method.invoke(clazz, rpcRequest.getParams());
        }
        ctx.write(result);
        ctx.flush();
        ctx.close();
    }
}
