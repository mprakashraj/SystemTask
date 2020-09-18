package com.example.systemtask

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.systemtask.adapter.BaseRecyclerViewAdapter
import com.example.systemtask.adapter.OnDataBindCallback
import com.example.systemtask.baseActivity.BaseActivity
import com.example.systemtask.baseActivity.BaseNavigator
import com.example.systemtask.databinding.ActivityMainBinding
import com.example.systemtask.databinding.HomeAdapterlistBinding
import com.example.systemtask.raw.JsonReader
import com.google.gson.reflect.TypeToken
import java.io.IOException

class MainActivity :  BaseActivity<ActivityMainBinding, SplashViewModel>(), BaseNavigator {
    private lateinit var viewModel: SplashViewModel

    private var adapterUserList : BaseRecyclerViewAdapter<UserListModel, HomeAdapterlistBinding>? =
        null
    override fun getBindingVariable(): Int {
        return BR.homeVM
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun getViewModel(): SplashViewModel {
        viewModel = ViewModelProvider(this).get(SplashViewModel::class.java)
        return viewModel
    }

    override fun onClickView(v: View?) {

    }

    override fun goTo(clazz: Class<*>, mExtras: Bundle?) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setNavigator(this)

        mViewDataBinding?.rvUserList?.layoutManager =
            LinearLayoutManager(
                this,
                LinearLayoutManager.VERTICAL,
                false
            )
        adapterUserList =
            BaseRecyclerViewAdapter(R.layout.home_adapterlist, BR.homeAdapterVM, ArrayList(),
                null, object : OnDataBindCallback<HomeAdapterlistBinding> {
                    override fun onItemClick(view: View, position: Int, v: HomeAdapterlistBinding) {

                    }

                    override fun onDataBind(v: HomeAdapterlistBinding, onClickListener: View.OnClickListener) {

                    }

                })

        mViewDataBinding?.rvUserList?.adapter = adapterUserList

        val userList = readProductsList()
        adapterUserList?.addDataSet(userList)

    }

    private fun readProductsList(): ArrayList<UserListModel> {
        val inputStream = resources.openRawResource(R.raw.userlistrecord)

        val productListType = object : TypeToken<ArrayList<UserListModel>>() {

        }.type
        try {
            return JsonReader.readJsonStream<ArrayList<UserListModel>>(
                inputStream,
                productListType
            )
        } catch (e: IOException) {
            return ArrayList()
        }
    }
}