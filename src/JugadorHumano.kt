import es.uam.eps.multij.*

/**
 *
 * @author mfreire
 */
class JugadorHumano
/** Creates a new instance of JugadorColumna0  */
@JvmOverloads constructor(private val nombre: String = "Aleatorio " + ++numHumanos) : Jugador {

    /**
     * Recibe una notificacion de un cambio en la partida
     */
    override fun onCambioEnPartida(evento: Evento) {
        when (evento.tipo) {
            Evento.EVENTO_FIN, Evento.EVENTO_CAMBIO, Evento.EVENTO_CONFIRMA -> {
            }

            Evento.EVENTO_TURNO -> {
                // jugar al azar, que gran idea
                val t = evento.partida.tablero
                println("Te toca mover, ${evento.partida.getJugador(t.turno).nombre}")

                try {
                    var input = readLine()?.toIntOrNull() ?: -999
                    while (!t.esValido(MovimientoConecta4(input)) && !t.movimientosValidos().isEmpty()){
                        println("Movimiento no permitido")
                        input = readLine()?.toIntOrNull() ?: -999
                    }
                    evento.partida.realizaAccion(
                        AccionMover(
                            this, MovimientoConecta4(input)
                        )
                    )
                } catch (e: ExcepcionJuego) {

                }


            }
        }
    }

    /**
     * Devuelve el nombre del jugador
     */
    override fun getNombre(): String {
        return nombre
    }

    /**
     * Este jugador juega *todos* los juegos
     */
    override fun puedeJugar(tablero: Tablero): Boolean {
        return true
    }

    companion object {
        private var numHumanos = 0
    }
}
/** Constructor por defecto  */
