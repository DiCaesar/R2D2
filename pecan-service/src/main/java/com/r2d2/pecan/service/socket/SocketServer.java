package com.r2d2.pecan.service.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by DiCaesar on 2017/11/22
 */
public class SocketServer {

    public static void main(String srgs[]) throws IOException {
        SocketServer socketServer = new SocketServer();
        socketServer.init(8900);
    }

    public void init(int port) throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("init...");
        while (true){
            Socket socket = serverSocket.accept();  //blocking
            new HandleThread(socket);
        }
    }

    private class HandleThread implements Runnable{
        private Socket socket;

        HandleThread(Socket socket){
            this.socket = socket;
            new Thread(this).run();
        }

        @Override
        public void run() {
            try{
                int port = socket.getPort();
                System.out.println("connect new..."+socket.getInetAddress()+"--"+socket.getPort());
                while (true) {
                    InputStream is = socket.getInputStream();
                    byte[] bytes = new byte[1024];
                    int line = is.read(bytes);  //blocking
                    if (line != -1) {
                        String str = new String(bytes, "GBK");
                        System.out.println("accept str form client " +port+":"+ str);
                    }else {
                        break;
                    }
                }
                this.socket.close();
                System.out.println("connect close..."+port);

            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }



//        PrintStream out = new PrintStream(socket.getOutputStream());
//        System.out.print("请输入:\t");
//
//        String s = new BufferedReader(new InputStreamReader(System.in)).readLine();
//        out.println(s);
//
//        out.close();

}
