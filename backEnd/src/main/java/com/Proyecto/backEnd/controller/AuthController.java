package com.Proyecto.backEnd.controller;

import java.time.LocalDate;
import java.util.stream.Collectors;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Proyecto.backEnd.config.JwtUtil;
import com.Proyecto.backEnd.model.UsuariosModel;
//import com.Proyecto.backEnd.model.RolesModel; 
import com.Proyecto.backEnd.service.UsuariosService;
import com.Proyecto.backEnd.utils.AuthRequest;
import com.Proyecto.backEnd.utils.AuthResponse;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

        private final AuthenticationManager authenticationManager;
        private final UserDetailsService userDetailsService;
        private final JwtUtil jwtUtil;
        private final UsuariosService usuService;

        public AuthController(AuthenticationManager authenticationManager,
                        UserDetailsService userDetailsService,
                        JwtUtil jwtUtil,
                        UsuariosService usuService) {
                this.authenticationManager = authenticationManager;
                this.userDetailsService = userDetailsService;
                this.jwtUtil = jwtUtil;
                this.usuService = usuService;
        }

        // Exception
        @PostMapping("/login")
        public ResponseEntity<?> login(@RequestBody AuthRequest request) {
                authenticationManager.authenticate(
                                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

                final UserDetails user = userDetailsService.loadUserByUsername(request.getUsername());
                String token = jwtUtil.generateToken(user.getUsername());
                UsuariosModel usuario = usuService.buscarPorLogin(request.getUsername());

                String nombreCompleto = usuario.getPersonal().getNombre() + " " +
                                usuario.getPersonal().getAp() +
                                (usuario.getPersonal().getAm() != null ? " " + usuario.getPersonal().getAm() : "");

                return ResponseEntity.ok(
                                new AuthResponse(
                                                token,
                                                nombreCompleto,
                                                usuario.getRoles(),
                                                LocalDate.now().toString()));
        }
}