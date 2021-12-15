package com.wzy.rpc.transport;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @program: simple-rpc
 * @description: 服务端网络模块
 * @author: WZY
 * @create: 2021-12-09 18:25
 **/
@Slf4j
public class HttpTransportServer implements TransportServer {
    private RequestHandler handler; // 服务端具体处理请求的方法（输入流进入、输出流返回）
    private Server server;
    @Override
    public void init(int port, RequestHandler handler) {
        this.handler = handler;
        this.server = new Server(port);
        // servlet 接收请求，设置ctx进入server
        ServletContextHandler ctx = new ServletContextHandler();
        server.setHandler(ctx);

        ServletHolder holder = new ServletHolder(new RequestServlet());
        ctx.addServlet(holder, "/*");
    }

    @Override
    public void start() {
        try{
            server.start();
            server.join();
        }catch (Exception e){
            log.error(e.getMessage(), e);
        }
    }

    @Override
    public void stop() {
        try{
            server.stop();
        }catch (Exception e){
            log.error(e.getMessage(), e);
        }
    }

    /**
     * 重写了以POST形式提交的处理方法
     */
    class RequestServlet extends HttpServlet{
        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            log.info("client connected !");

            InputStream in = req.getInputStream();
            OutputStream out = resp.getOutputStream();
            if (handler != null){
                handler.onRequest(in, out);
            }
            out.flush();
        }
    }
}
