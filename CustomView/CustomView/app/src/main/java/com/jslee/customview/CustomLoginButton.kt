package com.jslee.customview

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.jslee.customview.databinding.CustomLoginButtonBinding

class CustomLoginButton : ConstraintLayout {
    private var layoutBackground: ConstraintLayout? = null
    private var iconImageView: ImageView? = null
    private var loginTextView: TextView? = null

    private val binding: CustomLoginButtonBinding =
        CustomLoginButtonBinding.inflate(LayoutInflater.from(context), this, false)

    init {
        addView(binding.root)
    }

    constructor(context: Context) : super(context) {
        initView()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initView()
        getAttrs(attrs)
    }

    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyle: Int
    ) : super(context, attrs, defStyle) {
        getAttrs(attrs, defStyle)
    }

    private fun initView() {
        iconImageView = binding.iconImageView
        loginTextView = binding.loginTextView
        layoutBackground = binding.customLoginButtonLayout
    }

    private fun getAttrs(attrs: AttributeSet?) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomLoginButton)
        setTypedArray(typedArray)
    }

    private fun getAttrs(attrs: AttributeSet?, defStyle: Int) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomLoginButton, defStyle, 0)
        setTypedArray(typedArray)
    }

    private fun setTypedArray(typedArray: TypedArray) {
        val bgColor = typedArray.getColor(R.styleable.CustomLoginButton_bgColor, 0)
        layoutBackground?.setBackgroundColor(bgColor)

        val symbolIconId = typedArray.getResourceId(R.styleable.CustomLoginButton_symbol_icon, R.drawable.ic_apple_logo)
        iconImageView?.setImageResource(symbolIconId)

        val textString = typedArray.getString(R.styleable.CustomLoginButton_text)
        loginTextView?.text = textString

        val textColorId = typedArray.getColor(R.styleable.CustomLoginButton_textColor, 0)
        loginTextView?.setTextColor(textColorId)

        typedArray.recycle()
    }


}