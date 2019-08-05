package com.lyh;

import com.lyh.register.IRegisterCenter;
import com.lyh.register.RegisterCenterImpl;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException {
        System.out.println( "Hello World!" );

        IRegisterCenter iRegisterCenter = new RegisterCenterImpl();
        iRegisterCenter.register("com.lyh.DubboHello", "127.0.0.1:9090");
        System.in.read();
    }
}
