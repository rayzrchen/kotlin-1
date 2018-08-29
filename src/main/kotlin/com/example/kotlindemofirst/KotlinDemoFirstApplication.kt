package com.example.kotlindemofirst

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*
import java.util.stream.Stream
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@SpringBootApplication
class KotlinDemoFirstApplication(val personRepository: PersonRepository) : CommandLineRunner {

    override fun run(vararg args: String?) {
        personRepository.deleteAll()

        Stream.of("a,b", "c,d")
                .map { it.split(",") }
                .forEach { personRepository.save(Person(it[0], it[1])) }

        personRepository.findAll().forEach { println(it) }

        personRepository.findByFirst("a").stream().forEach { println(it) }

    }

}

fun main(args: Array<String>) {
    runApplication<KotlinDemoFirstApplication>(*args)
}


interface PersonRepository : JpaRepository<Person, UUID> {
    fun findByFirst(first: String?): MutableList<Person>
}


@Entity
data class Person(var first: String? = null,
                  var last: String? = null,
                  @Id @GeneratedValue var id: UUID? = null)