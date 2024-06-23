import java.awt.Dimension
import java.util.*
import javax.swing.*

class VentanaDiseniador(nombresColumnas: Array<String>):Ventana(nombresColumnas){

    override fun crearVentanaCrear(): Array<JTextField?> {
        var nombreCampos:Array<String> = arrayOf("Nombre: ", "Valor de mercado: ", "Número de  colecciones: ", "¿Creador unisex? (true/false): ")
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

        var registrosRopa = Ropa.obtenerRegistroRopa()

        var listaRopaPanel = JPanel()
        listaRopaPanel.setLayout(BoxLayout(listaRopaPanel, BoxLayout.X_AXIS))
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

        var resultadoVentana = JOptionPane.showConfirmDialog(
            null,
            panel,
            "Crear diseñador",
            JOptionPane.OK_CANCEL_OPTION
        )

        if(resultadoVentana==JOptionPane.CANCEL_OPTION||resultadoVentana==JOptionPane.CLOSED_OPTION){
            camposEntrada = emptyArray()
        }else{
            for(campo in camposEntrada){
                if (campo?.getText().equals("")||campo==null){
                    JOptionPane.showMessageDialog(null, "Uno o más campos no fueron completados.","ERROR",JOptionPane.ERROR_MESSAGE)
                    camposEntrada = emptyArray()
                    break
                }
            }
        }

        return camposEntrada
    }

    override fun obtenerDatosTabla(): String {
        var registros = ""
        var registrosRopa = Ropa.obtenerRegistroRopa()
        for(i in 0..tabla.rowCount-1){
            for(j in 0..tabla.columnCount-1){
                var valor = tabla.getValueAt(i,j).toString().replace("\n","").replace("\r","")
                if(j!=tabla.columnCount-1){
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
            if(i!= tabla.rowCount-1){
                registros += ";\n"
            }else{
                registros += ";"
            }

        }
        return registros
    }


}