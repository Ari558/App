package com.example.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import java.net.Authenticator
import java.security.Principal

class MainActivity : AppCompatActivity() {
    private lateinit var etEmail: EditText
    private lateinit var etpass: EditText
    private lateinit var btnregistrar: Button
    private lateinit var btnlogin: Button

    private lateinit var  llAuthenticator: LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        etEmail = findViewById(R.id.et_email)
        etpass = findViewById(R.id.et_pass)
        btnregistrar = findViewById(R.id.btn_registrar)
        btnlogin = findViewById(R.id.btn_login)

        ejecutarAnalitica()
        setup()
    }
    //aqui va lo de firebase
    fun ejecutarAnalitica(){

    }
    fun setup(){
        title = "Autentificacion"
        btnregistrar.setOnClickListener {
            if (etEmail.text.isNotEmpty() && etpass.text.isNotEmpty()) {
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(etEmail.text.toString(), etpass.text.toString())
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            mostrarPrincipal(it?.result?.user?.email?:"", TiposProveedor.BASICO)

                        } else {
                            mostrarAlerta()
                        }
                    }
            }

        }
        btnlogin.setOnClickListener{
            if (etEmail.text.isNotEmpty() && etpass.text.isNotEmpty()) {
                FirebaseAuth.getInstance().signInWithEmailAndPassword(etEmail.text.toString(), etpass.text.toString())
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            mostrarPrincipal(it?.result?.user?.email?:"", TiposProveedor.BASICO)

                        } else {
                            mostrarAlerta()
                        }
                    }
            }

        }
    }
    fun mostrarAlerta(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error de conexion")
        builder.setMessage("se ha producido un error de autentificacion complete")
        builder.setPositiveButton("Aceptar",null)
        val dialog = builder.create()
    }
    fun mostrarPrincipal(email:String,proveedor:TiposProveedor){
        val ventana: Intent = Intent(this, principalActivity::class.java).apply {
            putExtra("email",email)
            putExtra("proveedor",proveedor)
        }
        startActivity(ventana)
    }

    enum class TiposProveedor{
        BASICO
    }
}