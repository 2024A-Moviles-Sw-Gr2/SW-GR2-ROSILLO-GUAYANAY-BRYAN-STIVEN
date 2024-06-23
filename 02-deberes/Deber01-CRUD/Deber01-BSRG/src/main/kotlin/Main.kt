
import java.text.SimpleDateFormat
import java.util.*
import javax.swing.JOptionPane


fun main(){
    var ventanaRopa = VentanaRopa(arrayOf("Nombre", "Precio", "¿Está de moda?", "Lanzamiento", "Vida útil"))
    var ventanaDiseniador = VentanaDiseniador(arrayOf("Nombre", "Valor de mercado", "N° Colecciones", "¿Creador Unisex?", "Ropa creada"))


    var opcionAccesoCRUD = 0

    while (opcionAccesoCRUD!=2){

        opcionAccesoCRUD = Ventana.crearVentanaBienvenida()
        when(opcionAccesoCRUD){

            //OPCIONES CRUD DE DISEÑADOR
            (0) -> {
                var opcionDiseniador = 0
                while (opcionDiseniador!=3){
                    opcionDiseniador = ventanaDiseniador.crearVentanaPrincipal("CRUD de diseñador", Diseniador.obtenerRegistroDiseniador())

                    when(opcionDiseniador){
                        //Crear Diseñador
                        (0)->{
                            var resultado = ventanaDiseniador.crearVentanaCrear()

                            if(resultado.isNotEmpty()){
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

                        }

                        //Actualizar Diseñador
                        (1)->{
                            var opcionActualizarDiseniador = ventanaDiseniador.crearVentanaActualizar("Actualizar diseñador")

                            when(opcionActualizarDiseniador){
                                (0)->{
                                    Diseniador.actualizarRegistroDiseniador(ventanaDiseniador.obtenerDatosTabla())
                                }
                            }
                        }

                        //Eliminar Diseñador
                        (2)->{
                            var opcionEliminarDiseniador = ventanaDiseniador.crearVentanaCrearEliminar("Eliminar diseñador")

                            when(opcionEliminarDiseniador){
                                (0)->{
                                    Diseniador.eliminarRegistroDiseniador(ventanaDiseniador.retornarFilaSeleccionadaTabla());
                                }
                            }

                        }

                        //Roper bucle - Salir
                        else->{
                            break
                        }
                    }
                }
            }

            //OPCIONES CRUD DE ROPA
            (1) -> {
                var opcionRopa = 0
                while (opcionRopa!=3){
                    opcionRopa = ventanaRopa.crearVentanaPrincipal("CRUD de ropa", Ropa.obtenerRegistroRopa())

                    when(opcionRopa){

                        //Crear Ropa
                        (0) -> {
                            var resultado = ventanaRopa.crearVentanaCrear()
                            if(resultado.isNotEmpty()){
                                Ropa.agregarRegistroRopa(Ropa(
                                    resultado[0]?.getText().toString().replace("\n",""),
                                    resultado[1]?.getText().toString().toFloat(),
                                    resultado[2]?.getText().toString().toBoolean(),
                                    Date(resultado[3]?.getText().toString()),
                                    resultado[4]?.getText().toString().toInt()
                                ))
                            }

                        }

                        //Actualizar Ropa
                        (1)->{
                            var opcionActualizarRopa = ventanaRopa.crearVentanaActualizar("Actualizar ropa")

                            when(opcionActualizarRopa){
                                (0)->{
                                    Ropa.actualizarRegistroRopa(ventanaRopa.obtenerDatosTabla())
                                }
                            }
                        }

                        //Eliminar Ropa
                        (2)->{
                            var opcionEliminarRopa = ventanaRopa.crearVentanaCrearEliminar("Eliminar ropa")

                            when(opcionEliminarRopa){
                                (0)->{
                                    Ropa.eliminarRegistroRopa(ventanaRopa.retornarFilaSeleccionadaTabla())
                                }
                            }
                        }

                        //Romper bucle - Salir
                        else->{
                            break
                        }
                    }
                }
            }

            //Romper bucle - SALIR del programa
            else -> {
                JOptionPane.showMessageDialog(null, "Gracias por usar el programa.")
                break
            }
        }
    }




}