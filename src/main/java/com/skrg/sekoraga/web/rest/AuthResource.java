package com.skrg.sekoraga.web.rest;

import com.skrg.sekoraga.service.AuthService;
import com.skrg.sekoraga.web.rest.vm.LoginVM;
import com.skrg.sekoraga.web.rest.vm.RegisterVM;
import com.skrg.sekoraga.web.rest.vm.ForgotPasswordVM;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthResource {
    private final AuthService authService;

    public AuthResource(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterVM registerVM) {
        return authService.register(registerVM);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginVM loginVM) {
        return authService.login(loginVM);
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestBody ForgotPasswordVM forgotPasswordVM) {
        return authService.forgotPassword(forgotPasswordVM);
    }
}
