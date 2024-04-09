package com.example.meteortracker.util

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.example.meteortracker.R
import javax.inject.Inject

class NetworkUtil @Inject constructor() {

    fun isInternetAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork
        val capabilities = connectivityManager.getNetworkCapabilities(network)
        return capabilities != null &&
                (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR))
    }

    fun showNoNetworkDialog(activity: Activity) {
        val builder = AlertDialog.Builder(activity)
        builder.setTitle(activity.getString(R.string.no_network))
            .setMessage(activity.getString(R.string.check_connection))
            .setNeutralButton(activity.getString(R.string.close)) { dialog, _ ->
                dialog.dismiss()
                activity.finish()
            }
            .setPositiveButton(activity.getString(R.string.retry)) { dialog, _ ->
                dialog.dismiss()
                activity.recreate()
            }
        val dialog = builder.create()
        dialog.show()
    }
}
