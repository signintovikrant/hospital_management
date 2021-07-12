package com.techgeeks.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.techgeeks.dao.DoctorService;
import com.techgeeks.dao.PatientService;
import com.techgeeks.dao.UserService;
import com.techgeeks.model.Doctor;
import com.techgeeks.model.Patient;
import com.techgeeks.model.User;
import com.techgeeks.repository.DoctorRepository;
import com.techgeeks.repository.PatientRepository;

@Controller
@CrossOrigin(origins="http://localhost:4200")
public class LoginController {

	@Autowired
	PatientService patientService;

	@Autowired
	PatientRepository patientRepository;

	@Autowired
	UserService userService;

	@Autowired
	Patient patient;

	@Autowired
	User user;

	@Autowired
	DoctorService doctorService;

	@Autowired
	DoctorRepository doctorRepository;

	@RequestMapping("/")
	public String welcome() {
		return "welcome";
	}

	@RequestMapping("/login")
	public String login(ModelMap modelMap, Model model) {
		// User user = new User();
		Patient patient = new Patient();
		modelMap.put("patient", patient);
		model.addAttribute("user", user);
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String testlogin(@ModelAttribute("user") User user, ModelMap modelMap, HttpSession session) {
//		System.out.println(user);
		User fromDB = userService.getByEmail(user.getEmail());
		if (fromDB.getPassword().equals(user.getPassword())) {
			if (fromDB.getRole().equals("patient")) {
				session.setAttribute("pId", patientRepository.findByEmail(fromDB.getEmail()).getId());
//				modelMap.addAttribute("pId", fromDB.getId());
//				modelMap.addAttribute("pEmail", fromDB.getEmail());
				
				session.setAttribute("pEmail", patientRepository.findByEmail(fromDB.getEmail()).getEmail());
				
				return "patientHome";
			}  else if (fromDB.getRole().equals("admin")) {
//				modelMap.addAttribute("aId", fromDB.getId());
//				modelMap.addAttribute("aEmail", fromDB.getEmail());
				
				session.setAttribute("adminId", fromDB.getId());
				session.setAttribute("adminEmail", fromDB.getEmail());
				
//				System.out.println(fromDB.getId());
//
//				System.out.println(fromDB.getEmail());
				
				return "adminHome";
				
			} else {

				Doctor doc = doctorRepository.findByDocEmail(fromDB.getEmail());
				session.setAttribute("dId", doc.getDocId());
				session.setAttribute("dEmail", fromDB.getEmail());
				session.setAttribute("doc", doctorRepository.findByDocEmail(fromDB.getEmail()));
				return "doctorHome";
			}
		} else {
			modelMap.addAttribute("msg", "Invalid username or password .Please try again.");
		}

		return "login";

	}

//	@RequestMapping("/register")
//	public String register(ModelMap model) {
//		Patient patient = new Patient();
//		model.put("patient", patient);
//		return "register";
//	}
	
	@RequestMapping("/register")
	public String register(ModelMap model) {
		Patient patient = new Patient();
		model.put("patient", patient);
		return "register";
	}

	@RequestMapping(value = "/register", method=RequestMethod.POST)
	public String savePatient(@ModelAttribute("patient") Patient patient, User user) {
		patientService.addPatient(patient);
//		User user = new User();
//		user.setEmail(patient.getEmail());
//		user.setPassword(patient.getPassword());
//		user.setRole(patient.getRole());
//		userService.saveUser(user);
		return "welcome";
	}
	
//	@RequestMapping(value = "/register", method=RequestMethod.POST)
//	public String savePatient(@ModelAttribute("patient") Patient patient) {
//		patientService.addPatient(patient);
//		User user = new User();
//		user.setEmail(patient.getEmail());
//		user.setPassword(patient.getPassword());
//		user.setRole(patient.getRole());
//		userService.saveUser(user);
//		return "login";
//	}
	
	
}