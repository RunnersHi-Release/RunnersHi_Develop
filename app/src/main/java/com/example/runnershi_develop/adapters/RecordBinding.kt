package com.example.runnershi_develop.adapters

import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.runnershi_develop.R
import com.example.runnershi_develop.data.OpponentRunningDetail

@BindingAdapter("backgroundImage")
fun bindBackground(imageView: ImageView, result: Int) {
    if (result == 1) {
        Glide.with(imageView.context)
            .load(R.drawable.blueline___recbadgefragment_winnerrecord)
            .into(imageView)
    } else {
        Glide.with(imageView.context)
            .load(R.drawable.grayline___recbadgefragment_winnerrecord)
            .into(imageView)
    }
}

@BindingAdapter("backgroundLayout")
fun bindBackgroundLayout(layout: ConstraintLayout, result: Int) {
    if (result == 1) {
        layout.setBackgroundResource(R.drawable.bluebox_recdetailactivity)
    } else {
        layout.setBackgroundResource(R.drawable.graybox_recdetailactivity)
    }
}

@BindingAdapter("date")
fun bindDate(textView: TextView, date: String) {
    val sliced = date.split("-")
    val newFormatDate = "${sliced[0]}.${sliced[1]}.${sliced[2]}"
    textView.text = newFormatDate
}

@BindingAdapter("distance")
fun bindDistance(textView: TextView, meter: Int?) {
    meter?.let{
        val km = meter / 1000.0
        textView.text = String.format("%.2f", km)
    }
}

@BindingAdapter("time")
fun bindTime(textView: TextView, time: String?) {
    time?.let{
        with(time.split(":")) {
            textView.text = if (this[0] == "00") {
                "${this[1]}:${this[2]}"
            } else {
                "${this[0][1]}:${this[1]}:${this[2]}"
            }
        }
    }
}

@BindingAdapter("opponentIsVisible")
fun bindVisible(layout: ConstraintLayout, opponentRunningDetail: OpponentRunningDetail?){
    layout.isVisible = opponentRunningDetail != null
}