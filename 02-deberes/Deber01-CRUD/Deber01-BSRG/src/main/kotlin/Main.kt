
import java.util.*
import javax.swing.JOptionPane


fun main(){


    var opcionAccesoCRUD = 0

    while (opcionAccesoCRUD!=2){

        opcionAccesoCRUD = Ventana.crearVentanaPrincipal()
        when(opcionAccesoCRUD){

            (0) -> {
                JOptionPane.showMessageDialog(null, "Estas en Diseño")
            }
            (1) -> {

                var opcionRopa = 0
                while (opcionRopa!=3){
                    opcionRopa = Ventana.crearVentanaPrincipalRopa()

                    when(opcionRopa){
                        (0) -> {
                            var resultado = Ventana.crearVentanaCrearRopa()

                            Ropa.agregarRegistroRopa(Ropa(
                                resultado[0]?.getText().toString(),
                                resultado[1]?.getText().toString().toFloat(),
                                resultado[2]?.getText().toString().toBoolean(),
                                Date(resultado[3]?.getText().toString()),
                                resultado[4]?.getText().toString().toInt()
                            ))

                        }

                        (1)->{
                            var opcionActualizarRopa = Ventana.crearVentanaActualizarRopa()

                            when(opcionActualizarRopa){
                                (0)->{
                                    Ropa.actualizarRegistroRopa(Ventana.obtenerDatosTablaRopa())
                                }
                            }
                        }
                        (2)->{
                            var opcionEliminarRopa = Ventana.crearVentanaEliminarRopa()

                            when(opcionEliminarRopa){
                                (0)->{
                                    Ropa.eliminarRegistroRopa(Ventana.retornarFilaSeleccionadaTablaRopa())
                                }
                            }
                        }
                        else->{
                            break
                        }
                    }
                }
            }else -> {
                JOptionPane.showMessageDialog(null, "Gracias por usar el programa.")
                break
            }
        }
    }




}