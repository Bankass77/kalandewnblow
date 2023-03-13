package authservice.densagouAuth.events;

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@EqualsAndHashCode
@Getter
@RequiredArgsConstructor
@ToString
public class UserServiceSolvedEvent implements Serializable {
	private static final long serialVersionUID = 1L;
	private final Long userId;
	private String email;
	private String password;
	private String firstName;
	private String lastName;

}
