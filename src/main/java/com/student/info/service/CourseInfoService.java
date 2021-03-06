package com.student.info.service;
import com.student.info.model.CourseInfo;
import com.student.info.core.Service;

import java.util.List;


/**
 * Created by Dragonsking309 on 2018/11/19.
 */
public interface CourseInfoService extends Service<CourseInfo> {

    List<CourseInfo> findSelectedCoursesByTeacherNo(String teacherNo);
}
