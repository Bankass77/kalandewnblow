package authservice.densagouAuth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import authservice.densagouAuth.domain.User;
import authservice.densagouAuth.request.AuthRequest;
import authservice.densagouAuth.request.AuthResponse;
import reactor.core.publisher.Mono;

@Service
public class AuthService {
	private final RestTemplate restTemplate;
    private final JwtUtil jwt;

    @Autowired
    public AuthService(RestTemplate restTemplate,
                       final JwtUtil jwt) {
        this.restTemplate = restTemplate;
        this.jwt = jwt;
    }

    public AuthResponse register(AuthRequest authRequest) {
        //do validation if user already exists
        authRequest.setPassword(BCrypt.hashpw(authRequest.getPassword(), BCrypt.gensalt()));

        User userVO = restTemplate.postForObject("http://userService/users", authRequest, User.class);
        Assert.notNull(userVO, "Failed to register user. Please try again later");

        String accessToken = jwt.generate(userVO, "ACCESS");
        String refreshToken = jwt.generate(userVO, "REFRESH");

        return new AuthResponse(accessToken, refreshToken);

    }
    
    public Mono<User> findByUsername(String username){
    	
    	return Mono.justOrEmpty(null);
    }

}
