package cz.hanusova.example

import cz.hanusova.example.repository.EmployeeRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ExampleApplicationTests {

    @Autowired
    lateinit var employeeRepository: EmployeeRepository

    @Test
    fun contextLoads() {
        assertThat(employeeRepository).isNull()
    }

}
