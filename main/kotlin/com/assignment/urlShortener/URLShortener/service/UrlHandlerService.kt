package com.assignment.urlShortener.URLShortener.service


import org.springframework.http.ResponseEntity

interface UrlHandlerService{
    fun convertToShortUrl(bigUrl:String):String

    fun redirectToBigUrl(baseValue:String):ResponseEntity<Object>
}