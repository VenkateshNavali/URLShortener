package com.assignment.urlShortener.URLShortener.entity

import javax.persistence.Entity
import javax.persistence.Id

@Entity
class UrlInfo(@Id var id:Long?=null,var baseValue:String, var bigUrl:String)

