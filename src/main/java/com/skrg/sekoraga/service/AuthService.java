package com.skrg.sekoraga.service;

import com.skrg.sekoraga.domain.AdAuthority;
import com.skrg.sekoraga.domain.AdUser;
import com.skrg.sekoraga.repository.AdUserRepository;
import com.skrg.sekoraga.repository.AdAuthorityRepository;
import com.skrg.sekoraga.security.jwt.TokenProvider;
import com.skrg.sekoraga.web.rest.vm.LoginVM;
import com.skrg.sekoraga.web.rest.vm.RegisterVM;
import com.skrg.sekoraga.web.rest.vm.ForgotPasswordVM;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.Set;
import java.util.HashSet;

@Service
public class AuthService {
    private final AdUserRepository userRepository;
    private final AdAuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;

    public AuthService(AdUserRepository userRepository, AdAuthorityRepository authorityRepository,
            PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, TokenProvider tokenProvider) {
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
    }

    public ResponseEntity<?> register(RegisterVM vm) {
        // Validasi username/email unik
        if (userRepository.existsByUsername(vm.getUsername())) {
            return ResponseEntity.badRequest().body("Username already used");
        }
        if (userRepository.existsByEmail(vm.getEmail())) {
            return ResponseEntity.badRequest().body("Email already used");
        }
        // Ambil role TEACHER
        Optional<AdAuthority> teacherRole = authorityRepository.findFirstByName("SISWA");
        if (teacherRole.isEmpty()) {
            return ResponseEntity.badRequest().body("Role TEACHER not found");
        }
        AdUser user = new AdUser();
        user.setUsername(vm.getUsername());
        user.setEmail(vm.getEmail());
        user.setPassword(passwordEncoder.encode(vm.getPassword()));
        Set<AdAuthority> authorities = new HashSet<>();
        authorities.add(teacherRole.get());
        user.setAuthorities(authorities);
        userRepository.save(user);
        return ResponseEntity.ok("User registered successfully");
    }

    public ResponseEntity<?> login(LoginVM vm) {
        Optional<AdUser> userOpt = userRepository.findFirstByUsernameOrEmail(vm.getUsernameOrEmail(),
                vm.getUsernameOrEmail());
        if (userOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("User not found");
        }
        AdUser user = userOpt.get();
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), vm.getPassword()));
        String token = tokenProvider.createToken(authentication, Optional.of(user), false);
        return ResponseEntity.ok(token);
    }

    public ResponseEntity<?> forgotPassword(ForgotPasswordVM vm) {
        Optional<AdUser> userOpt = userRepository.findFirstByUsernameOrEmail(vm.getUsername(), vm.getEmail());
        if (userOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("User not found");
        }
        return ResponseEntity.ok("Reset password link sent to email (mock)");
    }
}
