package DensagouService.userService.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import DensagouService.userService.domain.PhoneNumber;
import DensagouService.userService.util.PhoneNumberPropertyEditor;
import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalControllerAdvice {

	@ResponseStatus(HttpStatus.CONFLICT)
	@ExceptionHandler({ DataIntegrityViolationException.class, ObjectOptimisticLockingFailureException.class })
	public ModelAndView handleConflict(HttpServletRequest request, Exception e) {

		ModelAndView result = new ModelAndView("error/409");

		result.addObject("url", request.getRequestURL());

		return result;
	}

	@Value("${application.version}")
	private String version;

	@ModelAttribute("version")
	public String getVersion() {
		return version;
	}

	@GetMapping("/error/500")
	public ModelAndView error500View() {
		ModelAndView modelAndView = new ModelAndView("error/500");
		return modelAndView;
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {

		StringTrimmerEditor editor = new StringTrimmerEditor(false);
		binder.registerCustomEditor(String.class, editor);

		binder.registerCustomEditor(PhoneNumber.class, new PhoneNumberPropertyEditor());
	}

}
