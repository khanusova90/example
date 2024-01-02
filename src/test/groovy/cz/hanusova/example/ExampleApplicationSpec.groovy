package cz.hanusova.example

import cz.hanusova.example.repository.EmployeeRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class ExampleApplicationSpec extends Specification {

    @Autowired
    private EmployeeRepository employeeRepository

    def 'When context is loaded, then repositories are available' () {
        expect:
        employeeRepository == null
    }

}