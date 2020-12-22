package com.ctillnow.io;

import io.lettuce.core.resource.NettyCustomizer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * 2 * @Author: Cai
 * 3 * @Date: 2020/7/25 18:54
 * 4
 */
public class IOClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1",8000);
        System.out.println(socket.getInetAddress());
        while (true){
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write(new Scanner(System.in).nextLine().getBytes());
            outputStream.flush();
        }

    }
}
