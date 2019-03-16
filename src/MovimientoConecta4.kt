import es.uam.eps.multij.Movimiento

class MovimientoConecta4(var posicion: Int) : Movimiento() {
    override fun equals(other: Any?): Boolean {
        return if (other is MovimientoConecta4)
            other.posicion == posicion
        else false
    }

    override fun toString() = posicion.toString()
}