package DensagouService.userService.events;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EventDispatcher {

	private RabbitTemplate rabbitTemplate;

	// The exchange to use to send anything related to userService
	private String userServiceExchange;

	// The routing key to use to send this particular event
	private String userServiceRoutingKey;

	@Autowired
	public EventDispatcher(RabbitTemplate rabbitTemplate, @Value("${userService.exchange}") final String userServiceExchange,
			@Value("${userService.solved.key}") final String userServiceRoutingKey) {
		super();
		this.rabbitTemplate = rabbitTemplate;
		this.userServiceExchange = userServiceExchange;
		this.userServiceRoutingKey = userServiceRoutingKey;
	}

	public void send(final UserServiceSolvedEvent userServiceSolvedEvent) {

		rabbitTemplate.convertAndSend(userServiceExchange, userServiceRoutingKey, userServiceSolvedEvent);
	}

}
