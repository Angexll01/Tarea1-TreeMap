package com.example.treemap

data class Estudiante(
    val nombre: String,
    val pp1: Double,
    val pp2: Double,
    val pp3: Double
) {
    fun promedio(): Double {
        return (pp1 + pp2 + pp3) / 3
    }

    fun estado(): String {
        val prom = promedio()
        return when {
            prom >= 13 -> "Aprobado"
            prom >= 10 -> "Sustitutorio"
            else -> "Desaprobado"
        }
    }
}