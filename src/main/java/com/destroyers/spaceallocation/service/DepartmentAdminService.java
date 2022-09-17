package com.destroyers.spaceallocation.service;

import com.destroyers.spaceallocation.dao.DepartmentAdminDao;
import com.destroyers.spaceallocation.entities.DepartmentAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentAdminService {

    @Autowired
    private DepartmentAdminDao departmentAdminDao;

    public List<DepartmentAdmin> getByEmployeeId(Long pid) {
        return departmentAdminDao.findByEmployeeId(pid);
    }
}
