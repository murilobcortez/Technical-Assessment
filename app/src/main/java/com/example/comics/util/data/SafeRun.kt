package com.example.comics.util.data

import retrofit2.Response
import java.io.IOException
import java.net.SocketTimeoutException
import kotlinx.coroutines.ensureActive
import kotlin.coroutines.coroutineContext
import com.example.comics.util.domain.DataError
import com.example.comics.util.domain.Result

/**
 * Executa uma chamada de rede de forma segura, convertendo exceções e respostas
 * em um Result padronizado.
 *
 * - Trata erros comuns de rede (timeout, conexão)
 * - Verifica se a corrotina ainda está ativa para cancelamentos
 * - Delega o processamento da resposta para responseToResult()
 *
 * @param execute Lambda com a chamada Retrofit a ser executada
 * @return Result contendo dados ou erro mapeado
 */
suspend inline fun <reified T> safeRun(
    crossinline execute: suspend () -> Response<T>
): Result<T, DataError.Remote> {
    val response = try {
        execute()
    } catch (e: SocketTimeoutException) {
        return Result.Error(DataError.Remote.REQUEST_TIMEOUT)
    } catch (e: IOException) {
        return Result.Error(DataError.Remote.NETWORK)
    } catch (e: Exception) {
        coroutineContext.ensureActive()
        return Result.Error(DataError.Remote.UNKNOWN)
    }

    return responseToResult(response)
}

/**
 * Converte uma resposta Retrofit em um Result padronizado.
 *
 * - Verifica códigos HTTP específicos (408, 429, 5xx)
 * - Trata corpo de resposta vazio como erro de serialização
 * - Cobre todos os outros casos com erro genérico
 *
 * @param response Resposta HTTP do Retrofit
 * @return Result com dados do corpo ou erro mapeado
 */
suspend inline fun <reified T> responseToResult(
    response: Response<T>
): Result<T, DataError.Remote> {
    return when {
        response.isSuccessful -> {
            val body = response.body()
            if (body != null) {
                Result.Success(body)
            } else {
                Result.Error(DataError.Remote.SERIALIZATION)
            }
        }
        response.code() == 408 -> Result.Error(DataError.Remote.REQUEST_TIMEOUT)
        response.code() == 429 -> Result.Error(DataError.Remote.TOO_MANY_REQUESTS)
        response.code() in 500..599 -> Result.Error(DataError.Remote.SERVER)
        else -> Result.Error(DataError.Remote.UNKNOWN)
    }
}
