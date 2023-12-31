package it.unitn.webarch.recap;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketClientDemo {
    public static void main(String a[]) {
        try {

            Socket server = new Socket("localhost", 8000);
            InputStream in = server.getInputStream();
            InputStreamReader din = new InputStreamReader(in);
            BufferedReader bin = new BufferedReader(din);

            OutputStream out = server.getOutputStream();
            PrintStream pout = new PrintStream(out);

            // Say "Hello" (send newline delimited string)
            String greeting="Hello";
            pout.println(greeting);
            System.out.println("Said to the server "+greeting);

            // Read a newline delimited string
            String response = bin.readLine();
            System.out.println("Got from the server "+response);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
