package cl.talentodigital.superrepaso.registro.data.remote

import cl.talentodigital.superrepaso.registro.domain.RegistroRepository
import cl.talentodigital.superrepaso.registro.domain.RegistroUsuario
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.tasks.await

class FirebaseRegistroRepository(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseDatabase: FirebaseDatabase
) : RegistroRepository {

    override suspend fun registrarUsuario(registroUsuario: RegistroUsuario): Boolean {
        val result = crearUsuarioEnFirebase(registroUsuario.email, registroUsuario.pass)
        guardarNombreEnFirebase(registroUsuario.nombre)
        guardaValoresEnBaseDeDatosFirebase(registroUsuario.nombre, registroUsuario.rut, registroUsuario.email)
        return result.user?.email?.isNotEmpty() ?: false
    }

    private suspend fun guardaValoresEnBaseDeDatosFirebase(nombre: String, rut: String, email: String) {
        val dataBase = firebaseDatabase.getReference("usuarios/${rut}")
        val registroUsuarioFirebase = RegistroUsuarioFirebase(
            nombre,
            rut,
            email
        )
        dataBase.setValue(registroUsuarioFirebase).await()
    }

    private suspend fun crearUsuarioEnFirebase(email: String, pass: String) = firebaseAuth
        .createUserWithEmailAndPassword(email, pass)
        .await()

    private suspend fun guardarNombreEnFirebase(nombre: String) {
        val user = firebaseAuth.currentUser
        val profileUpdates = UserProfileChangeRequest.Builder()
            .setDisplayName(nombre)
            .build()
        user?.updateProfile(profileUpdates)?.await()
    }

}