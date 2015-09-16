/*
 * Copyright 2015 Alexandre Terrasa <alexandre@moz-code.org>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Socket client that sends a message to the server.
 * 
 * This client connects to the server of `Exercice3_Server` and displays
 * the server response.
 */
public class Exercice3_Client {
    public static void main(String[] args) throws IOException {
        System.out.println("Connect to server localhost:3000...");
        // Open a new socket connection to "localhost:3000"
        Socket socket = new Socket("localhost", 3000);
        // Open a reader used to read messages sent by the server
        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        // Open a print writer on the output stream to send message to the server
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        // Read a line in the input stream
        System.out.println(input.readLine());
        // Send lang to server
        out.println("FR");
        System.out.println(input.readLine());
    }
}
