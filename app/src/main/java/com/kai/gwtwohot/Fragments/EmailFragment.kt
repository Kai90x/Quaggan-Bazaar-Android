package com.kai.gwtwohot.Fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.kai.gwtwohot.APIServices.QuagganAPI
import com.kai.gwtwohot.Extensions.circularReveal
import com.kai.gwtwohot.Extensions.emailValidate
import com.kai.gwtwohot.Factory.RetrofitFactory
import com.kai.gwtwohot.R
import com.kai.gwtwohot.Serialization.QuagganApi.Simple.Data
import com.kai.gwtwohot.Serialization.QuagganApi.Simple.Simple
import com.kai.gwtwohot.Utils.ExceptionUtils
import com.kai.gwtwohot.Utils.NetworkUtils
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by ikraammoothianpillay1 on 3/12/16.
 */
class EmailFragment: BaseFragment() {
    private var edtEmail: EditText? = null
    private var edtContent: EditText? = null
    private var edtSubject: EditText? = null
    private var btnSend: Button? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater!!.inflate(R.layout.fragment_email, container, false)
        edtEmail = rootView.findViewById(R.id.edtEmailAddress) as EditText
        edtContent = rootView.findViewById(R.id.edtEmailContent) as EditText
        edtSubject = rootView.findViewById(R.id.edtEmailSubject) as EditText
        btnSend = rootView.findViewById(R.id.btnSendEmail) as Button
        rootView.circularReveal()
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setToolbarTitle(R.string.preference_email)
        btnSend?.setOnClickListener {
            if (edtEmail?.text.toString().isNullOrEmpty() || edtEmail?.text.toString().emailValidate().not()) {
                showSnackbar(R.string.dialog_invalid_email)
            } else if (edtSubject?.text.toString().isNullOrEmpty()) {
                showSnackbar(R.string.email_no_subject)
            } else if (edtContent?.text.toString().isNullOrEmpty()) {
                    showSnackbar(R.string.email_no_content)
            } else {
                //Send EmailReport
                if (NetworkUtils.isNetworkAvailable(activity)) {
                    val service = RetrofitFactory.createService(QuagganAPI::class.java, QuagganAPI.BaseURL)
                    service.email("kai_x@outlook.com",edtContent?.text.toString(),edtEmail?.text.toString(),edtSubject?.text.toString()).subscribeOn(Schedulers.newThread())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(object : Subscriber<Data<Simple>>() {
                                override fun onCompleted() { showSnackbar(R.string.sent_email) }
                                override fun onError(e: Throwable) {
                                    showSnackbar(R.string.could_not_send_email)
                                    Log.e("Error email", ExceptionUtils.getStackTrace(e))
                                }
                                override fun onNext(newJson: Data<Simple>) {}
                            })
                } else {
                    showSnackbar(R.string.snackbar_noConnection)
                }
                edtContent?.setText("")
                edtSubject?.setText("")
            }
        }
    }

    companion object {
        fun newInstance() : EmailFragment = EmailFragment()
    }
}