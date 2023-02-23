package DensagouService.userService.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import DensagouService.userService.enums.EditeMode;
import DensagouService.userService.enums.Gender;
import DensagouService.userService.enums.MaritalStatus;
import DensagouService.userService.enums.UserRole;
import DensagouService.userService.exception.EntityType;
import DensagouService.userService.exception.ExceptionType;
import DensagouService.userService.exception.KaladewnManagementException;
import DensagouService.userService.response.CreateStudentFormData;
import DensagouService.userService.response.EditStudentFormData;
import DensagouService.userService.service.StudentService;
import DensagouService.userService.validation.CreateUserValidationGroupSequence;
import DensagouService.userService.validation.EditUserValidationGroupSequence;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/students")
@Slf4j
public class StudentController {

	private final StudentService studentService;

	private String genders = "genders";
	private String maritalStatus = "maritalStatus";
	private String editStudent = "users/student/edit";
	private String list = "users/student/list";
	private String possibleRoles = "possibleRoles";
	private String editeMode = "editeMode";

	@Autowired
	public StudentController(StudentService studentService) {
		super();
		this.studentService = studentService;
	}

	/**
	 * @param model
	 * @param pageable
	 * @return
	 */
	@GetMapping("/all")
	public String getAllStudent(Model model, @SortDefault.SortDefaults({ @SortDefault("userName.lastName"),
			@SortDefault("userName.firstName") }) Pageable pageable) {

		model.addAttribute("students", studentService.getUsers(pageable));
		log.debug("Request to get all Student", studentService.getUsers(pageable));
		return list;
	}

	// tag::create-get[]
	@GetMapping("/create")
	public ModelAndView addNewStudent() {

		ModelAndView modelAndView = new ModelAndView(editStudent);

		modelAndView.addObject("student", new CreateStudentFormData());
		modelAndView.addObject(possibleRoles, possibleRoles());
		modelAndView.addObject(genders, genders());
		modelAndView.addObject(maritalStatus, maritalStatus());
		modelAndView.addObject(editeMode, EditeMode.CREATE);
		return modelAndView;
	}
	// end::create-get[]

	// tag::create-post[]
	/**
	 * @param formData
	 * @param bindingResult
	 * @param modelAndView
	 * @return
	 */
	@PostMapping("/create")
	public ModelAndView createStudent(
			@Validated(CreateUserValidationGroupSequence.class) @ModelAttribute("student") CreateStudentFormData formData,
			BindingResult bindingResult, ModelAndView modelAndView) {

		modelAndView = new ModelAndView(editStudent);
		if (bindingResult.hasErrors()) {
			modelAndView.addObject(possibleRoles, possibleRoles());
			modelAndView.addObject(genders, genders());
			modelAndView.addObject(maritalStatus, maritalStatus());
			return modelAndView;
		}
		log.debug("Request to create  student : {}", studentService.createStudent(formData.toStudentParameters()));
		studentService.createStudent(formData.toStudentParameters());

		return new ModelAndView("redirect:" + list);

	}
	// end::create-post[]

	// tag::edit-get[]
	/**
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping("/edit/{id}")
	public String editStudentForm(@PathVariable("id") Long id, Model model) {

		var student = studentService.getUserById(id).orElseThrow(() -> new KaladewnManagementException()
				.throwException(EntityType.STUDENT, ExceptionType.ENTITY_NOT_FOUND, String.valueOf(id)));

		model.addAttribute("student", EditStudentFormData.fromStudent(student));
		model.addAttribute(genders, genders());
		model.addAttribute(possibleRoles, possibleRoles());
		model.addAttribute(maritalStatus, maritalStatus());
		model.addAttribute("editeMode", EditeMode.UPDATE);
		return editStudent;

	}
	// end::edit-get[]

	// tag::edit-post[]
	/**
	 * @param id
	 * @param formData
	 * @param bindingResult
	 * @param model
	 * @return
	 */
	@PostMapping("/edit/{id}")
	public String doEditStudent(@PathVariable("id") Long id,
			@Validated(EditUserValidationGroupSequence.class) @ModelAttribute("student") EditStudentFormData formData,
			BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
			model.addAttribute(genders, genders());

			model.addAttribute(maritalStatus, maritalStatus());
			model.addAttribute(possibleRoles, possibleRoles());
			model.addAttribute("editeMode", EditeMode.UPDATE);
			return editStudent;
		}

		log.debug("Request to edit School : {}", id);

		studentService.editUser(id, formData.toStudentParameters());

		return "redirect:" + "users/student/list";

	}
	// end::edit-post[]

	// tag::delete-post[]
	/**
	 * @param id
	 * @param redirectAttributes
	 * @return
	 */
	@PostMapping("/delete/{userId}")
	public String doDeleteStudentById(@PathVariable("userId") Long id, RedirectAttributes redirectAttributes) {

		var student = studentService.getUserById(id);

		if (student.isPresent()) {
			studentService.deleteUserById(id);
			redirectAttributes.addFlashAttribute("deleteStudent:{}", (student.get()).getUserName().getFullName());
			log.debug("Request to delete Student : {}", id);
			return "redirect:/users/student/list";
		}
		return "redirect:" + "users/student/list";

	}
	// end::delete-post[]

	@GetMapping("/ex")
	public String ThrowException() {

		throw new RuntimeException("This is a fake exception testing.");
	}

	// tag::model-attributes[]
	@ModelAttribute("genders")
	public Set<Gender> genders() {
		return new HashSet<>(List.of(Gender.FEMALE, Gender.MALE));

	}

	@ModelAttribute("maritalStatus")
	public Set<MaritalStatus> maritalStatus() {
		return new HashSet<>(List.of(MaritalStatus.DIVORCED, MaritalStatus.MARRIED, MaritalStatus.SINGLE));
	}

	@ModelAttribute("possibleRoles")
	public List<UserRole> possibleRoles() {
		return List.of(UserRole.STUDENT);
	}
	// end::model-attributes[]
}
