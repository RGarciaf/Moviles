import es.uam.eps.multij.Evento
import es.uam.eps.multij.PartidaListener

class ObservadorSencillo : PartidaListener {
    override fun onCambioEnPartida(evento: Evento?) {
        when(evento?.tipo) {
            Evento.EVENTO_CAMBIO -> {
                val turno = evento.partida.tablero.turno
                val nombre = evento.partida.getJugador(turno).nombre
                println(evento.descripcion)
                println("El jugador $nombre ha movido")
                println(evento.partida.tablero.toString())
                //Guardar partida
            }
        } // IMPLEMENTAR TODOS LOS ESTADOS
    }

}