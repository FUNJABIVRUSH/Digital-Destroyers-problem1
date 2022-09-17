package com.destroyers.spaceallocation.service;

import com.destroyers.spaceallocation.dao.OECodeDao;
import com.destroyers.spaceallocation.entities.*;
import com.destroyers.spaceallocation.model.department.DepartmentResponse;
import com.destroyers.spaceallocation.model.employee.EmployeeResponse;
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
class DepartmentServiceTest {

    @InjectMocks
    private DepartmentService departmentService;

    @Mock
    private OECodeDao oeCodeDao;

    @Mock
    private DepartmentAdminService departmentAdminService;

    @Mock
    private EmployeeService employeeService;

    @Nested
    class GetAllDepartmentsTest {

        @Test
        void shouldReturnAllDepartments() {
            String pid = "M12345";
            Building building = new Building(1L, "EON2");
            OECode highLevelOECode = new OECode(1L, "MBLD1", 110, null, "HIGH", null);
            OECode oeCode1 = new OECode(2L, "MBLD11", 100, null, "MID", null);
            OECode oeCode2 = new OECode(3L, "MBLD12", 10, null, "MID", null);
            Department department = new Department(1L, "Private Banking",building);
            Employee employee = new Employee(1L, pid, "User-1", ADMIN, department, oeCode1);

            DepartmentAdmin departmentAdmin = new DepartmentAdmin(1L, employee, department);

            when(employeeService.getByPid(pid)).thenReturn(EmployeeResponse.from(employee, List.of()));
            when(oeCodeDao.findByDepartmentId(1L)).thenReturn(List.of(oeCode1, oeCode2, highLevelOECode));
            when(departmentAdminService.getByEmployeeId(1L)).thenReturn(List.of(departmentAdmin));

            List<DepartmentResponse> departments = departmentService.getAll(pid);

            assertThat(departments).hasSize(1);
            assertThat(departments).contains(new DepartmentResponse(
                    building.getId(), building.getName(), department.getId(), "Private Banking", highLevelOECode.getId(), highLevelOECode.getName(), 110
            ));
        }
    }
}