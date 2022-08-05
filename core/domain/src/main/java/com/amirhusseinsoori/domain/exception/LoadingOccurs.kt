package com.amirhusseinsoori.domain.exception

sealed class LoadingOccurs {
    object StartLoading : LoadingOccurs()
    object FinishLoading : LoadingOccurs()
}
