package com.amirhusseinsoori.grpckotlin.domain.exception

sealed class LoadingOccurs {
    object StartLoading : LoadingOccurs()
    object FinishLoading : LoadingOccurs()
}
