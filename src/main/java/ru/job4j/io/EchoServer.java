package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    String firstStr = in.readLine();
                    System.out.println(firstStr);
                    for (String str = in.readLine(); str != null && !str.isEmpty(); str = in.readLine()) {
                        System.out.println(str);
                    }
                    out.flush();
                    if ("Bye".equals(firstStr.
                            substring(firstStr.indexOf("=") + 1, firstStr.lastIndexOf(" HTTP/1.1")))) {
                        server.close();
                        System.out.println("The server has been closed.");
                    }
                }
            }
        }
    }
}
