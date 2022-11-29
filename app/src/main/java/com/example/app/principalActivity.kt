package com.example.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Button
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

class principalActivity : AppCompatActivity() {
    private lateinit var tvEmail:TextView
    private lateinit var tvproveedor:TextView
    private lateinit var btnCerrar: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal)
        tvEmail = findViewById(R.id.tv_email)
        tvproveedor = findViewById(R.id.tv_proveedor)
        btnCerrar = findViewById(R.id.tv_proveedor)

        val bundle:Bundle? = intent.extras
        val email:String? = bundle?.getString("email")
        val proveedor: String? = bundle?.getString("proveedor")
        setup(email?:"", proveedor?:"")

    }
    fun setup(email:String,proveedor:String){
        title = "principal"
        tvEmail.text = email
        tvproveedor.text = proveedor

        btnCerrar.setOnClickListener{
            FirebaseAuth.getInstance().signOut()
            onBackPressed()
        }

    }
}