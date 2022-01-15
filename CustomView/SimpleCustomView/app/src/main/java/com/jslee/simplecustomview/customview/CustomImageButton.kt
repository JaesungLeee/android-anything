package com.jslee.simplecustomview.customview

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageButton
import com.jslee.simplecustomview.R
import com.jslee.simplecustomview.enums.ImageButtonState

class CustomImageButton(
    context: Context,
    attrs: AttributeSet
): AppCompatImageButton(context, attrs) {

    fun updateIconWithState(state: ImageButtonState) {
        when (state) {
            ImageButtonState.BEFORE_RECORDING -> {
                setImageResource(R.drawable.ic_baseline_record_24)
            }
            ImageButtonState.ON_RECORDING, ImageButtonState.ON_PLAYING -> {
                setImageResource(R.drawable.ic_baseline_stop_24)
            }
            ImageButtonState.AFTER_RECORDING -> {
                setImageResource(R.drawable.ic_baseline_play_arrow_24)
            }
        }

    }
}