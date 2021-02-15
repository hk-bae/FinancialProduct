package com.hkbae.financialProduct.model

import java.io.Serializable


data class User (
    var id : String="",
    var password : String?=null,
    var name : String?=null,
    var born : String?=null
):Serializable
