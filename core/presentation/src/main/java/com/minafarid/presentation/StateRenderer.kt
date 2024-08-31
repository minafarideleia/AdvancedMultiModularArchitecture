package com.minafarid.presentation

import androidx.annotation.StringRes
import com.minafarid.domain.model.ErrorMessage

sealed class StateRenderer<out S, O> {
    // S for ViewState and O for Output

    // Content State
    class ScreenContent<S, O>(val viewState: S) : StateRenderer<S, O>()

    // Loading States
    // 1- Popup Loading State
    data class LoadingPopup<S, O>(
        val viewState: S,
        @StringRes val loadingMessage: Int = R.string.loading
    ) : StateRenderer<S, O>()


    // 2- Full Screen Loading State
    data class FullScreenLoading<S, O>(
        val viewState: S,
        @StringRes val loadingMessage: Int = R.string.loading
    ) : StateRenderer<S, O>()

    // Error States
    // 1- Popup Error State
    data class ErrorPopup<S, O>(
        val viewState: S,
        val errorMessage: ErrorMessage
    ) : StateRenderer<S, O>()

    // 2- Full Screen Error State
    data class ErrorFullScreenPopup<S, O>(
        val viewState: S,
        val errorMessage: ErrorMessage
    ) : StateRenderer<S, O>()

    // Empty State
    data class Empty<S, O>(
        val viewState: S,
        @StringRes val emptyMessage: Int = R.string.no_data
    ) : StateRenderer<S, O>()

    // Success State
    data class Success<S, O>(val output: O) : StateRenderer<S, O>()


}