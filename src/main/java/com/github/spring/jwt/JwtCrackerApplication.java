/**
 *   Copyright 2016 Pratapi Hemant Patel
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *   
 */
package com.github.spring.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.beust.jcommander.Parameter;


@SpringBootApplication
public class JwtCrackerApplication implements CommandLineRunner {

    @Autowired
    private ThreadService crackerService;
    
    @Override
    public void run(String... args) throws Exception {

        CommandLineOptions options = new CommandLineOptions();
        
       // new JCommander(options , args);
        
        System.out.printf(
                "cracking jwt token [%s] with %d threads upto password length %d%n",
                options.token(),
                options.maxThread(),
                options.maxKeyLength());
        
        crackerService.crackJwt(options);
    }
    
    public static void main(String[] args) {

        SpringApplication.run(JwtCrackerApplication.class, args);
    }
    
    static class CommandLineOptions {
        
        @Parameter(names = {"-j", "--jwt"}, description = "jwt token to be cracked", required = true)
        private String token;
        
        @Parameter(names = {"-m", "--max-thread"}, description = "max no of threads to be used, default is 20")
        private int maxThread = 20;
        
        @Parameter(names = {"-l", "--max-length"}, description = "max possible length of the jwt secret key")
        private int maxKeyLength = 5;
        
        public String token() {
            return token;
        }
        
        public int maxThread() {
            return maxThread;
        }
        
        public int maxKeyLength() {
            return maxKeyLength;
        }
    }
}