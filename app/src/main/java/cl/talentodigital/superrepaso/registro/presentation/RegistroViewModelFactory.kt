package cl.talentodigital.superrepaso.registro.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import cl.talentodigital.superrepaso.registro.domain.RegistraUsuarioUseCase

class RegistroViewModelFactory(
    private val registrarUsuarioUseCase: RegistraUsuarioUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(RegistraUsuarioUseCase::class.java)
            .newInstance(registrarUsuarioUseCase)
    }
}