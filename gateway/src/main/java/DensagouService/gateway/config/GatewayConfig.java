package DensagouService.gateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import DensagouService.gateway.auth.AuthenticationFilter;

@Configuration
public class GatewayConfig {

	@Autowired
	AuthenticationFilter filter;

	@Bean
	public RouteLocator routes(RouteLocatorBuilder builder) {

		return builder.routes()
				.route("userService", r -> r.path("/users/**").filters(f -> f.filter(filter)).uri("lb://userService"))
				.route("auth-service", r -> r.path("/auth/**").filters(f -> f.filter(filter)).uri("lb://auth-service"))
				.build();
	}

}
