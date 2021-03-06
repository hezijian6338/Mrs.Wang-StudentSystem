package com.student.info.service;

import com.student.info.model.CourseInfoStudent;
import com.student.info.core.Service;

import java.util.List;


/**
 * Created by Dragonsking309 on 2018/11/19.
 */
public interface CourseInfoStudentService extends Service<CourseInfoStudent> {

    /**
     * TODO: 根据学生学号和选课课程的数组来做判断增加还是删除
     * @author hezijian6338
     * @date 2019/3/21 11:14
     * @param studentno
     * @param courseids
     * @return void
     * @throws
     **/

    public void studentToCourseIds(String studentno, List<String> courseids);

    /**
     * TODO: 根据学生学号返回完整已选课程的数据类型的数组
     * @author hezijian6338
     * @date 2019/3/21 11:14
     * @param studentno
     * @return java.util.List<CourseInfoStudent>
     * @throws
     **/

    public List<CourseInfoStudent> findByStudentno(String studentno);

    /**
     * TODO: 根据学生学号返回已选课程的id 的数组
     * @author hezijian6338
     * @date 2019/3/21 11:16
     * @param studentno
     * @return java.util.List<java.lang.String>
     * @throws
     **/

    public List<String> findIdsByStudentno(String studentno);

    /**
     * TODO: 根据学生学号返回已选课程的id 的数组
     * @author hezijian6338
     * @date 2019/3/21 11:16
     * @param courseNo, teacherNo
     * @return java.util.List<java.lang.String>
     * @throws
     **/
    public List<CourseInfoStudent> findByTeacherno(String courseNo, String teacherNo);
}
