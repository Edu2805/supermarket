package br.com.amorim.supermarket.service.department;

import br.com.amorim.supermarket.model.department.Department;
import br.com.amorim.supermarket.service.common.genericcrudservice.CrudServiceCommon;

import java.util.UUID;

public interface DepartmentCrudService extends CrudServiceCommon<Department, UUID>  {
}
