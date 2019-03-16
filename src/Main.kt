import es.uam.eps.multij.Jugador
import es.uam.eps.multij.Partida

fun main(args: Array<String>) {

    val tablero = TableroConecta4()
    val observer = ObservadorConecta4()
    var listaJugadores : ArrayList<Jugador> = arrayListOf<Jugador>()
    listaJugadores.add(JugadorColumna0("Columna 0"))
    listaJugadores.add(JugadorColumna1("Columna 1"))
//    tablero.stringToTablero(null)
    val partida = Partida(tablero, listaJugadores)
    partida.addObservador(observer)
    partida.comenzar()
//    Descomentar la siguiente linea para guardar la partida y cambiar
//    partida.continuar() por partida.comenzar()
//    tablero.tableroToString()

}