package cz.hanusova.example.integration

import cz.hanusova.example.model.Employee
import cz.hanusova.example.repository.EmployeeRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.MongoDBContainer
import org.testcontainers.spock.Testcontainers
import spock.lang.Shared
import spock.lang.Specification

import java.time.LocalDate

@Testcontainers
@SpringBootTest
class EmployeeRepositoryIntSpec extends Specification {

    @Shared
    static def mongo = new MongoDBContainer("mongo:latest")

    @Autowired
    private EmployeeRepository employeeRepository

    @DynamicPropertySource
    static void mongoProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", () -> mongo.replicaSetUrl)
    }

    def setupSpec() {
        mongo.start()
        println "MongoDB container started at ${mongo.replicaSetUrl}"
    }

    def cleanupSpec() {
        mongo.stop()
    }

    def 'Saved entity should be available in repository'() {
        given: 'Employee saved in DB'
        def employee = new Employee(name, surname, birthdate)

        when: 'Employee is retrieved from repository'
        employeeRepository.save(employee)

        then: 'Saved values are returned'
        def result = employeeRepository.findFirstBySurname(surname)
        result.name == name
        result.surname == surname
        result.birthDate == birthdate

        where:
        name   | surname | birthdate
        'John' | 'Doe'   | LocalDate.of(1990, 1, 1)
        'Jane' | 'Doe'   | LocalDate.of(1991, 2, 2)
    }

}