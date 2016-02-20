package com.kai.gwtwohot

import android.app.Application
import com.paypal.android.sdk.payments.PayPalConfiguration

/**
 * Created by ikraammoothianpillay1 on 2/20/16.
 */

class GuildWarsApplication : Application() {
    var daoSession: DaoSession? = null

    override fun onCreate() {
        super.onCreate()
        setupDatabase()
    }

    private fun setupDatabase() {
        val helper = DaoMaster.DevOpenHelper(this, "com.kai.gwtwohot", null)
        val db = helper.writableDatabase
        val daoMaster = DaoMaster(db)
        daoSession = daoMaster.newSession()
    }

    companion object {
        // Start with mock environment.  When ready, switch to sandbox (ENVIRONMENT_SANDBOX)
        // or live (ENVIRONMENT_PRODUCTION)
        var paypalConfig = PayPalConfiguration()
                .environment(PayPalConfiguration.ENVIRONMENT_PRODUCTION)
                .clientId("Ad7eizksgAxqR7ZJSAe_JmSI-6HNSpq0XIHf5OWijB9wuJXB5UyGRBwG3iCgGlRsTLp8m-U6gDc9lN6i")
    }
}
