package com.destroyers.seatallocation.service;

import com.destroyers.seatallocation.dao.DepartmentDao;
import com.destroyers.seatallocation.dao.OECodeDao;
import com.destroyers.seatallocation.entities.Department;
import com.destroyers.seatallocation.entities.OECode;
import com.destroyers.seatallocation.model.department.DepartmentResponse;
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
            OECode oeCode1 = new OECode(1L, "MBLD12", 100, null);
            OECode oeCode2 = new OECode(2L, "MBLD13", 10, null);
            Department department = new Department(1L, "Private Banking");

            when(departmentDao.findAll()).thenReturn(List.of(department));
            when(oeCodeDao.findByDepartmentId(1L)).thenReturn(List.of(oeCode1, oeCode2));

            List<DepartmentResponse> departments = departmentService.getAll();

            assertThat(departments).hasSize(1);
            assertThat(departments).contains(new DepartmentResponse(
                    "Private Banking", 110
            ));
        }
    }
}