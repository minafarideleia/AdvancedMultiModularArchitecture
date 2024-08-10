package com.minafarid.data.source

import com.google.gson.Gson
import com.minafarid.data.connectivity.NetworkMonitorInterface

class NetworkDataSource<SERVICE>(
    private val service: SERVICE,
    private val gson: Gson,
    private val networkMonitorInterface: NetworkMonitorInterface,
    private val userIdProvider: () -> String
)