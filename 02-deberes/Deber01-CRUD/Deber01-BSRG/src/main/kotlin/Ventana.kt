import java.awt.BorderLayout
import java.awt.Dimension
import java.util.*
import javax.swing.*

class Ventana {

    companion object{

        var panelGeneralRopa = JPanel(BorderLayout())
        var tablaRopa:JTable = JTable()

        fun crearVentanaPrincipal(): Int{
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

        fun obtenerDatosTablaRopa():String{
            var nuevosRegistros = ""
            for(i in 0..tablaRopa.rowCount-1){
                for(j in 0..tablaRopa.columnCount-1){
                    var valor = tablaRopa.getValueAt(i,j).toString().replace("\n","")
                    nuevosRegistros += valor
                    if(j!=tablaRopa.columnCount-1){
                        nuevosRegistros += ","
                    }
                }
                if(i!= tablaRopa.rowCount-1){
                    nuevosRegistros += ";\n"
                }else{
                    nuevosRegistros += ";"
                }

            }
            return nuevosRegistros
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

        fun retornarFilaSeleccionadaTablaRopa():Int{
            return tablaRopa.selectedRow
        }




    }
}