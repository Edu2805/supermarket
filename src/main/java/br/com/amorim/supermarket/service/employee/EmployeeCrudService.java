package br.com.amorim.supermarket.service.employee;

import br.com.amorim.supermarket.model.employee.Employee;
import br.com.amorim.supermarket.service.common.genericcrudservice.CrudServiceCommon;

import java.util.UUID;

public interface EmployeeCrudService extends CrudServiceCommon<Employee, UUID> {
}
