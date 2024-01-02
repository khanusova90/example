package cz.hanusova.example.model

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDate

@Document
class Employee {
    @Id
    var id: ObjectId? = null
    var name: String? = null
    var surname: String? = null
    var birthDate: LocalDate? = null

    constructor()

    constructor(name: String?, surname: String?, birthDate: LocalDate?) {
        this.name = name
        this.surname = surname
        this.birthDate = birthDate
    }
}
