import es.uam.eps.multij.ExcepcionJuego
import es.uam.eps.multij.Movimiento
import es.uam.eps.multij.Tablero
import java.util.ArrayList

class TableroSencillo(var dim : Int): Tablero() {
    var tablero = arrayListOf<Int>()
    var tableroGuardado : String = ""

    init {
        for (i in 0 until dim) {
            tablero.add(0)
        }

        estado = Tablero.EN_CURSO
    }

    override fun toString(): String {

        var cadena : String = ""
        for (i in 0 until dim)
            cadena = "$cadena${tablero[i]} "

        return cadena
    }

    override fun movimientosValidos(): ArrayList<Movimiento> {
        var movs = arrayListOf<Movimiento>()

        for (i in 0 until dim) {
            if(esValido(MovimientoSencillo(i))) {
                movs.add(MovimientoSencillo((i)))
            }
        }

        return movs
    }

    override fun stringToTablero(cadena: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun mueve(m: Movimiento?) {
        if (esValido(m) == true) {
            tablero[m?.toString()?.toInt()!!] = turno +1
            ultimoMovimiento = m
            cambiaTurno()
            //Actualizar Estado
            if(tablero[0] != 0) {
                estado = Tablero.FINALIZADA
            }
        } else {
            throw ExcepcionJuego("Movimiento inválido")
        }
    }

    override fun esValido(m: Movimiento?): Boolean {
        val posicion = m.toString().toInt()

        if(tablero[posicion] == 0)
            return true
        else
            return false
    }

    override fun tableroToString(): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}