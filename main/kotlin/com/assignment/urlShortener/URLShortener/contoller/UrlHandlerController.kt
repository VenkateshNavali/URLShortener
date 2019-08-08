package com.assignment.urlShortener.URLShortener.contoller;

import com.assignment.urlShortener.URLShortener.service.UrlHandlerService
import javax.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/")
class UrlHandlerController{

    @Autowired
    private val urlHandlerService: UrlHandlerService? = null


    @GetMapping("/createShortURL")
    fun createShortURL( request:HttpServletRequest,@RequestParam("bigURL",required = true) bigURL:String):String
    {return urlHandlerService?.convertToShortUrl(bigURL)?:"something went wrong";}


    @GetMapping(value = "/{baseValue}")
    fun redirect(@PathVariable("baseValue") baseValue:String):ResponseEntity<Object>
    {var response:ResponseEntity<Object> =urlHandlerService!!.redirectToBigUrl(baseValue)
        return response}



}