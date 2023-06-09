package controller;


import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import model.Course;
import service.BankService;

@RestController
@RequestMapping("/bank/{bankId}/courses")
public class BankController 
{
@Autowired
private BankService bankService;

@GetMapping()
public List<Course> retrieveCoursesForStudent(@PathVariable String bankId) {
    return bankService.retrieveCourses(bankId);
}

@PostMapping()
public ResponseEntity<Void> registerBankForCourse(@PathVariable String bankId,
                                                     @RequestBody Course newCourse) {

    Course course = bankService.addCourse(bankId, newCourse);

    if (course == null)
        return ResponseEntity.noContent().build();

    URI location = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(course.id())
            .toUri();

    return ResponseEntity.created(location).build();
}

@GetMapping("/{courseId}")
public Course retrieveDetailsForCourse(@PathVariable String bankId,
                                       @PathVariable String courseId) {

    return bankService.retrieveCourse(bankId, courseId);
}

}