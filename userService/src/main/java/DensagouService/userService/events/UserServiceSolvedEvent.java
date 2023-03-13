package DensagouService.userService.events;

import java.io.Serializable;

import groovy.transform.EqualsAndHashCode;
import groovy.transform.ToString;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Event taht models the fact that a
 * {@link DensagouService.userService.domain.Utilisateur} has been solved in the
 * system. Provides some context information about utilisateur
 * 
 *
 */
@RequiredArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class UserServiceSolvedEvent implements Serializable {
	private static final long serialVersionUID = 1L;
	private final Long userId;
	private String email;
	private String password;
	private String firstName;
	private String lastName;


}
