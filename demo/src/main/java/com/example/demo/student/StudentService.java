package com.example.demo.student;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

//This is our business logic
@Service
public class StudentService {
	
	private final StudentRepository studentRepository;
	
	public StudentService(StudentRepository studentRepository) {
		super();
		this.studentRepository = studentRepository;
	}
	
	//Get method
	public List<Student> getStudents() {
		return studentRepository.findAll();
	}
	
	//Post Method
	public void addNewStudent(Student student) {
		Optional <Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
		if(studentOptional.isPresent()) {
			throw new IllegalStateException("email already taken");
		}
		studentRepository.save(student);
		System.out.println(student);
	}
	
	//Delete method
	public void deleteStudent(Long studentId) {
		boolean exists = studentRepository.existsById(studentId);
		if(!exists) {
			throw new IllegalStateException("Student with id: "+ studentId+" doesn't exists");
		}
		studentRepository.deleteById(studentId);
	}
	
	//Put (update) method
	@Transactional
	public void updateStudent(Long studentId, String email, String name) {
		Student student = studentRepository.findById(studentId).orElseThrow(
				() -> new IllegalStateException("Student with Id:" +studentId+ "doesn't exists"));
				
				if(name != null && name.length()>0 && !Objects.equals(student.getName(), name)) {
					student.setName(name);
				}
				
				if(email != null && email.length()>0 && !Objects.equals(student.getEmail(), email)) {
					Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
					if(studentOptional.isPresent()) {
						throw new IllegalStateException("email taken");
					}
				}
				student.setEmail(email);
	}
}
