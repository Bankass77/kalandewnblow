package DensagouService.userService.service.impl;

import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import DensagouService.userService.service.LogInService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LoggingServiceImpl implements LogInService {

	@Override
	public void logRequest(HttpServletRequest httpServletRequest, Object body) {

		StringBuilder stringBuilder = new StringBuilder();
		Map<String, String> parameters = builderParametersMap(httpServletRequest);
		stringBuilder.append("REQUEST");
		stringBuilder.append("Method=[").append(httpServletRequest.getMethod()).append("]");
		stringBuilder.append("path=[").append(httpServletRequest.getRequestURI()).append("]");
		stringBuilder.append("headers=[").append(buildHeadersMap(httpServletRequest)).append("]");

		if (!parameters.isEmpty()) {
			stringBuilder.append("parameters=[").append(parameters).append("]");
		}

		if (body != null) {
			stringBuilder.append("body=[" + body + "]");
		}

		log.info(stringBuilder.toString());
	}

	private Map<String, String> buildHeadersMap(HttpServletRequest httpServletRequest) {

		Map<String, String> map = new HashMap<>();
		Enumeration<?> headerNames = httpServletRequest.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String key = (String) headerNames.nextElement();
			String vale = httpServletRequest.getHeader(key);
			map.put(key, vale);

		}

		return map;
	}

	private Map<String, String> builderParametersMap(HttpServletRequest httpServletRequest) {

		Map<String, String> resultMap = new HashMap<>();
		Enumeration<String> parameterNames = httpServletRequest.getParameterNames();

		while (parameterNames.hasMoreElements()) {
			String key = (String) parameterNames.nextElement();
			String value = httpServletRequest.getParameter(key);
			resultMap.put(key, value);

		}
		return resultMap;
	}

	@Override
	public void logResponse(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Object body) {

		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("RESPONSE");
		stringBuilder.append("method=[").append(httpServletRequest.getMethod()).append("]");
		stringBuilder.append("path=[").append(httpServletRequest.getRequestURI()).append("]");
		stringBuilder.append("ResponseHeaders=[").append(buildHeadersMap(httpServletResponse)).append("]");
		log.info(stringBuilder.toString());
	}

	private Map<String, String> buildHeadersMap(HttpServletResponse httpServletResponse) {

		Map<String, String> map = new HashMap<>();
		Collection<String> headreNames = httpServletResponse.getHeaderNames();

		for (String header : headreNames) {
			map.put(header, httpServletResponse.getHeader(header));
		}
		return map;
	}
}
