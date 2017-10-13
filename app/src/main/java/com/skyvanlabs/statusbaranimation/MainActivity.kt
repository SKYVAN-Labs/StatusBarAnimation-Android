package com.skyvanlabs.statusbaranimation

import android.animation.ObjectAnimator
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.animation.AccelerateInterpolator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val statusBarHSV = FloatArray(3)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        Color.colorToHSV(window.statusBarColor, statusBarHSV)

        button_fade_out.setOnClickListener({
            animate(0)
        })

        button_fade_in.setOnClickListener({
            animate(255)
        })
    }

    private fun animate(newAlpha: Int) {
        with(ObjectAnimator.ofInt(Color.alpha(window.statusBarColor), newAlpha)) {
            addUpdateListener {
                window.statusBarColor = Color.HSVToColor(animatedValue as Int, statusBarHSV)
            }

            interpolator = AccelerateInterpolator()
            duration = 400
            start()
        }
    }
}
