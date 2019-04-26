package com.idiotleon.tutorialdagger2withkotlin.basic

class MainPresenter(private val client:DINetworkClient){
    fun connect(show:(String)->Unit){
        show(client.fetchData())
    }
}