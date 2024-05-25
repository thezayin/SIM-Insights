package com.thezayin.paksimdata.domain.usecase

import com.thezayin.framework.utils.Response
import com.thezayin.paksimdata.domain.repository.SimRepository
import kotlinx.coroutines.flow.Flow

interface RemoteUseCase : suspend (String) -> Flow<Response<String>>
class RemoteUseCaseImpl(
    private val repository: SimRepository
) : RemoteUseCase {
    override suspend fun invoke(number: String): Flow<Response<String>> =
        repository.searchNumber(number)
}