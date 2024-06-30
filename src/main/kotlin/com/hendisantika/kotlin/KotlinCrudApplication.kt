package com.hendisantika.kotlin

import com.hendisantika.kotlin.entity.Person
import com.hendisantika.kotlin.repository.PersonRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import java.util.UUID
import kotlin.system.measureTimeMillis

//github source :- https://github.com/hendisantika/kotlin-crud
@SpringBootApplication
class KotlinCrudApplication {
    @Bean
    fun init(repository: PersonRepository) = CommandLineRunner {
        val personListTest  =personListDummyData();

        repository.saveAll(personListTest)

        val personList = (1..100).map { generateRandomPerson() }

        // Measure time for forEach and save
        val forEachSaveTime = measureTimeMillis {
            personList.forEach { person -> repository.save(person) }
        }
        println("Time taken for forEach and save: $forEachSaveTime ms")

        // Measure time for saveAll
        val saveAllTime = measureTimeMillis {
            repository.saveAll(personList)
        }
        println("Time taken for saveAll: $saveAllTime ms")
    }
}

fun generateRandomPerson(): Person {
    val id = UUID.randomUUID().mostSignificantBits and Long.MAX_VALUE
    val name = "Name_${UUID.randomUUID()}"
    val level = "Level_${UUID.randomUUID()}"
    return Person(id, name, level)
}
fun personListDummyData(): MutableList<Person> {
    val personNameArr = arrayOf("Naruto", "Kakshi", "Sakura", "Sasuke", "Jiraiya");
    val personLevelArr= arrayOf("Hokage", "Hokage", "Jounin", "Jounin", "Sannin" );
    var personCount =personNameArr.size;

    val personList = (0 until personCount)
            .map { i ->
                println(" i : "+i)
                val name = personNameArr[i]
                val level = personLevelArr[i]
                val id = personCount.toLong()
                if (personCount != 0) --personCount
                Person(id, name, level)
            }
           .toMutableList()
    return personList;
}

fun main(args: Array<String>) {
    SpringApplication.run(KotlinCrudApplication::class.java, *args)
}
