package com.destroyers.spaceallocation.service;

import com.destroyers.spaceallocation.dao.DepartmentAdminDao;
import com.destroyers.spaceallocation.entities.Building;
import com.destroyers.spaceallocation.entities.Department;
import com.destroyers.spaceallocation.entities.DepartmentAdmin;
import com.destroyers.spaceallocation.entities.Employee;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.destroyers.spaceallocation.model.employee.EmployeeRole.ADMIN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class DepartmentAdminServiceTest {

    @InjectMocks
    private DepartmentAdminService departmentAdminService;

    @Mock
    private DepartmentAdminDao departmentAdminDao;


    @Nested
    class GetByEmployeeId {

        @Test
        void shouldReturnDepartmentAdminsByEmployeeId() {
            Long employeeId = 1L;
            Building building = new Building(1L, "EON2");

            Department department = new Department(1L, "Private Banking", building);
            Employee employee = new Employee(1L, "M12345", "User-1", ADMIN, department, null);

            DepartmentAdmin departmentAdmin = new DepartmentAdmin(1L, employee, department);
            when(departmentAdminDao.findByEmployeeId(employeeId)).thenReturn(List.of(departmentAdmin));

            List<DepartmentAdmin> departmentAdmins = departmentAdminService.getByEmployeeId(employeeId);

            assertThat(departmentAdmins).isEqualTo(List.of(departmentAdmin));
        }
    }

}