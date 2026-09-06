package com.example.treemap

import android.app.AlertDialog
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import java.util.TreeMap

class MainActivity : AppCompatActivity() {

    lateinit var etNombre: EditText
    lateinit var etPp1: EditText
    lateinit var etPp2: EditText
    lateinit var etPp3: EditText
    lateinit var btnGuardar: Button
    lateinit var lvAlumnos: ListView

    val alumnos = TreeMap<String, Estudiante>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etNombre = findViewById(R.id.etNombre)
        etPp1 = findViewById(R.id.etPp1)
        etPp2 = findViewById(R.id.etPp2)
        etPp3 = findViewById(R.id.etPp3)
        btnGuardar = findViewById(R.id.btnGuardar)
        lvAlumnos = findViewById(R.id.lvAlumnos)

        btnGuardar.setOnClickListener {
            guardarAlumno()
        }
    }

    fun guardarAlumno() {
        val nombre = etNombre.text.toString().trim()
        val pp1Texto = etPp1.text.toString().trim()
        val pp2Texto = etPp2.text.toString().trim()
        val pp3Texto = etPp3.text.toString().trim()

        if (nombre.isEmpty() || pp1Texto.isEmpty() || pp2Texto.isEmpty() || pp3Texto.isEmpty()) {
            AlertDialog.Builder(this)
                .setTitle("Aviso")
                .setMessage("Por favor ingrese datos")
                .setPositiveButton("OK", null)
                .show()
            return
        }

        val pp1 = pp1Texto.toDouble()
        val pp2 = pp2Texto.toDouble()
        val pp3 = pp3Texto.toDouble()

        val estudiante = Estudiante(nombre, pp1, pp2, pp3)
        alumnos[nombre.lowercase()] = estudiante

        etNombre.text.clear()
        etPp1.text.clear()
        etPp2.text.clear()
        etPp3.text.clear()

        actualizarLista()
    }

    fun actualizarLista() {
        val datos = alumnos.values.map {
            it.nombre + " - Prom: " + it.promedio() + " - " + it.estado()
        }
        lvAlumnos.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, datos)
    }
}