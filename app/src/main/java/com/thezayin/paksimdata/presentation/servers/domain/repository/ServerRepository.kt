package com.thezayin.paksimdata.presentation.servers.domain.repository

import com.thezayin.framework.utils.Response
import com.thezayin.paksimdata.presentation.servers.domain.model.ServerList
import kotlinx.coroutines.flow.Flow

interface ServerRepository {
    fun getServerList(): Flow<Response<List<ServerList>>>
}