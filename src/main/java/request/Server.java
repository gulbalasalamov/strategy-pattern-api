package request;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Server {
    private static HttpServer server;
    private static String code;

    public static void start(int port) {
        try {
            server = HttpServer.create();
            server.bind(new InetSocketAddress((port)), 0);
        } catch (IOException e) {
            e.printStackTrace();
        }

        server.start();

        server.createContext("/", exchange -> {
            String query = exchange.getRequestURI().toString();
            String response;
            if (query.contains("code=")) {
                code = query.split("=")[1];
                response = "Got the code. Return back to your program.";
            } else {
                response = "Authorization code not found. Try again.";
            }
            exchange.sendResponseHeaders(200, response.length());
            exchange.getResponseBody().write(response.getBytes());
            exchange.getResponseBody().close();
        });

        System.out.println("waiting for code...");
        while (code == null) {
            sleep();
        }
        server.stop(5);
    }

    public static String getCode() {
        return code;
    }

    private static void sleep() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}