package com.assignment.urlShortener.URLShortener.controller.test

import com.assignment.urlShortener.URLShortener.contoller.UrlHandlerController
import com.assignment.urlShortener.URLShortener.repository.UrlHandlerRepository
import com.assignment.urlShortener.URLShortener.service.UrlHandlerService
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.mock.web.MockHttpServletResponse
import org.springframework.test.web.servlet.MvcResult
import org.springframework.test.web.servlet.RequestBuilder
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import java.net.URI


@RunWith(SpringRunner::class)
@WebMvcTest(value = UrlHandlerController::class)
class UrlHandlerControllerTest{

    @Autowired
    val mockMvc: MockMvc? = null

    @MockBean
    val urlHandlerRepository: UrlHandlerRepository?=null

    @MockBean
    val urlShortenerService: UrlHandlerService?=null

    @Value("\${response.Url.format}")
    var hostUrl: String = "http://localhost:8086/"




    @Test
    fun `createShortURL positive test`(){

        Mockito.`when`(urlShortenerService?.convertToShortUrl(Mockito.anyString())).thenReturn(hostUrl+"34mD")

        var reqBuilder: RequestBuilder =MockMvcRequestBuilders.get("/createShortURL?bigURL=https://found.ee/super").accept(MediaType.APPLICATION_JSON)
        var result: MvcResult =mockMvc?.perform(reqBuilder)!!.andReturn()
        assertEquals((hostUrl+"34mD"),result.getResponse().contentAsString)

    }

    @Test
    fun `createShortURL Negative test`(){



        var reqBuilder: RequestBuilder =MockMvcRequestBuilders.get("/createShortURL?bigURL=https://found.ee/super").accept(MediaType.APPLICATION_JSON)
        var result: MvcResult =mockMvc?.perform(reqBuilder)!!.andReturn()
        assertEquals("something went wrong",result.getResponse().contentAsString)

    }


    @Test
    fun `redirect positive test`(){

        val url =  URI("https://found.ee/super");
        val httpHeaders = HttpHeaders();
        httpHeaders.location=url
        val response: ResponseEntity<Object> = ResponseEntity(httpHeaders, HttpStatus.TEMPORARY_REDIRECT)


        Mockito.`when`(urlShortenerService?.redirectToBigUrl(Mockito.anyString())).thenReturn(response)

        var reqBuilder: RequestBuilder =MockMvcRequestBuilders.get("/34mD").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON);
        var result: MvcResult =mockMvc?.perform(reqBuilder)!!.andReturn()
        var responseTemp:MockHttpServletResponse = result.response
        assertEquals(HttpStatus.TEMPORARY_REDIRECT.value(),responseTemp.status)
        assertEquals("https://found.ee/super",responseTemp.getHeader(HttpHeaders.LOCATION))


    }

}