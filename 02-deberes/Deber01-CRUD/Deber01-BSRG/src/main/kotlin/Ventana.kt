import java.awt.BorderLayout
import java.awt.Dimension
import java.util.StringTokenizer
import javax.swing.*

abstract class Ventana {

    /*
    abstract fun crearVentana()
    abstract fun crearVentanaCrear()
    abstract fun crearVentanaActualizar()
    abstract fun crearVentanaCrearEliminar()
    */
    
    
    companion object{
        //Para la creación de la ventana principal
        fun crearVentanaBienvenida(): Int{
            return JOptionPane.showOptionDialog(
                null,
                "PROGRAMA CRUD BÁSICO\n¿Desea entrar a las opciones de CRUD de...?",
                "Bienvenido",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                arrayOf("Diseñador", "Ropa", "SALIR"),
                null
            )
        }

        var panelGeneralRopa = JPanel(BorderLayout())
        var tablaRopa:JTable = JTable()
        var tablaDiseniador:JTable = JTable()
        var panelGeneralDiseniador = JPanel(BorderLayout())

        

        //Ventanas para la administración de ROPA
        fun crearVentanaPrincipalRopa():Int{
            var columnas = arrayOf("Nombre", "Precio", "¿Está de moda?", "Lanzamiento", "Vida útil")
            tablaRopa = JTable(Ropa.obtenerRegistroRopa(), columnas)
            var panelScroll = JScrollPane(tablaRopa)

            panelGeneralRopa = JPanel(BorderLayout())
            panelGeneralRopa.add(panelScroll, BorderLayout.CENTER)
            panelGeneralRopa.setPreferredSize(Dimension(600,200))

            return JOptionPane.showOptionDialog(
                null,
                panelGeneralRopa,
                "CRUD de ropa",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                arrayOf("Crear", "Editar", "Eliminar", "REGRESAR"),
                null
            )
        }

        fun crearVentanaCrearRopa(): Array<JTextField?>{
            var nombreCampos:Array<String> = arrayOf("Nombre: ", "Precio: ", "¿Está en tendencia?: ", "Fecha de lanzamiento: ","Años de vida útil: ")
            var camposEntrada = arrayOfNulls<JTextField>(nombreCampos.size)
            var panel = JPanel()
            panel.setLayout(BoxLayout(panel, BoxLayout.Y_AXIS))

            for(i in 0..nombreCampos.size-1){
                var segundoPanel = JPanel()
                var etiqueta = JLabel(nombreCampos[i])
                var campoTexto = JTextField(20)
                camposEntrada[i] = campoTexto
                segundoPanel.add(etiqueta)
                segundoPanel.add(campoTexto)
                panel.add(segundoPanel)
            }

            JOptionPane.showConfirmDialog(
                null,
                panel,
                "Crear ropa",
                JOptionPane.OK_CANCEL_OPTION
            )

            return camposEntrada

        }

        fun crearVentanaActualizarRopa():Int{
            return JOptionPane.showOptionDialog(
                null,
                panelGeneralRopa,
                "Actualizar ropa",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                arrayOf("Actualizar", "Cancelar"),
                null
            )
        }
        
        fun crearVentanaEliminarRopa():Int{
            return JOptionPane.showOptionDialog(
                null,
                panelGeneralRopa,
                "Eliminar ropa",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                arrayOf("Eliminar", "Cancelar"),
                null
            )
        }
        
        //Métodos necesarios para retornar datos de las ventanas respecto a ROPA

        fun retornarFilaSeleccionadaTablaRopa():Int{
            return tablaRopa.selectedRow
        }

        fun obtenerDatosTablaRopa():String{
            var registros = ""
            for(i in 0..tablaRopa.rowCount-1){
                for(j in 0..tablaRopa.columnCount-1){
                    var valor = tablaRopa.getValueAt(i,j).toString().replace("\n","")
                    registros += valor
                    if(j!=tablaRopa.columnCount-1){
                        registros += ","
                    }
                }
                if(i!= tablaRopa.rowCount-1){
                    registros += ";\n"
                }else{
                    registros += ";"
                }

            }
            return registros
        }

        //Ventanas para la administración de DISEÑADORES
        fun crearVentanaPrincipalDiseniador():Int{
            var columnas = arrayOf("Nombre", "Valor de mercado", "N° Colecciones", "¿Creador Unisex?", "Ropa creada")
            tablaDiseniador = JTable(Diseniador.obtenerRegistroDiseniador(), columnas)
            var panelScroll = JScrollPane(tablaDiseniador)

            panelGeneralDiseniador = JPanel(BorderLayout())
            panelGeneralDiseniador.add(panelScroll, BorderLayout.CENTER)
            panelGeneralDiseniador.setPreferredSize(Dimension(600,200))

            return JOptionPane.showOptionDialog(
                null,
                panelGeneralDiseniador,
                "CRUD de diseñador",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                arrayOf("Crear", "Editar", "Eliminar", "REGRESAR"),
                null
            )
        }

        fun crearVentanaCrearDiseniador(): Array<JTextField?>{
            var nombreCampos:Array<String> = arrayOf("Nombre: ", "Valor de mercado: ", "Número de  colecciones: ", "¿Creador unisex?: ")
            var camposEntrada = arrayOfNulls<JTextField>(nombreCampos.size+1)
            var panel = JPanel()
            panel.setLayout(BoxLayout(panel, BoxLayout.Y_AXIS))

            for(i in 0..nombreCampos.size-1){
                var segundoPanel = JPanel()
                var etiqueta = JLabel(nombreCampos[i])
                var campoTexto = JTextField(20)
                camposEntrada[i] = campoTexto
                segundoPanel.add(etiqueta)
                segundoPanel.add(campoTexto)
                panel.add(segundoPanel)
            }
            camposEntrada[nombreCampos.size] = JTextField(20)


            var listaRopaPanel = JPanel()
            listaRopaPanel.setLayout(BoxLayout(listaRopaPanel, BoxLayout.X_AXIS))
            var registrosRopa = Ropa.obtenerRegistroRopa()

            var nombresRopa = Array(registrosRopa.size){Array(1){""} }
            for(i in 0..registrosRopa.size-1){
                nombresRopa[i][0] = registrosRopa[i][0]
            }

            var tablaRopa = JTable(nombresRopa, arrayOf("Nombre de ropa"))
            var panelScrollRopa = JScrollPane(tablaRopa)
            panelScrollRopa.setPreferredSize(Dimension(50,150))


            var tablaAgregar = JTable(Array(registrosRopa.size){Array(1){""} }, arrayOf("Nombre de ropa"))
            var panelScrollRopaAgregada = JScrollPane(tablaAgregar)
            panelScrollRopaAgregada.setPreferredSize(Dimension(50,150))


            var panelBotones = JPanel()
            panelBotones.setLayout(BoxLayout(panelBotones, BoxLayout.Y_AXIS))
            var botonAgregar = JButton(">>")

            var contadorFila = 0

            var ropaSeleccionada = ""
            botonAgregar.addActionListener {
                var nombreRopaAgregar = tablaRopa.getValueAt(tablaRopa.selectedRow, 0)
                tablaAgregar.setValueAt(nombreRopaAgregar, contadorFila,0 )
                ropaSeleccionada += tablaRopa.selectedRow.toString()
                contadorFila++
                camposEntrada[nombreCampos.size]?.setText(ropaSeleccionada)
            }

            var botonEliminar = JButton("<<")
            botonEliminar.addActionListener {

                ropaSeleccionada = ""

                tablaAgregar.setValueAt("", tablaAgregar.selectedRow,0)

                for(i in 0..registrosRopa.size-1){
                    for(j in 0..tablaAgregar.rowCount-1){
                        var x = registrosRopa[i][0].replace("\n", "")
                        var y = tablaAgregar.getValueAt(j,0)


                        if(registrosRopa[i][0].replace("\n","").equals(tablaAgregar.getValueAt(j,0).toString().replace("\n",""))){
                            ropaSeleccionada += i.toString()
                        }
                    }
                }
                contadorFila--
                camposEntrada[nombreCampos.size]?.setText(ropaSeleccionada)
            }


            panelBotones.add(botonAgregar)
            panelBotones.add(botonEliminar)

            listaRopaPanel.add(panelScrollRopa)
            listaRopaPanel.add(panelBotones)
            listaRopaPanel.add(panelScrollRopaAgregada)


            panel.add(listaRopaPanel)

            JOptionPane.showConfirmDialog(
                null,
                panel,
                "Crear diseñador",
                JOptionPane.OK_CANCEL_OPTION
            )

            return camposEntrada
        }

        fun crearVentanaActualizarDiseniador(): Int{
            return JOptionPane.showOptionDialog(
                null,
                panelGeneralDiseniador,
                "Actualizar diseniador",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                arrayOf("Actualizar", "Cancelar"),
                null
            )
        }

        fun crearVentanaEliminarDiseniador():Int{
            return JOptionPane.showOptionDialog(
                null,
                panelGeneralDiseniador,
                "Eliminar ropa",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                arrayOf("Eliminar", "Cancelar"),
                null
            )

        }


        //Métodos necesarios para retornar datos de las ventanas respecto a DISEÑADOR
        fun retornarFilaSeleccionadaTablaDiseniador():Int{
            return tablaDiseniador.selectedRow
        }
        
        fun obtenerDatosTablaDiseniador():String{
            var registros = ""
            var registrosRopa = Ropa.obtenerRegistroRopa()
            for(i in 0..tablaDiseniador.rowCount-1){
                for(j in 0..tablaDiseniador.columnCount-1){
                    var valor = tablaDiseniador.getValueAt(i,j).toString().replace("\n","").replace("\r","")
                    if(j!=tablaDiseniador.columnCount-1){
                        registros += valor
                        registros += ","
                    }else{
                        if(!valor.equals("")){
                            var ropaTokenizer = StringTokenizer(valor," ")
                            while(ropaTokenizer.hasMoreTokens()){
                                var nombreRopa = ropaTokenizer.nextToken()
                                for (k in 0.. registrosRopa.size-1){
                                    if(nombreRopa.equals(registrosRopa[k][0].replace("\n",""))){
                                        registros += k.toString()
                                    }
                                }
                            }
                        }
                    }
                }
                if(i!= tablaDiseniador.rowCount-1){
                    registros += ";\n"
                }else{
                    registros += ";"
                }

            }
            return registros
        }



    }
}