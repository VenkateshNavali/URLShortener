package com.assignment.urlShortener.URLShortener.appInitializer.test

import com.assignment.urlShortener.URLShortener.Application
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@WebMvcTest()
@ContextConfiguration(classes=arrayOf(Application::class))

class ApplicationTests {

    @Test
    fun contextLoads() {
    }

}
