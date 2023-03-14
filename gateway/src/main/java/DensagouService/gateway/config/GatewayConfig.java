package DensagouService.gateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.loadbalancer.core.RandomLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ReactorLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.cloud.loadbalancer.support.LoadBalancerClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;

import DensagouService.gateway.auth.AuthenticationFilter;

@Configuration
@LoadBalancerClient(name = "microservices-densagou")
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
	
	@LoadBalanced
	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	@Bean
	ReactorLoadBalancer<ServiceInstance> randomLoadBalancer(Environment environment,
	        LoadBalancerClientFactory loadBalancerClientFactory) {
	    String name = environment.getProperty(LoadBalancerClientFactory.PROPERTY_NAME);
	    return new RandomLoadBalancer(loadBalancerClientFactory
	            .getLazyProvider(name, ServiceInstanceListSupplier.class),
	            name); 
	  }

}
