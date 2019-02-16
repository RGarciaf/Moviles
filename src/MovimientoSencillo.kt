import es.uam.eps.multij.Movimiento

class MovimientoSencillo(var posicion: Int) : Movimiento() {
    override fun equals(other: Any?): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun toString() = posicion.toString()
}