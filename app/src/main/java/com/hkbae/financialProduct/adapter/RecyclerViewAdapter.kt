package com.hkbae.financialProduct.adapter

import android.app.Activity
import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.hkbae.financialProduct.R
import com.hkbae.financialProduct.databinding.RecommendedItemViewBinding
import com.hkbae.financialProduct.model.FinancialProduct
import com.hkbae.financialProduct.model.SavingsData

class RecyclerViewAdapter(
    private val recommendedList : ArrayList<FinancialProduct>,
    val activity : Activity
) :RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

        inner class ViewHolder(private val binding : RecommendedItemViewBinding) : RecyclerView.ViewHolder(binding.root){

            fun bind(data:FinancialProduct){
                binding.financialProduct=data
                binding.detailBtn.setOnClickListener {
                    var detailInfo=data.detailInfoAsText()

                    if(data is SavingsData){
                        detailInfo+="\n\n저축유형(자율적립형 : F, 정기적금형 : S) : ${data.rsrv_type}"
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

        val binding = DataBindingUtil
            .inflate<RecommendedItemViewBinding>(LayoutInflater.from(parent.context)
                ,R.layout.recommended_item_view, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(recommendedList[position])
    }

    override fun getItemCount(): Int {
        return recommendedList.size
    }
}