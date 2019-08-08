package com.assignment.urlShortener.URLShortener.repository

import com.assignment.urlShortener.URLShortener.entity.UrlInfo
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository("urlHandlerRepository")
interface UrlHandlerRepository:CrudRepository<UrlInfo,Long> {

    @Query("SELECT coalesce(max(id),0) FROM UrlInfo")
     fun getLastId():Long;

    @Query("SELECT u.bigUrl FROM UrlInfo u where u.baseValue=:baseValue")
    fun getBigUrl(@Param("baseValue")baseValue:String):String
}