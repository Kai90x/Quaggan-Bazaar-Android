package com.kai.gwtwohot.Fragments

import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import com.kai.gwtwohot.Extensions.circularReveal
import com.kai.gwtwohot.Extensions.show
import com.kai.gwtwohot.Extensions.start
import com.kai.gwtwohot.Extensions.stop
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
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (arguments != null) {
            link = arguments.getString(LINK)
            setToolbarSubtitleTitle(arguments.getString(TITLE))
        }

        loadUrl()
    }

    private fun loadUrl() {
        if (!NetworkUtils.isNetworkAvailable(kaiActivity!!.getContext())) {
            errorView?.show(R.string.dialog_no_network, { loadUrl()})
        } else if (link != null) {
            webView?.setWebViewClient(object : WebViewClient() {
                override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                    super.onPageStarted(view, url, favicon)
                    progress?.start()
                }

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
        val LINK: String = "link"
        val TITLE: String = "title"

        fun newInstance(link: String,title: String) : WebFragment {
            val frag = WebFragment()
            val args = Bundle()
            args.putString(LINK, link)
            args.putString(TITLE, title)
            frag.arguments = args
            return frag
        }
    }
}