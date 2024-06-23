import javax.swing.*

class VentanaRopa(nombresColumnas: Array<String>) :Ventana(nombresColumnas) {


    override fun crearVentanaCrear(): Array<JTextField?> {
        var nombreCampos:Array<String> = arrayOf("Nombre: ", "Precio: ", "¿Está en tendencia? (true/false): ", "Fecha de lanzamiento (dd/mm/yyyy): ","Años de vida útil: ")
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

        var resultadoVentana = JOptionPane.showConfirmDialog(
            null,
            panel,
            "Crear ropa",
            JOptionPane.OK_CANCEL_OPTION
        )

        if(resultadoVentana==JOptionPane.CANCEL_OPTION||resultadoVentana==JOptionPane.CLOSED_OPTION){
            camposEntrada = emptyArray()
        }else{
            for(campo in camposEntrada){
                if (campo?.getText().equals("")){
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
        for(i in 0..tabla.rowCount-1){
            for(j in 0..tabla.columnCount-1){
                var valor = tabla.getValueAt(i,j).toString().replace("\n","")
                registros += valor
                if(j!=tabla.columnCount-1){
                    registros += ","
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