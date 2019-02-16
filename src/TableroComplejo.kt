import es.uam.eps.multij.ExcepcionJuego
import es.uam.eps.multij.Movimiento
import es.uam.eps.multij.Tablero
import java.util.*
import kotlin.collections.ArrayList

class TableroComplejo(var rows : Int = 6, var cols: Int = 7): Tablero() {

    var tablero = mutableListOf<Stack<Int>>()
    var tableroGuardado : String = ""
//    var cont = 0

    init {
        for (i in 0 until this.cols) {
            tablero.add(Stack())
//            for (j in 0 until this.rows) tablero[i].push(0)

        }

        this.estado = EN_CURSO
    }

    override fun toString(): String {

        var cadena : String = ""
        var row : String = ""

        for (i in rows -1 downTo 0) {
            for (j in 0 until cols) {
                if (tablero[j].size > i)
                    row = "$row ${tablero[j][i]}"
                else row = "$row 0"
            }

            cadena = "$cadena\n$row"
            row = ""
        }

        return cadena
    }

    override fun movimientosValidos(): ArrayList<Movimiento> {
        val movs = arrayListOf<Movimiento>()

        for (i in 0 until cols) {
            if(this.esValido(MovimientoSencillo(i))) {
                movs.add(MovimientoSencillo((i)))
            }
        }

        return movs
    }

    override fun stringToTablero(cadena: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun mueve(m: Movimiento?) {
        if (esValido(m)) {
            val pos = m?.toString()?.toInt()!!

            tablero[pos].push(turno + 1)

            ultimoMovimiento = m
            cambiaTurno()

            if(terminado()) estado = FINALIZADA
            else if (movimientosValidos().isEmpty()) estado = TABLAS

        } else {
            throw ExcepcionJuego("Movimiento inválido")
        }
    }

    fun terminado (): Boolean {
        return compruebaCols() || compruebaRows()
    }

    fun compruebaCols(): Boolean {
        var jugador = 1
        var nfichas = 0

        for (col in tablero){
            for (ficha in col){
                if (ficha != 0)
                    if (ficha == jugador) nfichas ++
                    else nfichas = 0; jugador = ficha

                    if (nfichas == 4){
                        return true
                    }
            }
        }
        return false
    }

    fun compruebaRows(): Boolean {
        var jugador = 1
        var nfichas = 0

        for (i in rows -1 downTo 0) {
            for (j in 0 until cols) {
                if (tablero[j].size > i) {
                    if (tablero[j][i] != 0) {
                        if (tablero[j][i] == jugador) nfichas++
                        else nfichas = 0; jugador = tablero[j][i]
                    }

                    if (nfichas == 4) {
                        return true
                    }
                } else { nfichas = 0 }
            }
        }
        return false
    }

    override fun esValido(m: Movimiento?): Boolean {
        return tablero[m.toString().toInt()].size < rows

//        val posicion = m.toString().toInt()
//        return tablero[posicion].contains(0)

//        if(tablero[posicion].contains(0) == true)
//            return true
//        else
//            return false
    }

    override fun tableroToString(): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}