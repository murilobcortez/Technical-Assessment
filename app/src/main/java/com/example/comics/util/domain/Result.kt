package com.example.comics.util.domain

// Result é um sealed interface que representa o resultado de uma operação,
// podendo ser sucesso (Success) ou erro (Error).
sealed interface Result<out D, out E: Error> {
    data class Success<out D>(val data: D): Result<D, Nothing>
    data class Error<out E: com.example.comics.util.domain.Error>(val error: E):
        Result<Nothing, E>
}

/**
 * Transforma o dado de um Result.Success usando a função [map].
 * Se for um Result.Error, apenas propaga o erro.
 *
 * @param map Função que transforma o dado de sucesso.
 * @return Um novo Result com o dado transformado ou o erro original.
 */
inline fun <T, E: Error, R> Result<T, E>.map(map: (T) -> R): Result<R, E> {
    return when(this) {
        is Result.Error -> Result.Error(error)
        is Result.Success -> Result.Success(map(data))
    }
}

/**
 * Executa uma ação caso o resultado seja Success.
 * Não altera o Result, apenas executa o side-effect.
 *
 * @param action Função a ser executada com o dado de sucesso.
 * @return O mesmo Result original, para permitir encadeamento.
 */
inline fun <T, E: Error> Result<T, E>.onSuccess(action: (T) -> Unit): Result<T, E> {
    return when(this) {
        is Result.Error -> this
        is Result.Success -> {
            action(data)
            this
        }
    }
}

/**
 * Executa uma ação caso o resultado seja Error.
 * Não altera o Result, apenas executa o side-effect.
 *
 * @param action Função a ser executada com o erro.
 * @return O mesmo Result original, para permitir encadeamento.
 */
inline fun <T, E: Error> Result<T, E>.onError(action: (E) -> Unit): Result<T, E> {
    return when(this) {
        is Result.Error -> {
            action(error)
            this
        }
        is Result.Success -> this
    }
}

// Typealias para um Result que não retorna dado em caso de sucesso (usa Unit).
typealias EmptyResult<E> = Result<Unit, E>