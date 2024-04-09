package com.example.meteortracker.util

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
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
        builder.setTitle("No Network Connection")
            .setMessage("Please check your network connection and try again.")
            .setNeutralButton("Cancel") { dialog, _ ->
                dialog.dismiss()
                activity.finish()
            }
            .setPositiveButton("Retry") { dialog, _ ->
                dialog.dismiss()
                activity.recreate()
            }
        val dialog = builder.create()
        dialog.show()
    }
}
