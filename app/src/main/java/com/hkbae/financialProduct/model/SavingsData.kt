package com.hkbae.financialProduct.model


//적금 클래스
class SavingsData(
    baseInfo: BaseInfo, option: Option,
    var rsrvType: Char // 적립유형 (자유적립식 : F, 정액정립식 :S)
) : FinancialProduct(baseInfo, option) {

}