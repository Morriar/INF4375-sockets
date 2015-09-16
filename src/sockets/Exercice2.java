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
import java.net.Socket;

/**
 * Socket example.
 * 
 * This client connects to the server of Exercice1 and display the server
 * response.
 */
public class Exercice2 {
    public static void main(String[] args) throws IOException {
        System.out.println("Connect to server localhost:3000...");
        // Open a new socket connection to "localhost:3000"
        Socket socket = new Socket("localhost", 3000);
        // Open a reader used to read messages sent by the server
        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        // Read a line in the input stream
        System.out.println(input.readLine());
        // Close the scket
        socket.close();
    }
}
