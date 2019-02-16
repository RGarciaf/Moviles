import es.uam.eps.multij.AccionMover;
import es.uam.eps.multij.Evento;
import es.uam.eps.multij.Jugador;
import es.uam.eps.multij.Tablero;

/**
 *
 * @author mfreire
 */
public class JugadorColumna0 implements Jugador {

    private String nombre;
    private static int numAleatorios = 0;

    /** Constructor por defecto */
    public JugadorColumna0() {
        this("Aleatorio "+ (++numAleatorios));
    }

    /** Creates a new instance of JugadorColumna0 */
    public JugadorColumna0(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Recibe una notificacion de un cambio en la partida
     */
    public void onCambioEnPartida(Evento evento) {
        switch (evento.getTipo()) {
            case Evento.EVENTO_FIN:
            case Evento.EVENTO_CAMBIO:
                break;

            case Evento.EVENTO_CONFIRMA:
                // este jugador confirma al azar
                try {
                    evento.getPartida().confirmaAccion(
                            this, evento.getCausa(), (Math.random() > .5));
                }
                catch(Exception e) {

                }
                break;

            case Evento.EVENTO_TURNO:
                // jugar al azar, que gran idea
                Tablero t = evento.getPartida().getTablero();
                int r = (int)(Math.random() * t.movimientosValidos().size());
                try {
                    evento.getPartida().realizaAccion(new AccionMover(
                            this, t.movimientosValidos().get(0)));
                }
                catch(Exception e) {

                }
                break;
        }
    }

    /**
     * Devuelve el nombre del jugador
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Este jugador juega *todos* los juegos
     */
    public boolean puedeJugar(Tablero tablero)  {
        return true;
    }
}
