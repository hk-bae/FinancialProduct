package com.hkbae.financialProduct.model

import java.io.Serializable
import java.util.*


class UserInfo(
    var productType : Int=0,//정기예금 = 0, 적금=1
    var months:Int=0, // 가입기간(개월)
    var amounts : Int=0,//월 납입금액(원) 또는 예치금(원)
    var joinPath : Array<String?> =arrayOfNulls<String>(4), // 가입경로 0.영업점 1.인터넷 2.스마트폰 3.콜센터
    var sex : Int=0, //0.남자 1.여자
    var age : Int=0, //만 나이
    var cities :HashSet<City> = hashSetOf<City>(),  //은행방문 가능지역
    var conditions :Array<String?> = arrayOfNulls<String>(7) //추가조건
   //0.기초생활수급자 1.소년소녀가장 2.북한이탈주민 3.결혼이민자 4.근로장려금수급자 5.한부모가족지원 보호대상자 6.차상위계층 대상자
) : Serializable {


    enum class City(val cityName:String){
        SEOUL("서울"),BUSAN("부산"),DAEGU("대구"),INCHEON("인천"),
        GWANGJU("광주"),DAEJEON("대전"),ULSAN("울산"),GYEONGGI("경기"),
        GANGWON("강원"),CHUNGBUK("충북"),CHUNGNAM("충남"),JEONBUK("전북"),
        JEONNAM("전남"),GYEONGBUK("경북"),GYEONGNAM("경남"),JEJU("제주")
    }

}