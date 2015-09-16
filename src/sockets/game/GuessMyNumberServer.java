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
package sockets.game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

/**
 * GuessMyNumberServer asks clients to guess a random number.
 * 
 * For each client guess, the server returns:
 *   - "Too big!": if `guess` is greater than `toGuess`
 *   - "Too small!": if `guess` is lower than `toGuess`
 *   - "YOU WON!": if `guess` equals `toGuess`
 */
public class GuessMyNumberServer {
    
    /** Number to guess by the client. **/
    Integer toGuess;
    
    /** Default port to listen. **/
    Integer port = 3000;
    
    public GuessMyNumberServer() {
        Random rand = new Random();
        toGuess = rand.nextInt(100);
        System.out.println("Number to guess is " + toGuess);
    }
    
    /** Start listening on `port`. **/
    public void start() {
        System.out.println("Listening on port " + port + "...");
        try (ServerSocket listener = new ServerSocket(port)) {
            while (true) {
                try (Socket socket = listener.accept()) {
                    // To read client inputs
                    BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    // To write responses to client
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                    out.println("Hi there! Guess a number between 0 and 100!");
                    
                    while(true) {
                        String message = input.readLine();
                        try {
                            System.out.println("CLIENT SAYS: \"" + message + "\"");
                            Integer guess = Integer.parseInt(message);
                            if(guess < toGuess) {
                                out.println(guess + " is too small!");
                            } else if(guess > toGuess) {
                                out.println(guess + " is too big!");
                            } else {
                                out.println("YOU WON!");
                                System.exit(0);
                            }
                        } catch(NumberFormatException ne) {
                            out.println("This is not an integer! Try again!");
                        }
                    }
                }
            }
        } catch(IOException e) {
            System.out.println("Can't open server!");
            System.out.println("Cause: " + e.getMessage());
            System.exit(1);
        }
    }
}
