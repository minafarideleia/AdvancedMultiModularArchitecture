package com.minafarid.presentation

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import com.minafarid.domain.model.ErrorMessage
import com.minafarid.presentation.views.renderEmptyScreen
import com.minafarid.presentation.views.renderErrorFullScreen
import com.minafarid.presentation.views.renderErrorPopup
import com.minafarid.presentation.views.renderLoadingFullScreen
import com.minafarid.presentation.views.renderLoadingPopup

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
    data class LoadingFullScreen<S, O>(
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
    data class ErrorFullScreen<S, O>(
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

    // ScreenContent

    @Composable
    fun onUiState(action: @Composable (S) -> Unit): StateRenderer<S, O> {
        if (this is ScreenContent) {
            action(viewState)
        }
        return this
    }

    @Composable
    fun onLoadingState(action: @Composable (S) -> Unit): StateRenderer<S, O> {
        if (this is LoadingPopup) {
            action(viewState)
        } else if (this is LoadingFullScreen) {
            action(viewState)
        }
        return this
    }

    fun onSuccess(action: (O) -> Unit): StateRenderer<S, O> {
        if (this is Success) {
            action(output)
        }
        return this
    }

    @Composable
    fun onErrorState(action: @Composable (S) -> Unit): StateRenderer<S, O> {
        if (this is ErrorPopup) {
            action(viewState)
        } else if (this is ErrorFullScreen) {
            action(viewState)
        }
        return this
    }

    fun onEmpty(action: () -> Unit): StateRenderer<S, O> {
        if (this is Empty) {
            action()
        }
        return this
    }

    companion object {
        @Composable
        fun <S, O> of(
            retryAction: () -> Unit = {},
            statRenderer: StateRenderer<S, O>,
            blocK: @Composable StateRenderer<S, O>.() -> Unit
        ): StateRenderer<S, O> {
            statRenderer.blocK() // show this first before doing any thing

            when (statRenderer) {
                is Empty -> renderEmptyScreen(statRenderer.emptyMessage)
                is ErrorFullScreen -> renderErrorFullScreen(statRenderer.errorMessage, retryAction)
                is ErrorPopup -> renderErrorPopup(statRenderer.errorMessage, retryAction)
                is LoadingFullScreen -> renderLoadingFullScreen(statRenderer.loadingMessage)
                is LoadingPopup -> renderLoadingPopup(statRenderer.loadingMessage)
                else -> {}
            }
            return statRenderer
        }

    }
}