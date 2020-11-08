package com.natsuki_kining.gupao.v2.distributed.netty.bio;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

@Slf4j
public class ServerHandler implements Runnable {

    private Socket socket;

    public ServerHandler(Socket socket) {
        this.socket = socket;
    }

    //@Override
    public void run() {
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            String expression;
            String result;
            while (true) {
                if ((expression = in.readLine()) == null) break;
                System.out.println("服务端收到信息：" + expression);
                //log.info("服务端收到信息：" + expression);

                result = Calculator.cal(expression);
                out.println(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getLocalizedMessage());
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                in = null;//方便GC回收
            }

            if (out != null) {
                try {
                    out.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                out = null;//方便GC回收
            }

            if (socket != null) {
                try {
                    socket.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                socket = null;//方便GC回收
            }

        }
    }
}
