package com.hkbae.financialProduct.model

//금융상품에 대한 abstract class
//Deposit(정기예금), SavingsData(적금)이 이를 상속받는다.
//멤버 필드로 BaseInfo(기본정보), Option(추가정보)를 갖는다.
abstract class FinancialProduct(var baseInfo: BaseInfo, var option: Option) {

    var maturityPayment : Int =0 //만기 지급액

}


// 상품에 대한 기본정보 클래스
// 금융감독원 금융상품 한눈에 홈페이지의 정기예금API 및 적금API의 baseInfo 정보들을 저장하는 클래스이다.
class BaseInfo {
    // setter 메서드
    // getter 메서드
    // 아래 3개의 멤버들은 option과 연결되는 멤버들이다.
    // 하나의 (dcls_month,fin_co_no,fin_prdt_cd) 쌍에 대하여 여러 개의 옵션을 가질 수 있다.

    var dclsMonth // 공시제출월[YYYYMM]
            : String? = null
    var finCoNo // 금융회사코드
            : String? = null
    var finPrdtCd // 금융상품코드
            : String? = null
    var korCoNm // 금융회사명
            : String? = null
    var finPrdtNm // 금융상품명
            : String? = null
    var joinWay // 가입방법
            : String? = null
    var spclCnd // 우대조건
            : String? = null
    var joinDeny // 가입제한 (1:제한없음 2:서민전용 3:일부제한)
            = 0
    var joinMember // 가입대상(키워드 : 만 XX세 이상, 여성)
            : String? = null
    var etcNote // 기타유의사항
            : String? = null
    var maxLimit // 최고한도
            = 0

}

class Option {
    // setter메서드
    // getter메서드

    var dclsMonth // 공시제출월[YYYYMM]
            : String? = null
    var finCoNo // 금융회사코드
            : String? = null
    var finPrdtCd // 금융상품코드
            : String? = null
    var intrRateType // 저축금리유형(단리 :S 복리 :M)
            = 0.toChar()
    var saveTrm // 저축 기간[단위:개월]
            = 0
    var intrRate // 저축금리[소수점 2자리]
            = 0.0
    var intrRate2 // 최고 우대금리[소수점 2자리]
            = 0.0

}