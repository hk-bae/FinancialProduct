package com.hkbae.financialProduct.adapter

import android.app.Activity
import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hkbae.financialProduct.R
import com.hkbae.financialProduct.model.FinancialProduct
import com.hkbae.financialProduct.model.SavingsData
import kotlinx.android.synthetic.main.recommended_item_view.view.*

class RecyclerViewAdapter(
    val recommendedList : ArrayList<FinancialProduct>,
    val infalter : LayoutInflater,
    val activity : Activity
) :RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

        inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
            var korCoNm: TextView
            var finPrdtNm : TextView
            var prdtInfo:TextView
            var detailBtn : Button

            init{
                korCoNm=itemView.kor_co_nm
                finPrdtNm=itemView.fin_prdt_nm
                prdtInfo=itemView.prdt_info
                detailBtn=itemView.detail_btn
                //상세보기 클릭 시
                detailBtn.setOnClickListener {
                    val position =adapterPosition
                    val product = recommendedList.get(position)
                    var detailInfo="은행명 : ${product.baseInfo.kor_co_nm}\n\n" +
                            "상품명 : ${product.baseInfo.fin_prdt_nm}\n\n" +
                            "가입경로 : ${product.baseInfo.join_way}\n\n"+
                            "가입대상 : ${product.baseInfo.join_member}\n\n"+
                            "우대조건 : ${product.baseInfo.spcl_cnd}\n\n" +
                            "기타 유의사항 : ${product.baseInfo.etc_note}\n\n" +
                            "최고한도 : ${product.baseInfo.max_limit}\n\n" +
                            "기본금리 : ${product.option.intr_rate}\n\n" +
                            "최고금리 : ${product.option.intr_rate2}\n\n" +
                            "저축기간 : ${product.option.save_trm}\n\n" +
                            "저축방법 (단리 : S , 복리 : M) : ${product.option.intr_rate_type}"

                    if(product is SavingsData){
                        detailInfo+="\n저축유형(자율적립형 : F, 정기적금형 : S) : ${product.rsrv_type}"
                    }

                    val showDetailDialog : AlertDialog = AlertDialog.Builder(activity)
                        .setTitle("상세 보기")
                        .setMessage(detailInfo)
                        .setPositiveButton("확인",null)
                        .create()
                    showDetailDialog.show()
                }
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = infalter.inflate(R.layout.recommended_item_view,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.korCoNm.setText(recommendedList.get(position).baseInfo.kor_co_nm)
        holder.finPrdtNm.setText(recommendedList.get(position).baseInfo.fin_prdt_nm)

        val product : FinancialProduct = recommendedList.get(position)
        var prdtInfoString : String ="기본 금리 : ${product.option.intr_rate} %\n" +
                "최고금리 : ${product.option.intr_rate2} %\n" +
                "만기지급액 : ${product.maturityPayment} 원"
        holder.prdtInfo.setText(prdtInfoString)
    }

    override fun getItemCount(): Int {
        return recommendedList.size
    }
}