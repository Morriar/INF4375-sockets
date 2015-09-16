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
import java.util.Scanner;

/**
 * Game client for `GuessMyNumberServer`.
 * 
 * This client connects to `Exercice4_Server` and plays the game.
 */
public class Exercice4_Client {
    public static void main(String[] args) throws IOException {
        System.out.println("Connect to server localhost:3000...");
        Socket s = new Socket("localhost", 3000);
        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        PrintWriter out = new PrintWriter(s.getOutputStream(), true);
        Scanner scan = new Scanner(System.in);
        while(true) {
            // Read line sent by server
            String message = in.readLine();
            System.out.println(message);
            
            // Stop the loop when game is over
            if(message.equals("YOU WON!")) {
                break;
            }
            
            // Get guess from stdin and send it to server
            System.out.println("Enter your value:");
            out.println(scan.nextLine());
        }
    }
}
