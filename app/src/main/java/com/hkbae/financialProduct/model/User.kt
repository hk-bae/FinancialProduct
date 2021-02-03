package com.hkbae.financialProduct.model

import java.io.Serializable


class User (
    var id : String="",
    var password : String?=null,
    var name : String?=null,
    var born : String?=null
):Serializable
