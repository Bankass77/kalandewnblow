package authservice.densagouAuth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import authservice.densagouAuth.request.AuthRequest;
import authservice.densagouAuth.request.AuthResponse;
import authservice.densagouAuth.service.AuthService;
import authservice.densagouAuth.service.JwtUtil;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/auth")
public class AuthController {

	private final AuthService authService;
	 private  final JwtUtil jwtUtil;
     private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	public AuthController(final AuthService authService, JwtUtil jwtUtil,BCryptPasswordEncoder bCryptPasswordEncoder) {

		this.authService = authService;
		this.jwtUtil= jwtUtil;
		this.bCryptPasswordEncoder= bCryptPasswordEncoder;
	}

	@PostMapping(value = "/register")
	public Mono<ResponseEntity<AuthResponse>> register(@RequestBody AuthRequest authRequest) {
		return Mono.just(ResponseEntity.ok(authService.register(authRequest)));
	}

	@PostMapping("/login")
	public Mono<ResponseEntity<AuthResponse>> login(@RequestBody AuthRequest authRequest){
		return authService.findByUsername(authRequest.getEmail()).filter(userDetails -> bCryptPasswordEncoder.encode(authRequest.getPassword())
				.equals(userDetails.getPassword()))
	            .map(userDetails -> ResponseEntity.ok(new AuthResponse(jwtUtil.generate(userDetails, userDetails.getEmail()), null)))
	            .switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()));
	    

	

	}
}
