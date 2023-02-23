package DensagouService.userService.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface LogInService {

	void logRequest(HttpServletRequest httpServletRequest, Object object);

	void logResponse(HttpServletRequest httpServletRequest, HttpServletResponse response, Object body);

}
