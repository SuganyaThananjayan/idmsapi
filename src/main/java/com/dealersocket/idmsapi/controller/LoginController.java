package com.dealersocket.idmsapi.controller;
import com.dealersocket.idmsapi.jwt.JwtUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class  LoginController {

    private final JwtUtil jwtUtil;
    public LoginController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }
    private boolean authenticate(String username, String password) {
        // For simplicity, hardcoding the username/password check.
        return "admin".equals(username) && "DriveSoft@@!".equals(password);
    }
    @GetMapping("/login")
    public String login(@RequestParam(required = false) String username,@RequestParam(required = false) String password) {

        System.out.println("username"+username);
        System.out.println("password"+password);
        if (username.isEmpty() || password.isEmpty()) {
            return "Username or password is missing!";
        }
        if (authenticate(username, password)) {
            // In a real scenario, authentication logic would happen here
            return jwtUtil.createToken(username);
        }
        return "Invalid credentials!";
    }
}
