import es.uam.eps.multij.ExcepcionJuego
import es.uam.eps.multij.Movimiento
import es.uam.eps.multij.Tablero
import java.util.*
import java.io.*
import kotlin.collections.ArrayList

class TableroConecta4(var rows : Int = 6, var cols: Int = 7): Tablero()  {

    var tablero = mutableListOf<Stack<Int>>()
//    var tableroGuardado : String = ""
//    var cont = 0

    init {
        for (i in 0 until this.cols) {
            tablero.add(Stack())
//            for (j in 0 until this.rows) tablero[i].push(0)

        }

        estado = EN_CURSO

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

                    if (nfichas == 4) return true

                } else  nfichas = 0
            }
        }

        return false
    }

    override fun esValido(m: Movimiento?): Boolean {
        return tablero[m.toString().toInt()].size < rows
    }

    override fun tableroToString(): String {
        try{
            val file = File("tablero.dat")
            var row : String = ""

            var cadena = "$cols,$rows,$turno,$estado,$numJugadas,$numJugadores,$ultimoMovimiento"

            for (i in 0 until cols) {
                for (j in 0 until tablero[i].size) {
                    if(j == 0) row = "${tablero[i][j]}"
                    else row = "$row,${tablero[i][j]}"
                }

                cadena = "$cadena\n$row"
                row = ""
            }

            file.writeText(cadena)

            return cadena

        } catch (e: FileNotFoundException) {
            println("Fichero no encontrado. Prueba otra vez")
        }

        return ""
    }

    override fun stringToTablero(cadena: String?) {
        try{
            val file = File("tablero.dat").readLines()
            var linea = file[0]
            var trozos = linea.split(",")
            this.cols = trozos.get(0).toInt()
            this.rows = trozos.get(1).toInt()
            this.turno = trozos.get(2).toInt()
            this.estado = trozos.get(3).toInt()
            this.numJugadas = trozos.get(4).toInt()
            this.numJugadores = trozos.get(5).toInt()
            this.ultimoMovimiento = MovimientoConecta4(trozos.get(6).toInt())

            tablero = mutableListOf<Stack<Int>>()
            for (i in 0 until this.cols) {
                tablero.add(Stack())
            }

            for (i in 0 until file.size -1){
                trozos = file[i+1].split(",")
                for (ficha in trozos) tablero[i].push(ficha.toInt())
            }

        } catch (e: FileNotFoundException) {
            println("Fichero no encontrado. Prueba otra vez")
        }
    }

}