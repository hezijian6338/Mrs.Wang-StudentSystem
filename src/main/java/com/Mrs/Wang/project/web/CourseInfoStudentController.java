package com.Mrs.Wang.project.web;

import com.Mrs.Wang.project.core.Result;
import com.Mrs.Wang.project.core.ResultGenerator;
import com.Mrs.Wang.project.dao.CourseInfoStudentMapper;
import com.Mrs.Wang.project.model.CourseInfo;
import com.Mrs.Wang.project.model.CourseInfoStudent;
import com.Mrs.Wang.project.model.Students;
import com.Mrs.Wang.project.service.CourseInfoService;
import com.Mrs.Wang.project.service.CourseInfoStudentService;
import com.Mrs.Wang.project.service.StudentsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by Dragonsking309 on 2018/11/19.
*/
@RestController
@RequestMapping("/course/info/student")
public class CourseInfoStudentController {
    @Resource
    private CourseInfoStudentService courseInfoStudentService;

    @Resource
    private StudentsService studentsService;

    @Resource
    private CourseInfoService courseInfoService;

    @Resource
    private CourseInfoStudentMapper courseInfoStudentMapper;

    @PostMapping
    public Result add(@RequestBody CourseInfoStudent courseInfoStudent) {
        courseInfoStudentService.save(courseInfoStudent);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/{studentno}/to/{coursecode}")
    public Result _add(@PathVariable String studentno, @PathVariable String coursecode) {
        Students students = studentsService.findBy("studentno", studentno);
        CourseInfo courseInfo = courseInfoService.findBy("coursecode", coursecode);
        courseInfoStudentService.studentToCourseCode(students,courseInfo);
        return ResultGenerator.genSuccessResult();
    }


    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        courseInfoStudentService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result update(@RequestBody CourseInfoStudent courseInfoStudent) {
        courseInfoStudentService.update(courseInfoStudent);
        return ResultGenerator.genSuccessResult();
    }

//    @GetMapping("/{id}")
//    public Result detail(@PathVariable Integer id) {
//        CourseInfoStudent courseInfoStudent = courseInfoStudentService.findById(id);
//        return ResultGenerator.genSuccessResult(courseInfoStudent);
//    }

    @GetMapping("/{studentno}")
    public Result findByStudentno(@PathVariable String studentno) {
        List<CourseInfoStudent> courseInfoStudent = courseInfoStudentService.findByStudentno(studentno);
        return ResultGenerator.genSuccessResult(courseInfoStudent);
    }

    @GetMapping("/condition/aca")
    public Result conditionOfAca() {
        return ResultGenerator.genSuccessResult(courseInfoStudentMapper.conditionOfAca());
    }

    @GetMapping("/{fieldName}/{value}")
    public Result searchBy(@PathVariable String fieldName, @PathVariable String value){
        CourseInfoStudent courseInfoStudent = courseInfoStudentService.findBy(fieldName, value);
        return ResultGenerator.genSuccessResult(courseInfoStudent);
    }

    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<CourseInfoStudent> list = courseInfoStudentService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
