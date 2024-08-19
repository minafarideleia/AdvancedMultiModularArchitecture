package com.minafarid.data.result

import kotlinx.coroutines.isActive
import kotlin.coroutines.coroutineContext

// Executes the given onSuccess action if the OutCome is successful and the coroutine is active.
suspend fun <T> OutCome<T>.doOnSuccess(onSuccess: suspend (T) -> Unit): OutCome<T> {
  if (this is OutCome.Success<T>) {
    if (coroutineContext.isActive) {
      onSuccess(this.data)
    }
  }
  return this
}

// Executes the given onEmpty action if the OutCome is empty and the coroutine is active.
suspend fun <T> OutCome<T>.doOnEmpty(onEmpty: suspend () -> Unit): OutCome<T> {
  if (this is OutCome.Empty) {
    if (coroutineContext.isActive) {
      onEmpty()
    }
  }
  return this
}

// Executes the given onError action if the OutCome is not successful.
suspend fun <T> OutCome<T>.doOnError(onError: () -> Unit): OutCome<T> {
  if (!this.isSuccess() && coroutineContext.isActive) {
    onError()
  }
  return this
}

// Transforms the successful OutCome into another type using the provided mapper function.
suspend fun <T, R> OutCome<T>.map(mapper: suspend (T) -> R): OutCome<R> {
  return when (this) {
    is OutCome.Success<T> -> {
      OutCome.success(mapper(this.data))
    }

    is OutCome.Error<T> -> {
      OutCome.error(this.errorMessage())
    }

    is OutCome.Empty<T> -> {
      OutCome.empty()
    }
  }
}

// Merges the current OutCome with another OutCome produced by a lazy function, using a merger function.
suspend fun <F, S, R> OutCome<F>.merge(
  lazy: suspend () -> OutCome<S>,
  merger: (F?, S?) -> R,
): OutCome<R> {
  return when (this) {
    is OutCome.Success<F> -> {
      when (val second = lazy()) {
        is OutCome.Success<S> -> {
          OutCome.success(merger(this.data, second.data))
        }

        is OutCome.Empty<S> -> {
          OutCome.success(merger(this.data, null))
        }

        is OutCome.Error<S> -> {
          OutCome.error(second.errorMessage())
        }
      }
    }

    is OutCome.Error<F> -> {
      OutCome.error(this.errorMessage())
    }

    is OutCome.Empty<F> -> {
      when (val second = lazy()) {
        is OutCome.Success<S> -> {
          OutCome.success(merger(null, second.data))
        }

        is OutCome.Empty<S> -> {
          OutCome.empty()
        }

        is OutCome.Error<S> -> {
          OutCome.error(second.errorMessage())
        }
      }
    }
  }
}
