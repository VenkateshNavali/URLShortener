package com.assignment.urlShortener.URLShortener.serviceImpl

import com.assignment.urlShortener.URLShortener.entity.UrlInfo
import com.assignment.urlShortener.URLShortener.service.UrlHandlerService
import com.assignment.urlShortener.URLShortener.repository.UrlHandlerRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.env.Environment
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.net.URI


@Service
@Qualifier("urlHandlerService")
class UrlHandlerServiceImpl: UrlHandlerService {

    @Value("\${response.Url.format}")
     var hostUrl: String = "http://localhost:8086/"


    @Autowired
     val urlHandlerRepository: UrlHandlerRepository? = null


    override fun convertToShortUrl(bigUrl: String): String {

         var lastId = urlHandlerRepository?.getLastId()

         var nextId = lastId?.plus(1) ?: 1;
         var baseValue = converter(62,nextId)

         var urlInfo = UrlInfo(nextId, baseValue, bigUrl)
         var entitySaved= urlHandlerRepository?.save(urlInfo)

         var valueToreturn=hostUrl+baseValue

         return valueToreturn
    }

    fun converter(base: Int, decimal: Long): String {

        var decimalNumber = decimal

        val baseDigits = arrayOf("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z")

        var tempVal = if (decimalNumber == 0L) "0" else ""
        var mod: Long=0

        while (decimalNumber != 0L) {
            mod = decimalNumber % base
            //since i need to take reminder in reverse manner, appending tempVal after
            tempVal = baseDigits[mod.toInt()] + tempVal
            decimalNumber = decimalNumber / base
        }

        return tempVal
    }

    override fun redirectToBigUrl(baseValue: String): ResponseEntity<Object> {
        var bigUrl:String = urlHandlerRepository?.getBigUrl(baseValue)?:"https://found.ee/super"

        val url =  URI(bigUrl);
        val httpHeaders = HttpHeaders();
        httpHeaders.location=url
        val response: ResponseEntity<Object> = ResponseEntity(httpHeaders,HttpStatus.TEMPORARY_REDIRECT)
        return response
    }
}