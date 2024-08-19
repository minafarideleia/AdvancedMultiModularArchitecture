package com.minafarid.data.connectivity

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

class NetworkMonitorImplementer(context: Context) : NetworkMonitorInterface {

  private val connectivityManager: ConnectivityManager =
    context.getSystemService(Context.CONNECTIVITY_SERVICE)
      as ConnectivityManager

  override fun hasConnectivity(): Boolean {
    return connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
      ?.let { networkCapabilities ->
        listOf(
          NetworkCapabilities.TRANSPORT_WIFI,
          NetworkCapabilities.TRANSPORT_CELLULAR,
          NetworkCapabilities.TRANSPORT_ETHERNET,
        ).any { networkCapabilities.hasTransport(it) }
      } ?: false
  }
}
