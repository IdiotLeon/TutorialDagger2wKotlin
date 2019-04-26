package com.idiotleon.tutorialdagger2withkotlin.extra

interface Prefix {
    fun prefix(): String
}

class DataObject0

data class DataObject1(val content: String)

data class DataObject2(val content: DataObject0)