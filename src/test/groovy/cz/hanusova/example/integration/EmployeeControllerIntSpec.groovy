package cz.hanusova.example.integration

import com.fasterxml.jackson.databind.ObjectMapper
import cz.hanusova.example.dto.EmployeeDto
import cz.hanusova.example.repository.EmployeeRepository
import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

import java.time.LocalDate

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post

@AutoConfigureMockMvc
@SpringBootTest
class EmployeeControllerIntSpec extends Specification {

    @Autowired
    private MockMvc mockMvc
    @Autowired
    private ObjectMapper objectMapper

    @SpringBean
    private EmployeeRepository employeeRepository = Mock(EmployeeRepository)

    def 'When POST employee is performed, then all the incoming information is saved in database'() {
        given: 'Employee data'
        def incomingEmployee = new EmployeeDto(name, surname, birthdate.toString())

        when: 'POST request is performed'
        mockMvc.perform(post('/employee')
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(incomingEmployee)))

        then: 'Employee is saved in database'
        1 * employeeRepository.save({
            it.name == name
            it.surname == surname
            it.birthDate == birthdate
        })

        where:
        name   | surname | birthdate
        'John' | 'Doe'   | LocalDate.of(1990, 1, 1)
        'Jane' | 'Doe'   | LocalDate.of(1991, 2, 2)
        'John' | 'Smith' | LocalDate.of(1992, 3, 3)
    }

}