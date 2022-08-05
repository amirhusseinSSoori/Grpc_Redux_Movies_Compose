package com.amirhusseinsoori.domain.usecase.base

abstract class UseCaseWithParamsImmediate<in Params, out R> {
    protected abstract suspend fun buildUseCaseImmediate(params: Params) : R
    suspend fun execute(params: Params): R = buildUseCaseImmediate(params)
}