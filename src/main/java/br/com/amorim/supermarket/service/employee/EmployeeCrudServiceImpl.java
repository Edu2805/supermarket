package br.com.amorim.supermarket.service.employee;

import br.com.amorim.supermarket.common.enums.MessagesKeyType;
import br.com.amorim.supermarket.common.exception.notfound.NotFoundException;
import br.com.amorim.supermarket.common.verifypagesize.VerifyPageSize;
import br.com.amorim.supermarket.model.employee.Employee;
import br.com.amorim.supermarket.repository.employee.EmployeeRepository;
import br.com.amorim.supermarket.service.employee.admitemployee.AdmitEmployee;
import br.com.amorim.supermarket.service.employee.employeefullname.EmployeeFullName;
import br.com.amorim.supermarket.service.employee.generateregisternumber.GenerateRegisterNumberEmployee;
import br.com.amorim.supermarket.service.employee.verifyemployeeperson.VerifyEmployeePerson;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

import static br.com.amorim.supermarket.configuration.internacionalizationmessages.ResourcesBundleMessages.getString;

@AllArgsConstructor

@Service
public class EmployeeCrudServiceImpl implements EmployeeCrudService {

    private EmployeeRepository employeeRepository;
    private GenerateRegisterNumberEmployee generateRegisterNumberEmployee;
    private VerifyEmployeePerson verifyEmployeePerson;
    private VerifyPageSize verifyPageSize;
    private EmployeeFullName employeeFullName;
    private AdmitEmployee admitEmployee;
    private static final int DECREASE_PAGE_SIZE = 1;
    private static final int ZERO_PAGE_SIZE = 0;

    @Override
    public Page<Employee> getAll (int page, int size) {
        if (page > ZERO_PAGE_SIZE) {
            page -= DECREASE_PAGE_SIZE;
        }
        verifyPageSize.verifyPageSizeForGetAll(page, size);
        Pageable pageableRequest = PageRequest.of(page, size);
        return employeeRepository.findAll(pageableRequest);
    }

    @Override
    public Employee findById (UUID id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> {
                    throw new NotFoundException(
                            getString(MessagesKeyType.EMPLOYEE_NOT_FOUND.message));
                });
    }

    @Transactional
    @Override
    public Employee save (Employee employee) {
        verifyEmployeePerson.verifyEmployeePerson(employee);
        setFields(employee);
        return employeeRepository.save(employee);
    }

    private void setFields(Employee employee) {
        var registerNumber = generateRegisterNumberEmployee.generate(employee);
        var fillFullName = employeeFullName.fillEmployeeFullName(employee);

        employee.setFullName(fillFullName);
        employee.setRegisterNumber(registerNumber);
        admitEmployee.isEmployee(employee);
    }

    @Transactional
    @Override
    public void update (Employee employee, UUID id) {
        employeeRepository.findById(id)
                .map(existingEmployee -> {
                   employee.setId(existingEmployee.getId());
                   employee.setRegisterNumber(existingEmployee.getRegisterNumber());
                   employee.setFullName(existingEmployee.getFullName());
                   employee.setPerson(existingEmployee.getPerson());
                   employeeRepository.save(employee);
                   return existingEmployee;
                }).orElseThrow(() ->
                        new NotFoundException(
                                getString(MessagesKeyType.EMPLOYEE_NOT_FOUND.message)));
    }

    @Transactional
    @Override
    public void delete (UUID id) {
        employeeRepository.findById(id)
                .map(existingEmployee -> {
                    employeeRepository.delete(existingEmployee);
                    return existingEmployee;
                }).orElseThrow(() ->
                        new NotFoundException(
                                getString(MessagesKeyType.EMPLOYEE_NOT_FOUND.message)));
    }

}
