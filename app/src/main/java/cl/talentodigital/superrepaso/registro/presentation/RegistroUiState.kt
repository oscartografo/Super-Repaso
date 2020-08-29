package cl.talentodigital.superrepaso.registro.presentation

sealed class RegistroUiState(
    open val result: Boolean? = null,
    open val error: Throwable? = null
) {
    object LoadingRegistroUiState : RegistroUiState()
    object SuccessRegistroUiState : RegistroUiState(result = true)
    object InvalidEmailRegistroUiState : RegistroUiState(result = false)
    data class ErrorRegistroUiState(override val error: Throwable) : RegistroUiState(error = error)
}
