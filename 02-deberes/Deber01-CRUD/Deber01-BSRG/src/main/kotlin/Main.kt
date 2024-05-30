
import java.text.SimpleDateFormat
import java.util.*
import javax.swing.JOptionPane


fun main(){
    var ventanaRopa:VentanaRopa
    var opcionAccesoCRUD = 0

    while (opcionAccesoCRUD!=2){

        opcionAccesoCRUD = Ventana.crearVentanaBienvenida()
        when(opcionAccesoCRUD){

            (0) -> {
                var opcionDiseniador = 0
                while (opcionDiseniador!=3){
                    opcionDiseniador = Ventana.crearVentanaPrincipalDiseniador()

                    when(opcionDiseniador){
                        (0)->{
                            var resultado = Ventana.crearVentanaCrearDiseniador()
                            var diseniador = Diseniador(
                                resultado[0]?.getText().toString(),
                                resultado[1]?.getText().toString().toLong(),
                                resultado[2]?.getText().toString().toInt(),
                                resultado[3]?.getText().toBoolean()
                            )

                            var formatoFecha = SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ROOT)


                            var registrosRopa = Ropa.obtenerRegistroRopa()
                            for(i in 0..resultado[4]?.getText().toString().length-1){
                                for(j in 0..registrosRopa.size-1){
                                    if(resultado[4]?.getText().toString()[i].toString().toInt() == j){
                                        var ropa = Ropa(
                                            registrosRopa[j][0].replace("\n",""),
                                            registrosRopa[j][1].toFloat(),
                                            registrosRopa[j][2].toBoolean(),
                                            formatoFecha.parse(registrosRopa[j][3]),
                                            registrosRopa[j][4].toInt()
                                        )
                                        diseniador.ropa.add(ropa)
                                    }
                                }
                            }

                            Diseniador.agregarRegistroDiseniador(diseniador)

                        }
                        (1)->{
                            var opcionActualizarDiseniador = Ventana.crearVentanaActualizarDiseniador()

                            when(opcionActualizarDiseniador){
                                (0)->{
                                    Diseniador.actualizarRegistroDiseniador(Ventana.obtenerDatosTablaDiseniador())
                                }
                            }
                        }
                        (2)->{
                            var opcionEliminarDiseniador = Ventana.crearVentanaEliminarDiseniador()

                            when(opcionEliminarDiseniador){
                                (0)->{
                                    Diseniador.eliminarRegistroDiseniador(Ventana.retornarFilaSeleccionadaTablaDiseniador());
                                }
                            }

                        }

                        else->{
                            break
                        }
                    }
                }
            }
            (1) -> {

                var opcionRopa = 0
                while (opcionRopa!=3){
                    opcionRopa = Ventana.crearVentanaPrincipalRopa()

                    when(opcionRopa){
                        (0) -> {
                            var resultado = Ventana.crearVentanaCrearRopa()

                            Ropa.agregarRegistroRopa(Ropa(
                                resultado[0]?.getText().toString().replace("\n",""),
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
            }
            else -> {
                JOptionPane.showMessageDialog(null, "Gracias por usar el programa.")
                break
            }
        }
    }




}