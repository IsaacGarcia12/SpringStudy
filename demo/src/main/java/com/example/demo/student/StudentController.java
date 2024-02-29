package com.example.demo.student;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//This is the API layer

@RestController
@RequestMapping(path="api/v1/student")
public class StudentController {
	
	//This makes reference to the Student business layer
	private final StudentService studentService;
	
	@Autowired
	public StudentController(StudentService studentService) {
		super();
		this.studentService = studentService;
	}

	@GetMapping
	public List<Student> getStudents() {
		return studentService.getStudents();
	}
	
	@PostMapping
	public void registerNewStudent(@RequestBody Student student) {
		studentService.addNewStudent(student);
	}
	
	@DeleteMapping(path="{studentId}")
	public void deleteStudent(@PathVariable ("studentId") Long id) {
		studentService.deleteStudent(id);
	}
	
	@PutMapping(path="{studentId}")
	public void updateStudent(@PathVariable("studentId") Long studentId, 
							  @PathVariable(required=false)String name, @PathVariable(required=false) String email){
		studentService.updateStudent(studentId, email, name);
	}	
}