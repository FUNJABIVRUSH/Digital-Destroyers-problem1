package com.destroyers.spaceallocation.service;

import com.destroyers.spaceallocation.dao.EmployeeDao;
import com.destroyers.spaceallocation.entities.Department;
import com.destroyers.spaceallocation.entities.Employee;
import com.destroyers.spaceallocation.model.employee.EmployeeResponse;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static com.destroyers.spaceallocation.model.employee.EmployeeRole.ADMIN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @InjectMocks
    private EmployeeService employeeService;

    @Mock
    private EmployeeDao employeeDao;

    @Nested
    class GetAllEmployeesTest {

        @Test
        void shouldReturnAllEmployees() {
            Department department = new Department(1L, "Private Banking");
            Employee employee = new Employee(1L, "M12345", "User-1", ADMIN, department);
            when(employeeDao.findAll()).thenReturn(List.of(employee));

            List<EmployeeResponse> employees = employeeService.getAll();

            assertThat(employees).hasSize(1);
            assertThat(employees).contains(new EmployeeResponse( 1L, "M12345", "User-1", ADMIN));
        }
    }

    @Nested
    class GetEmployeeByMpidTest {

        @Test
        void shouldReturnEmployeeByMpid() {
            Department department = new Department(1L, "Private Banking");
            Employee employee = new Employee(1L, "M12345", "User-1", ADMIN, department);
            when(employeeDao.findByMpid(employee.getMpid())).thenReturn(Optional.of(employee));

            EmployeeResponse employeeResponse = employeeService.getByPid(employee.getMpid());

            assertThat(employeeResponse).isEqualTo(new EmployeeResponse(1L, "M12345", "User-1", ADMIN));
        }
    }

}