package com.tjrwns8024.ttt_android.base

import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.tjrwns8024.ttt_android.BR

abstract class BaseActivity<T : ViewDataBinding>(
    private val layoutId: Int
) : AppCompatActivity() {

    private var _binding: T? = null
    protected val binding get() = _binding!!

    abstract val viewModel: BaseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(this, layoutId)

        _binding?.setVariable(BR.vm, viewModel)
        _binding?.lifecycleOwner = this

        observeEvent()

        //전체화면
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)

            window.attributes.layoutInDisplayCutoutMode =
                WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
        }
    }

    abstract fun observeEvent()

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}