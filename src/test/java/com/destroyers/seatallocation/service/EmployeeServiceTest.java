package com.destroyers.seatallocation.service;

import com.destroyers.seatallocation.dao.EmployeeDao;
import com.destroyers.seatallocation.entities.Employee;
import com.destroyers.seatallocation.model.employee.EmployeeResponse;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.destroyers.seatallocation.model.employee.EmployeeRole.ADMIN;
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
            Employee employee = new Employee(1L, "User-1", ADMIN);
            when(employeeDao.findAll()).thenReturn(List.of(employee));

            List<EmployeeResponse> employees = employeeService.getAll();

            assertThat(employees).hasSize(1);
            assertThat(employees).contains(new EmployeeResponse(1L, "User-1", ADMIN));
        }
    }
}