package cz.hanusova.example.service

import cz.hanusova.example.dto.EmployeeDto
import cz.hanusova.example.model.Employee
import cz.hanusova.example.repository.EmployeeRepository
import spock.lang.Specification

import java.time.LocalDate


class EmployeeServiceSpec extends Specification {

    def employeeRepository = Mock(EmployeeRepository)

    EmployeeRepository employeeRepositoryMock = Mock()


    def employeeService = new EmployeeService(employeeRepository)

    def 'Employee is saved'() {
        given: 'New employee'
        def name = 'John'
        def surname = 'Doe'
        def birthDate = '2000-12-12'
        def employee = new EmployeeDto(name, surname, birthDate)

        when: 'Employee is saved'
        employeeService.createEmployee(employee)

        then:
        1 * employeeRepository.save({
            it.name == name
            it.surname == surname
            it.birthDate == LocalDate.parse(birthDate)
        })
    }

    def "Should throw an exception when trying to save employee with invalid birth date #birthDate"() {
        given: 'Employee with invalid birth date'
        def employee = new EmployeeDto('John', 'Doe', '2000-13-12')

        when: 'Employee is saved'
        employeeService.createEmployee(employee)

        then: 'An exception is thrown'
        def t = thrown(IllegalArgumentException)
        t.message == 'Birth date is not in correct format'

        where:
        birthDate << ['20202020-12-12', 'abc']
    }

    def 'Return employee from DB' () {
        given: 'Employee in DB'
        def name = 'John'
        def surname = 'Doe'
        def birthDate = LocalDate.of(2000, 12, 12)
        def employee = new Employee(name: name, surname: surname, birthDate: birthDate)
        employeeRepository.findFirstBySurname(surname) >> employee

        when: 'Employee is searched'
        def result = employeeService.getEmployeeBySurname(surname)

        then: 'Employee is returned'
        result.name == name
        result.surname == surname
        result.birthDate == birthDate.toString()
    }

    def 'Should throw exception when trying to find non-existent employee' () {
        given: 'Empty employee DB'
        def surname = 'Doe'

        employeeRepository.findFirstBySurname(_) >> new Employee()
        employeeRepository.findFirstBySurname(_) >> null

        employeeRepository.findFirstBySurname(_) >>> [new Employee(), null]

        when: 'Employee is searched'
        employeeService.getEmployeeBySurname(surname)

        then: 'An exception is thrown'
        def t = thrown(IllegalArgumentException)
        t.message == "Employee with surname $surname does not exist"

    }

}