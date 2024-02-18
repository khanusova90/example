package cz.hanusova.example.controller

import cz.hanusova.example.dto.EmployeeDto
import cz.hanusova.example.service.EmployeeService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
class EmployeeController(
    val employeeService: EmployeeService
) {

    @PostMapping("/employee")
    fun createEmployee(@RequestBody employee: EmployeeDto) = employeeService.createEmployee(employee)

    @GetMapping("/employee")
    fun getEmployeeBySurname(@RequestParam surname: String) = employeeService.getEmployeeBySurname(surname)
}