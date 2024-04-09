package com.example.meteortracker.util

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
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

    @Composable
    fun showNoNetworkDialog(activity: Activity) {
        val builder = AlertDialog.Builder(activity)
        builder.setTitle(stringResource(id = R.string.no_network))
            .setMessage(stringResource(id = R.string.check_connection))
            .setNeutralButton(stringResource(id = R.string.close)) { dialog, _ ->
                dialog.dismiss()
                activity.finish()
            }
            .setPositiveButton(stringResource(id = R.string.retry)) { dialog, _ ->
                dialog.dismiss()
                activity.recreate()
            }
        val dialog = builder.create()
        dialog.show()
    }
}
