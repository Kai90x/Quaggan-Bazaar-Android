package com.kai.gwtwohot.Dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.kai.gwtwohot.R

/**
 * Created by ikraammoothianpillay1 on 3/7/16.
 */
class DonationDialog : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)
        builder.setTitle(R.string.donation)
        builder.setTitle(R.string.helping_quote1)

        builder.setPositiveButton(R.string.go_to_donation) { dialog, which ->
            val url = getString(R.string.save_child_url)
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(browserIntent)

            dismiss()
        }

        builder.setNegativeButton(R.string.later) { dialog, which -> dismiss() }

        return builder.create()
    }

    companion object {
        fun newInstance() : DonationDialog {
            val frag = DonationDialog()
            return frag
        }
    }
}