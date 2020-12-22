package com.ctillnow.io;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 2 * @Author: Cai
 * 3 * @Date: 2020/7/25 18:38
 * 4
 */
public class IOServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket= new ServerSocket(8000);
        while (true){
            Socket socket = serverSocket.accept();
            new Thread(()->{
                String name = Thread.currentThread().getName();
                try {
                    byte[] bytes = new byte[1024];
                    InputStream inputStream = socket.getInputStream();
                    while (true){
                        int len;
                        while ((len = inputStream.read(bytes))!=-1){
                            System.out.println("线程"+name+":"+ new String(bytes,0,len));
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();

        }

    }
}
