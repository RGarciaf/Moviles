import es.uam.eps.multij.Evento
import es.uam.eps.multij.PartidaListener

class ObservadorConecta4 : PartidaListener {
    override fun onCambioEnPartida(evento: Evento?) {
        when(evento?.tipo) {
            Evento.EVENTO_CAMBIO -> {
                val turno = evento.partida.tablero.turno
                val nombre = evento.partida.getJugador(turno).nombre
                println("Evento Cambio: ${evento.descripcion}")
//                println("Observer: El jugador $nombre ha movido\n")
                println("${evento.partida.tablero.toString()}\n")
                //Guardar partida
            }

            Evento.EVENTO_FIN -> {
                val turno = evento.partida.tablero.turno
                val nombre = evento.partida.getJugador(turno).nombre
                println(evento.descripcion)
                println(evento.partida.tablero.toString())
//                println("El jugador $nombre ha ganado")
            }

            Evento.EVENTO_TURNO -> {
                val turno = evento.partida.tablero.turno
                val nombre = evento.partida.getJugador(turno).nombre
                println("Evento Turno: ${evento.descripcion}")
//                println("Observer: El jugador $nombre ha movido\n")
            }
        } // IMPLEMENTAR TODOS LOS ESTADOS
    }

}