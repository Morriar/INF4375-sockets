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

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * ServerSocket example.
 * 
 * This server only sends "Hello World!" to all connecting clients.
 */
public class Exercice1 {
    public static void main(String[] args) throws IOException {
        System.out.println("Listening on port 3000...");
        // Open a SocketServer on port 3000
        try (ServerSocket server = new ServerSocket(3000)) {
            // Infinite loop, waiting for clients to connect
            while (true) {
                // Try to accept client connection and open socket to the client
                try (Socket socket = server.accept()) {
                    // Prepare response writer, so you can writte to the client
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                    // Print "Hello World" in client output stream
                    out.println("Hello World!");
                }
            }
        }
    }
}
