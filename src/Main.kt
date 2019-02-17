import es.uam.eps.multij.Jugador
import es.uam.eps.multij.JugadorAleatorio
import es.uam.eps.multij.Partida
import es.uam.eps.multij.Tablero
import java.io.*
import java.lang.System.exit

fun main(args: Array<String>) {

    val tablero = TableroComplejo()
    val observer = ObservadorComplejo()
    var listaJugadores : ArrayList<Jugador> = arrayListOf<Jugador>()
    listaJugadores.add(JugadorColumna0("Jugador 1"))
    listaJugadores.add(JugadorColumna1("Jugador 2"))
    tablero.stringToTablero(null)
    val partida = Partida(tablero, listaJugadores)
    partida.addObservador(observer)
    partida.continuar()
//    tablero.tableroToString()


//    var file = File("tablero.dat").readLines()
//    var trozo = file[1].split(",")
//    for (t in trozo) println(t)
}