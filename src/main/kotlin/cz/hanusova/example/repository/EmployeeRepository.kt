package cz.hanusova.example.repository

import cz.hanusova.example.model.Employee
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface EmployeeRepository: MongoRepository<Employee, ObjectId> {
}