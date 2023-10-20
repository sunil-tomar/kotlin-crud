package com.hendisantika.kotlin.service

import com.hendisantika.kotlin.entity.Person
import com.hendisantika.kotlin.repository.PersonRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityNotFoundException


/**
 * Created by hendisantika on 3/12/17.
 */

@Service
@Transactional
class PersonService {
    @Autowired
    internal var personRepository: PersonRepository? = null

    fun findAll(): List<Person> {
        return personRepository!!.findAll(Sort.by(Sort.Direction.ASC, "id"))
    }

    fun save(person: Person): Person {
        return personRepository!!.save(person)
    }

    fun delete(id: Long?) {
        personRepository!!.deleteById(id)
    }

    fun find(id: Long?): Person {
        return personRepository!!.findById(id).orElseThrow { EntityNotFoundException("Person with id $id not found") }
    }
}