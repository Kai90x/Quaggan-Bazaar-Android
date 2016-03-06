package com.kai.gwtwohot.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import com.kai.gwtwohot.Activities.IKaiActivity
import com.kai.gwtwohot.R
import com.kai.gwtwohot.Utils.NetworkUtils
import tr.xip.errorview.ErrorView

/**
 * Created by ikraammoothianpillay1 on 3/6/16.
 */
class WebFragment : BaseFragment() {
    protected var errorView: ErrorView? = null
    protected var webView: WebView? = null
    protected var link: String? = null
    protected var title: String? = null
    protected var progress: ProgressBar? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater!!.inflate(R.layout.fragment_webview, container, false)
        errorView = rootView.findViewById(R.id.errorView) as ErrorView
        webView = rootView.findViewById(R.id.webview) as WebView
        progress = rootView.findViewById(R.id.progressBar) as ProgressBar

        rootView?.circularReveal()
        setToolbarSubtitleTitle(title!!)

        loadUrl()
        return rootView
    }

    private fun loadUrl() {
        if (!NetworkUtils.isNetworkAvailable(activity!!.getContext())) {
            errorView?.show(R.string.dialog_no_network, { loadUrl()})
        } else if (link != null) {
            webView?.setWebViewClient(object : WebViewClient() {
                override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                    progress?.start()
                    return true
                }

                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    progress?.stop()
                }
            })

            webView?.settings?.javaScriptEnabled = true
            webView?.loadUrl(link)
        }
    }

    companion object {
        fun newInstance(activity: IKaiActivity,link: String,title: String) : WebFragment {
            val frag = WebFragment()
            frag.activity = activity
            frag.link = link
            frag.title = title
            return frag
        }
    }
}