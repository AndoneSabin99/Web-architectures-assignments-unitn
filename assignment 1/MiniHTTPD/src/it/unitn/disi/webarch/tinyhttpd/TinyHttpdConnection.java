package it.unitn.disi.webarch.tinyhttpd;

import java.io.*;
import java.net.Socket;
import java.util.StringTokenizer;

class TinyHttpdConnection extends Thread {

    Socket sock;

    TinyHttpdConnection(Socket s) {
        sock = s;
        setPriority(NORM_PRIORITY - 1);
        start();
    }

    //startProcess is a function that executes the external process
    public void startProcess(String token, String result){
        //we need to consider the operating system on which we are running the JVM.
        boolean isWindows = System.getProperty("os.name").toLowerCase().startsWith("windows");

        //we create the ProcessBuilder in order to launch the external process
        ProcessBuilder processBuilder = new ProcessBuilder();

        if (isWindows){
            // -- Windows --
            String path = System.getProperty("user.dir") + "\\process";
            //System.out.println("Path: " + path);
            path = path + "\\" + token + ".bat";
            //System.out.println("Path: " + path);

            // Run a command; we need to check if we are calling reverse
            // process or not, since reverse requires also a string parameter
            if (token.equals("reverse")){

                processBuilder.command("cmd.exe", "/c", path, result);
            }
            else{
                processBuilder.command("cmd.exe", "/c", path);
            }

        }else{
            // -- Linux and macOS --
            String path = System.getProperty("user.dir") + "/process";
            //System.out.println("Path: " + path);
            path = path + "\\" + token + ".sh";
            //System.out.println("Path: " + path);

            // Run a command; we need to check if we are calling reverse process or not, since reverse requires also
            // a string parameter
            if (token.equals("reverse")){
                processBuilder.command("bash", "-c", path, result);
            }
            else{
                processBuilder.command("bash", "-c", path);
            }
        }

        try {

            //execute the process
            Process process = processBuilder.start();
            StringBuilder output = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));


            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line + "\n");
            }

            int exitVal = process.waitFor();
            if (exitVal == 0) {
                System.out.println("Success!");
                System.out.println(output);
                //System.exit(0);
            } else {
                //abnormal...
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        System.out.println("=========");
        System.out.println("Connected on port "+ sock.getPort());
        OutputStream out = null;
        try {

            // OPEN SOCKETS FOR READING AND WRITING
            BufferedReader d = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            out = sock.getOutputStream();
            PrintStream ps=new PrintStream(out);

            // READ REQUEST
            String req = d.readLine();
            if (req==null) return;
            System.out.println("Request: " + req);

            // READ REQUEST HEADERS
            String head=null;
            do {
                head = d.readLine();
                System.out.println("Header: " + head);
            } while (head!=null && head.length()>0);

            // PARSE REQUEST
            StringTokenizer st = new StringTokenizer(req);
            if ((st.countTokens() >= 2) && st.nextToken().equals("GET")) {
                if ((req = st.nextToken()).startsWith("/")) {

                    req = req.substring(1);
                    StringTokenizer st2 = new StringTokenizer(req);
                    //necessary to get "process" string
                    if (st2.hasMoreTokens()){
                        req = st2.nextToken("/");
                    }
                    if (req.equals("process")){
                        {
                            //check if there is something else written in the URL
                            if (st2.hasMoreTokens()){
                                req = st2.nextToken("?");
                                req = req.substring(1);
                            }

                            //check if it is the reverse request
                            if (req.equals("reverse")){
                                //take the parameter value

                                String par = "";
                                if (st2.hasMoreTokens()){
                                    st2.nextToken("=");
                                    par = st2.nextToken();
                                }

                                System.out.println("req: " + req + "; par1: " + par);

                                //compute the reverse of parameter value
                                StringBuilder sb=new StringBuilder(par);
                                sb.reverse();
                                String result = sb.toString();
                                System.out.println(result);

                                if (par.equals("")){
                                    req = "process.html";
                                    startProcess("process", "");
                                }else{
                                    req = "reverse.html";
                                    startProcess("reverse", result);
                                }
                            }
                            else
                            {
                                req = "process.html";
                                //since we are not calling reverse process, we do not need result parameter
                                startProcess("process", "");
                            }
                        }
                    }
                }
                if (req.endsWith("/") || req.equals("")) {
                    req = req + "index.html";
                }

                // OPEN REQUESTED FILE AND COPY IT TO CLIENT
                try {
                    //All our requested files must be in the "Documents" directory
                    //System.out.println(System.getProperty("user.dir") + "\\Documents\\" + req);
                    FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\Documents\\" + req);
                    int responseLength=fis.available();

                    // LET'S SEND THE RESPONSE HEADERS
                    ps.print("HTTP/1.1 200 OK\r\n");
                    ps.print("Content-Length: "+responseLength+"\r\n");
                    ps.print("Content-Type: text/html\r\n");
                    ps.print("\r\n");

                    // LET'S SEND THE CONTENT
                    byte[] data = new byte[responseLength];
                    fis.read(data);
                    out.write(data);
                    fis.close();
                } catch (FileNotFoundException e) {
                    ps.print("HTTP/1.1 404 Not Found \r\n\r\n");
                    System.out.println("404 Not Found: " + req);
                }
            } else {
                ps.print("HTTP/1.1 400 Bad Request\r\n\r\n");
                System.out.println("400 Bad Request: " + req);
            }

        } catch (IOException e) { // Let's catch all generic I/O troubles
            System.out.println("Generic I/O error " + e);
        } finally { // the following code is ALWAYS executed
            try {
                // let's close all channels
                out.close();
                sock.close();
            } catch (IOException ex) {
                System.out.println("I/O error on close" + ex);
            }
        }
    }
}
