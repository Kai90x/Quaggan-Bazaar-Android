package com.kai.gwtwohot.Fragments

import android.app.Fragment
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.util.Log
import android.widget.Toast
import com.kai.gwtwohot.APIServices.QuagganAPI
import com.kai.gwtwohot.Activities.IKaiActivity
import com.kai.gwtwohot.Activities.MainActivity
import com.kai.gwtwohot.DaoRepositories.EventRepository
import com.kai.gwtwohot.Event
import com.kai.gwtwohot.Factory.RetrofitFactory
import com.kai.gwtwohot.R
import com.kai.gwtwohot.Serialization.QuagganApi.Details
import com.kai.gwtwohot.Serialization.QuagganApi.Events.Events
import com.kai.gwtwohot.Serialization.QuagganApi.Simple.Data
import com.kai.gwtwohot.Serialization.QuagganApi.Simple.Simple
import com.kai.gwtwohot.Utils.ExceptionUtils
import com.kai.gwtwohot.Utils.NetworkUtils
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by ikraammoothianpillay1 on 3/6/16.
 */

abstract class BaseFragment : Fragment() {
    protected var kaiActivity: IKaiActivity? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        kaiActivity = if (activity is MainActivity) this.activity as MainActivity else null
    }

    protected fun setToolbarTitle(title: Int) {
        kaiActivity?.getToolbar()?.setTitle(title)
    }

    protected fun setToolbarTitle(title: String) {
        kaiActivity?.getToolbar()?.title = title
    }

    protected fun setToolbarSubtitleTitle(title: Int) {
        kaiActivity?.getToolbar()?.setSubtitle(title)
    }

    protected fun setToolbarSubtitleTitle(title: String) {
        kaiActivity?.getToolbar()?.subtitle = title
    }

    protected fun showEmailSnackbar(mailContent:String, message: Int = R.string.dialog_error_occured_3) {
        val snackbar = Snackbar.make(kaiActivity?.getCoordinatorLayout()!!, message, Snackbar.LENGTH_LONG)
                .setAction(R.string.dialog_ok, {
                    if (NetworkUtils.isNetworkAvailable(activity)) {
                        val service = RetrofitFactory.createService(QuagganAPI::class.java, QuagganAPI.BaseURL)
                        service.email("kai_x@outlook.com",mailContent,"quagganbazaar@kai-mx.net","Quaggan Bazaar Android Error").subscribeOn(Schedulers.newThread())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(object : Subscriber<Data<Simple>>() {
                                    override fun onCompleted() { showSnackbar(R.string.sent_email) }
                                    override fun onError(e: Throwable) {
                                        showSnackbar(R.string.could_not_send_email)
                                        Log.e("Error email",ExceptionUtils.getStackTrace(e))
                                    }

                                    override fun onNext(newJson: Data<Simple>) {}
                                })
                    } else {
                        showSnackbar(R.string.snackbar_noConnection)
                    }
                });
        snackbar.show();
    }

    protected fun showSnackbar(message: Int) {
        val snackbar = Snackbar.make(kaiActivity?.getCoordinatorLayout()!!, message, Snackbar.LENGTH_LONG)
        snackbar.show();
    }

}