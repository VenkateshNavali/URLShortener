package com.assignment.urlShortener.URLShortener

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories


@SpringBootApplication
@EntityScan("com.assignment.urlShortener.URLShortener.entity")
@ComponentScan("com.assignment.urlShortener.URLShortener")
class Application

 fun main(args: Array<String>) {

	 SpringApplication.run(Application::class.java,*args)
	}
