package com.destroyers.spaceallocation.service;

import com.destroyers.spaceallocation.dao.EmployeeDao;
import com.destroyers.spaceallocation.dao.OECodeDao;
import com.destroyers.spaceallocation.entities.Employee;
import com.destroyers.spaceallocation.entities.OECode;
import com.destroyers.spaceallocation.model.employee.EmployeeResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeDao employeeDao;
    private final OECodeDao oeCodeDao;

    private final Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);

    @Autowired
    public EmployeeService(EmployeeDao employeeDao, OECodeDao oeCodeDao) {
        this.employeeDao = employeeDao;
        this.oeCodeDao = oeCodeDao;
    }

    public List<EmployeeResponse> getEmployees(Optional<Long> oeCodeId) {
        return employeeDao.findAll(buildCustomQuery(oeCodeId))
                .stream()
                .map(this::getEmployeeResponse)
                .collect(Collectors.toList());
    }

    public EmployeeResponse getByPid(String pid) {
        return employeeDao.findByMpid(pid)
                .map(this::getEmployeeResponse)
                .orElseThrow(() -> {
                    LOGGER.error("Employee not found. pid: {}",pid);
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found for pid " + pid);
                });
    }

    private EmployeeResponse getEmployeeResponse(Employee employee) {
        OECode oeCode = employee.getOeCode();
        List<OECode> oeCodes = oeCodeDao.findAllByParentOECodeId(oeCode.getId());
        return EmployeeResponse.from(employee, oeCodes);
    }

    private Specification<Employee> buildCustomQuery(Optional<Long> oeCodeIdOptional) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            oeCodeIdOptional.ifPresent(oeCodeId -> predicates.add(criteriaBuilder.equal(root.get("oeCode"), oeCodeId)));

            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }
}
