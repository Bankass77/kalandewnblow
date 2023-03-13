package DensagouService.gateway.jwt;

import java.security.Key;
import java.util.Date;

import javax.swing.JComboBox.KeySelectionManager;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtUtil {

	private String jwtSecret;

	private Key key;

	@Value("${app.jwtExpiration}")
	private int jwtExpiration;

	@PostConstruct
	public void init() {

		this.key = Keys.hmacShaKeyFor(jwtSecret.getBytes());
	}

	public Claims getAllClaimsFromToken(String token) {

		return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
	}

	private boolean IsTokeExpired(String token) {

		return this.getAllClaimsFromToken(token).getExpiration().before(new Date());

	}

	public boolean isInvalid(String token) {

		return false;
	}

	
}
