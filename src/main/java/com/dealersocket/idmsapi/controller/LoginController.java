package com.dealersocket.idmsapi.controller;
import com.dealersocket.idmsapi.jwt.JwtUtil;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> login(@RequestParam(required = false) String username, @RequestParam(required = false) String password) {

        if (authenticate(username, password)) {
            // In a real scenario, authentication logic would happen here
            String token=jwtUtil.createToken(username);
            return  ResponseEntity.ok(token);
        }
        return ResponseEntity.status(401).body("Invalid credentials");
    }
}
