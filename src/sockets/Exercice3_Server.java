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
import java.net.ServerSocket;
import java.net.Socket;

/**
 * This class provides an example of tiny server using Java ServerSocket.
 * 
 * As a demonstration purpose, the client is asked to give is language (EN or FR)
 * then the server responds a welcome message in the good idiom and closes the
 * connection.
 */
public class Exercice3_Server {
    public static void main(String[] args) throws IOException {
        System.out.println("Listening on port 3000...");
        try (ServerSocket server = new ServerSocket(3000)) {
            while (true) {
                try (Socket socket = server.accept()) {                  
                    // To read from the client
                    BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    // To write to the client
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                    
                    // Welcome message to client
                    out.println("Hi there! What's your language? Type either `EN` or `FR`");
                    
                    LISTEN: // Listening loop
                    while (true) {
                        // Read the response given by the client
                        switch (input.readLine()) {
                            case "EN":
                                out.println("Hello World!");
                                break LISTEN;
                            case "FR":
                                out.println("Bonjour le monde!");
                                break LISTEN;
                            default:
                                out.println("Unrecognized language, try again!");
                                break;
                        }
                    }
                }
            }
        }
    }
}
