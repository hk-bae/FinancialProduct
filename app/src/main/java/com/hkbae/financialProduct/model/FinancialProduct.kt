package com.hkbae.financialProduct.model

import java.io.Serializable

//금융상품에 대한 abstract class
//Deposit(정기예금), SavingsData(적금)이 이를 상속받는다.
//멤버 필드로 BaseInfo(기본정보), Option(추가정보)를 갖는다.
open class FinancialProduct(
    var baseInfo: BaseInfo=BaseInfo(),
    var option: Option=Option()
):Serializable {
    var maturityPayment : Int=0
}


// 상품에 대한 기본정보 클래스
// 금융감독원 금융상품 한눈에 홈페이지의 정기예금API 및 적금API의 baseInfo 정보들을 저장하는 클래스이다.
class BaseInfo : Serializable {
    var dcls_month: String?=null // 공시제출월[YYYYMM]
    var fin_co_no: String?=null // 금융회사코드
    var fin_prdt_cd: String?=null // 금융상품코드
    var kor_co_nm: String?=null // 금융회사명
    var fin_prdt_nm: String?=null // 금융상품명
    var join_deny:Int?=null // 가입제한 (1:제한없음 2:서민전용 3:일부제한)
    var join_way: String?=null // 가입방법
    var spcl_cnd: String?=null // 우대조건
    var join_member: String?=null // 가입대상(키워드 : 만 XX세 이상, 여성)
    var etc_note: String?=null // 기타유의사항
    var max_limit:Int?=null // 최고한도
}
// 아래 3개의 멤버들은 option과 연결되는 멤버들이다.
// 하나의 (dcls_month,fin_co_no,fin_prdt_cd) 쌍에 대하여 여러 개의 옵션을 가질 수 있다.



class Option : Serializable{
    var dcls_month : String?=null // 공시제출월[YYYYMM]
    var fin_co_no : String?=null// 금융회사코드
    var fin_prdt_cd : String?=null// 금융상품코드
    var intr_rate2 :Double=0.0 // 최고 우대금리[소수점 2자리]
    var save_trm : Int?=null // 저축 기간[단위:개월]
    var intr_rate_type : Char?=null // 저축금리유형(단리 :S 복리 :M)
    var intr_rate : Double=0.0 // 저축금리[소수점 2자리]
}