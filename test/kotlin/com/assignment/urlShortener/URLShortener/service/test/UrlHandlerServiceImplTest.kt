package com.assignment.urlShortener.URLShortener.service.test

import com.assignment.urlShortener.URLShortener.entity.UrlInfo
import com.assignment.urlShortener.URLShortener.repository.UrlHandlerRepository
import com.assignment.urlShortener.URLShortener.serviceImpl.UrlHandlerServiceImpl
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.junit.Assert.assertEquals
import org.junit.Before
import org.mockito.MockitoAnnotations
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import java.net.URI

class UrlHandlerServiceImplTest{


    @InjectMocks
    lateinit var urlHandlerService:UrlHandlerServiceImpl

    @Mock
    lateinit var urlHandlerRepository : UrlHandlerRepository


    @Value("\${response.Url.format}")
    var hostUrl: String = "http://localhost:8086/"


    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun `service Convert to Base method test`(){

        val urlInfo = UrlInfo(0,"b","https://found.ee/super")


        Mockito.`when`(urlHandlerRepository.getLastId()).thenReturn(0)
        Mockito.`when`(urlHandlerRepository.save(Mockito.any(UrlInfo::class.java))).thenReturn(urlInfo)

        val baseVale= urlHandlerService?.convertToShortUrl("https://found.ee/super")

       assertEquals((hostUrl+"b"),baseVale)
    }


    @Test
    fun `Test ConvertToBaseMethod`(){

        var baseValue1=  urlHandlerService.converter(62,1)
        assertEquals("b",baseValue1)

        var baseValue2=  urlHandlerService.converter(62,6785443)
        assertEquals("34mD",baseValue2)
    }

    @Test
    fun `Test redirectToBigUrl`(){

        val url =  URI("https://found.ee/super");
        val httpHeaders = HttpHeaders();
        httpHeaders.location=url
        val response: ResponseEntity<Object> = ResponseEntity(httpHeaders, HttpStatus.TEMPORARY_REDIRECT)

        Mockito.`when`(urlHandlerRepository.getBigUrl(Mockito.anyString())).thenReturn("https://found.ee/super")

        val result:ResponseEntity<Object> = urlHandlerService.redirectToBigUrl("b")?:response


        assertEquals(HttpStatus.TEMPORARY_REDIRECT.value(),result.statusCode.value())
        assertEquals("https://found.ee/super",result.headers.location.toString())
    }

}