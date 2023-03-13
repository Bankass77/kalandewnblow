/*
 * package DensagouService.userService.config;
 * 
 * import org.springframework.http.HttpMethod; import
 * org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.boot.web.servlet.DispatcherType; import
 * org.springframework.stereotype.Component; import
 * org.springframework.web.servlet.HandlerInterceptor; import
 * org.springframework.web.servlet.ModelAndView;
 * 
 * import DensagouService.userService.service.LogInService; import
 * jakarta.servlet.http.HttpServletRequest; import
 * jakarta.servlet.http.HttpServletResponse;
 * 
 * @Component public class LogInterceptor implements HandlerInterceptor {
 * 
 * private final LogInService logInService;
 * 
 * @Autowired public LogInterceptor(LogInService logInService) {
 * 
 * this.logInService = logInService; }
 * 
 * public boolean preHandle(HttpServletRequest request, HttpServletResponse
 * response, Object handler) {
 * 
 * if (DispatcherType.REQUEST.name().equals(request.getDispatcherType().name())
 * && request.getMethod().equals(HttpMethod.GET.name()))
 * 
 * logInService.logRequest(request, null); return true;
 * 
 * }
 * 
 * @Override public void postHandle(HttpServletRequest request,
 * HttpServletResponse response, Object o, ModelAndView modelAndView) throws
 * Exception {
 * 
 * }
 * 
 * @Override public void afterCompletion(HttpServletRequest httpServletRequest,
 * HttpServletResponse httpServletResponse, Object o, Exception e) throws
 * Exception {
 * 
 * }
 * 
 * }
 */