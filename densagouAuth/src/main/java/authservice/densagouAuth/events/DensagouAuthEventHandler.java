package authservice.densagouAuth.events;

import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import authservice.densagouAuth.service.AuthService;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * This class receives the events and triggers the associated business logic
 *
 */
@Component
@Slf4j
public class DensagouAuthEventHandler {
	
	private AuthService authService;

	
	@Autowired
	public DensagouAuthEventHandler(AuthService authService) {
		super();
		this.authService = authService;
	}
	@RabbitListener(queues = "${userService.queue}")
	public void handlerMultiplicationSolved(final UserServiceSolvedEvent event) {

		log.info("UserService solved Event received: {}", event.getUserId());

		try {
			authService.findByUsername(event.getEmail());
		} catch (Exception e) {

			log.error("Error when trying to process UserSericeSolvedEvent", e);

			// Avoids the event to be re-queued and reprocessed

			throw new AmqpRejectAndDontRequeueException(e);
		}

	}
	

}
