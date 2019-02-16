import es.uam.eps.multij.Jugador
import es.uam.eps.multij.JugadorAleatorio
import es.uam.eps.multij.Partida

fun main(args: Array<String>) {
    var listaJugadores : ArrayList<Jugador> = arrayListOf<Jugador>()
    listaJugadores.add(JugadorColumna0("Jugador 1"))
    listaJugadores.add(JugadorColumna1("Jugador 2"))

    val partida = Partida(TableroComplejo(), listaJugadores)
//    print(partida.tablero.toString())
    partida.addObservador(ObservadorSencillo())
    partida.comenzar()

}