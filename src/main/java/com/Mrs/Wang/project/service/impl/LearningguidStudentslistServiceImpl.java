package com.Mrs.Wang.project.service.impl;

import com.Mrs.Wang.project.dao.LearningguidStudentslistMapper;
import com.Mrs.Wang.project.model.LearningguidStudentslist;
import com.Mrs.Wang.project.service.LearningguidStudentslistService;
import com.Mrs.Wang.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by Dragonsking309 on 2018/11/19.
 */
@Service
@Transactional
public class LearningguidStudentslistServiceImpl extends AbstractService<LearningguidStudentslist> implements LearningguidStudentslistService {
    @Resource
    private LearningguidStudentslistMapper tLearningguidStudentslistMapper;

}