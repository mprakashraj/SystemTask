package com.example.systemtask.baseActivity

import android.content.*
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import android.os.Build
import android.os.Bundle
import android.os.Handler
import androidx.annotation.LayoutRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.core.content.ContextCompat
import androidx.appcompat.app.AppCompatActivity
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.FrameLayout
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.Toast
import com.example.systemtask.R

abstract class BaseActivity<T : ViewDataBinding, V : BaseViewModel<*>> : AppCompatActivity() {

    var mViewDataBinding: T? = null
        private set
    var mViewModel: V? = null
        private set
    /**
     * Override for set binding variable
     *
     * @return variable id
     */
    abstract fun getBindingVariable(): Int

    /**
     * @return layout resource id
     */
    @LayoutRes
    abstract fun getLayoutId(): Int

    /**
     * Override for set view model
     *
     * @return view model instance
     */
    abstract fun getViewModel(): V

    //abstract fun getCustomToolbarLayout(): Int

    private var progressBar: ProgressBar? = null

    private var fragment: Fragment? = null
    private var fragmentTransaction: FragmentTransaction? = null
    private val handler = Handler()
    private val interval = 500
    private var runnable: Runnable? = null
    private var isNoNetAlertVisible = false

    companion object {
        const val INTERNET_RESULT_CODE = 200
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        performDataBinding()
    }

    private fun performDataBinding() {
        mViewDataBinding = DataBindingUtil.setContentView(this, getLayoutId())
        this.mViewModel = if (mViewModel == null) getViewModel() else mViewModel
        mViewDataBinding?.setVariable(getBindingVariable(), mViewModel)
        mViewDataBinding?.executePendingBindings()
    }

    fun getViewDataBinding(): T {
        return mViewDataBinding!!
    }

    fun hideKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    /*
    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(LocaleManager.setLocale(base))
    }*/

    /**
     * common toast show for all screens
     *
     */
    fun putToast(message: String?) {
        Toast.makeText(applicationContext, "" + message, Toast.LENGTH_SHORT).show()
    }

    /**
     * common show progress bar for all screens
     *
     * @param parent layout of screen
     */
    fun showProgressBar(parent: ViewGroup) {
        window.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
        progressBar = ProgressBar(this, null, android.R.attr.progressBarStyleLarge)
        progressBar?.indeterminateDrawable?.setColorFilter(
            ContextCompat.getColor(this, R.color.colorPrimary),
            android.graphics.PorterDuff.Mode.SRC_ATOP
        )
        when (parent) {
            is RelativeLayout -> {
                val params = RelativeLayout.LayoutParams(130, 130)
                params.addRule(RelativeLayout.CENTER_IN_PARENT)
                parent.addView(progressBar, params)
            }
            is FrameLayout -> {
                val params = FrameLayout.LayoutParams(130, 130)
                params.gravity = Gravity.CENTER
                parent.addView(progressBar, params)
            }
            is ConstraintLayout -> {
                val params = ConstraintLayout.LayoutParams(130, 130)
                params.topToTop = ConstraintSet.PARENT_ID
                params.startToStart = ConstraintSet.PARENT_ID
                params.endToEnd = ConstraintSet.PARENT_ID
                params.bottomToBottom = ConstraintSet.PARENT_ID
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    parent.elevation = 10f
                }
                parent.addView(progressBar, params)
            }
        }
        progressBar?.visibility = View.VISIBLE  //To show ProgressBar
    }

    fun dismissProgressBar() {
        if (progressBar?.visibility == View.VISIBLE) {
            progressBar?.visibility = View.GONE     // To Hide ProgressBar
            window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }


    override fun onPause() {
        super.onPause()
       // unregisterReceiver(networkChangeReceiver)

    }


    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onResume() {
        super.onResume()
    }

}