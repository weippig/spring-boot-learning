package net.javaguides.springboot.controller;

import net.javaguides.springboot.bean.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("students")
public class StudentController {

    // http://localhost:8080/student
    @GetMapping("student")
    public ResponseEntity<Student> getStudent(){
        Student student =  new Student(
                1,
                "helen",
                "huang"
        );

        // return new ResponseEntity<>(student, HttpStatus.OK);
        // return ResponseEntity.ok(student);
        return ResponseEntity.ok()
                .header("custom-header", "helen")
                .body(student);
    }

    // http://localhost:8080/students
    @GetMapping()
    public ResponseEntity<List<Student>> getStudents(){
        List<Student> students = new ArrayList<>();

        students.add(new Student(1, "helen", "huang"));
        students.add(new Student(2, "kelly", "chu"));
        students.add(new Student(3, "amy", "chen"));
        students.add(new Student(4, "San", "Diego"));

        return ResponseEntity.ok(students);
    }


    // Sprint boot rest api with path variable
    // {id}-  uri template variable
    // 用 @PathVariable 把 argument 和 uri template variable bind 在一起
    // http://localhost:8080/students/1/he/ee
    @GetMapping("{id}/{first-name}/{last-name}")
    public ResponseEntity<Student> studentPathVariable(@PathVariable("id") int studentId,
                                                       @PathVariable("first-name") String firstName,
                                                       @PathVariable("last-name") String lastName){
        Student student = new Student(studentId, firstName, lastName);
        return ResponseEntity.ok(student);
    }

    // Spring boot rest api with request param
    // http://localhost:8080/students/query?id=1&firstName=helen&lastName=huang
    // @GetMapping and @RequestParam
    @GetMapping("query")
    public ResponseEntity<Student> studentRequestVariable(@RequestParam int id,
                                          @RequestParam String firstName,
                                          @RequestParam String lastName){
        Student student = new Student(id, firstName, lastName);
        return ResponseEntity.ok(student);
    };

    // Spring boot rest api that handles http post request - creating new resource
    // @PostMapping and @RequestBody
    // http://localhost:8080/students/create
    // request body json field 要和 student object field 相同
    @PostMapping("create")
    @ResponseStatus(HttpStatus.CREATED) // 成功會回傳 201
    public ResponseEntity<Student> createStudent(@RequestBody Student student){
        System.out.println(student.getId());
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());

        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    // spring boot rest api that handles http put request - updating existing resource
    // http://localhost:8080/student/{id}/update
    @PutMapping("{id}/update")
    public ResponseEntity<Student> updateStudent(@RequestBody  Student student,@PathVariable("id") int studentId){
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return ResponseEntity.ok(student);
    }

    // spring boot rest api that handles http delete request - deleting existing resource
    // http://localhost:8080/student/{id}/delete
    @DeleteMapping("{id}/delete")
    public ResponseEntity<String> deleteStudent(@PathVariable("id") int studentId){
        System.out.println(studentId);
        return ResponseEntity.ok("Student deleted successfully");
    }
}
