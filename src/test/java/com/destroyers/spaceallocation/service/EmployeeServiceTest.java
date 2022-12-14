package com.destroyers.spaceallocation.service;

import com.destroyers.spaceallocation.dao.EmployeeDao;
import com.destroyers.spaceallocation.dao.OECodeDao;
import com.destroyers.spaceallocation.entities.Building;
import com.destroyers.spaceallocation.entities.Department;
import com.destroyers.spaceallocation.entities.Employee;
import com.destroyers.spaceallocation.entities.OECode;
import com.destroyers.spaceallocation.model.employee.EmployeeResponse;
import com.destroyers.spaceallocation.model.oecode.OECodeResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

import static com.destroyers.spaceallocation.model.employee.EmployeeRole.ADMIN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @InjectMocks
    private EmployeeService employeeService;

    @Mock
    private EmployeeDao employeeDao;

    @Mock
    private OECodeDao oeCodeDao;

    @BeforeEach
    void setUp() {
        when(oeCodeDao.findAllByParentOECodeId(any())).thenReturn(List.of());
    }

    @Nested
    class GetAllEmployeesTest {

        @Test
        void shouldReturnAllEmployees() {
            Building building = new Building(1L, "EON2");
            Department department = new Department(1L, "Private Banking", building);
            OECode oeCode = new OECode(1L, "MBLD1", 100, null, "MID", null);
            Employee employee = new Employee(1L, "M12345", "User-1", ADMIN, department, oeCode);
            when(employeeDao.findAll(any(Specification.class))).thenReturn(List.of(employee));

            List<EmployeeResponse> employees = employeeService.getEmployees(Optional.of(1L));

            assertThat(employees).hasSize(1);
            assertThat(employees).contains(new EmployeeResponse(1L, "M12345", "User-1", ADMIN,
                    new OECodeResponse(1L, "MBLD1", 100), 1L, "Private Banking", List.of()));
        }
    }

    @Nested
    class GetEmployeeByMpidTest {

        @Test
        void shouldReturnEmployeeByMpid() {
            Building building = new Building(1L, "EON2");
            Department department = new Department(1L, "Private Banking", building);
            OECode oeCode = new OECode(1L, "MBLD1", 100, null, "MID", null);
            Employee employee = new Employee(1L, "M12345", "User-1", ADMIN, department, oeCode);
            when(employeeDao.findByMpid(employee.getMpid())).thenReturn(Optional.of(employee));

            EmployeeResponse employeeResponse = employeeService.getByPid(employee.getMpid());

            assertThat(employeeResponse).isEqualTo(new EmployeeResponse(1L, "M12345", "User-1", ADMIN,
                    new OECodeResponse(1L, "MBLD1", 100), 1L, "Private Banking", List.of()));
        }
    }

}