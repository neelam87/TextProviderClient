package com.client.textPro;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;

/**
 * @author neel
 *
 */
public class Client 
{
    public static void main( String[] args ) throws IOException
    {   
        getText(args[0]);
    }

	private static void getText(String lineNumber) throws UnknownHostException, IOException {
		URL url;
        try {
            url = new URL("http://localhost:8080/GET/");
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
            return;
        }

        String hostname = url.getHost();
        int port = 10322;

    	Socket socket = new Socket(hostname, port);

        try{
            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);
            writer.println(lineNumber);
            writer.println();

            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (UnknownHostException ex) {
            System.out.println("Server not found: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("I/O error: " + ex.getMessage());
     
        }
        finally {
        	socket.close();
        }
		
	}
}
