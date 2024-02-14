package cz.hanusova.example.dto

import cz.hanusova.example.model.Employee

data class EmployeeDto(
    val name: String,
    val surname: String,
    val birthDate: String
) {
    constructor(employee: Employee) : this(
        name = employee.name!!,
        surname = employee.surname!!,
        birthDate = employee.birthDate.toString()
    )
}
