package com.destroyers.spaceallocation.service;

import com.destroyers.spaceallocation.dao.DepartmentDao;
import com.destroyers.spaceallocation.dao.OECodeDao;
import com.destroyers.spaceallocation.entities.Department;
import com.destroyers.spaceallocation.entities.OECode;
import com.destroyers.spaceallocation.model.department.DepartmentResponse;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class DepartmentServiceTest {

    @InjectMocks
    private DepartmentService departmentService;

    @Mock
    private DepartmentDao departmentDao;

    @Mock
    private OECodeDao oeCodeDao;

    @Nested
    class GetAllDepartmentsTest {

        @Test
        void shouldReturnAllDepartments() {
            OECode highLevelOECode = new OECode(1L, "MBLD1", 110, null, "HIGH");
            OECode oeCode1 = new OECode(2L, "MBLD11", 100, null, "MID");
            OECode oeCode2 = new OECode(3L, "MBLD12", 10, null, "MID");
            Department department = new Department(1L, "Private Banking");

            when(departmentDao.findAll()).thenReturn(List.of(department));
            when(oeCodeDao.findByDepartmentId(1L)).thenReturn(List.of(oeCode1, oeCode2, highLevelOECode));

            List<DepartmentResponse> departments = departmentService.getAll();

            assertThat(departments).hasSize(1);
            assertThat(departments).contains(new DepartmentResponse(
                    "Private Banking", highLevelOECode.getName(), 110
            ));
        }
    }
}