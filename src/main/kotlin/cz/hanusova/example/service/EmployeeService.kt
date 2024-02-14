package cz.hanusova.example.service

import cz.hanusova.example.dto.EmployeeDto
import cz.hanusova.example.model.Employee
import cz.hanusova.example.repository.EmployeeRepository
import org.springframework.stereotype.Service
import java.time.LocalDate


@Service
class EmployeeService(
    val employeeRepository: EmployeeRepository
) {
    fun createEmployee(employeeDto: EmployeeDto) {
        val birthDate = parseBirthDate(employeeDto.birthDate)
        Employee(
            name = employeeDto.name,
            surname = employeeDto.surname,
            birthDate = birthDate
        ).let {
            employeeRepository.save(it)
        }
    }

    private fun parseBirthDate(birthDate: String): LocalDate {
        try {
            return LocalDate.parse(birthDate)
        } catch (e: Exception) {
            throw IllegalArgumentException("Birth date is not in correct format")
        }
    }

    fun getEmployeeBySurname(surname: String): EmployeeDto = EmployeeDto(
        employeeRepository.findFirstBySurname(surname)
            ?: throw IllegalArgumentException("Employee with surname $surname does not exist")
    )
}