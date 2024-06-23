import java.awt.BorderLayout
import java.awt.Dimension
import java.awt.Label
import javax.swing.*

abstract class Ventana {

    var tabla: JTable = JTable()
    var panelGeneral = JPanel(BorderLayout())
    var columnas: Array<String>

    constructor(nombresColumnas:Array<String>){
        this.columnas = nombresColumnas
    }

    //Ventanas que son empleadas tanto para el CRUD de diseñador como de ropa.
    fun crearVentanaPrincipal(nombreVentana:String, datos: Array<Array<String>>):Int{

        tabla = JTable(datos, columnas)
        var panelScroll = JScrollPane(tabla)

        panelGeneral = JPanel(BorderLayout())
        panelGeneral.add(panelScroll, BorderLayout.CENTER)
        panelGeneral.setPreferredSize(Dimension(600,200))

        return JOptionPane.showOptionDialog(
            null,
            panelGeneral,
            nombreVentana,
            JOptionPane.YES_NO_OPTION,
            JOptionPane.PLAIN_MESSAGE,
            null,
            arrayOf("Crear", "Editar", "Eliminar", "REGRESAR"),
            null
        )
    }
    fun crearVentanaActualizar(nombreVentana: String):Int{
        var areaMensaje = JPanel()
        areaMensaje.setLayout(BoxLayout(areaMensaje, BoxLayout.X_AXIS))
        var mensaje = JLabel("Puedes alterar cualquier celda. Pulsa ENTER una vez terminado, y ACTUALIZA.")
        areaMensaje.add(mensaje)
        panelGeneral.add(areaMensaje, BorderLayout.NORTH)

        panelGeneral.revalidate()
        panelGeneral.repaint()

        return JOptionPane.showOptionDialog(
            null,
            panelGeneral,
            nombreVentana,
            JOptionPane.YES_NO_OPTION,
            JOptionPane.PLAIN_MESSAGE,
            null,
            arrayOf("Actualizar", "Cancelar"),
            null
        )
    }
    fun crearVentanaCrearEliminar(nombreVentana: String):Int{
        var areaMensaje = JPanel()
        areaMensaje.setLayout(BoxLayout(areaMensaje, BoxLayout.X_AXIS))
        var mensaje = JLabel("Selecciona una fila, y ELIMINA.")
        areaMensaje.add(mensaje)
        panelGeneral.add(areaMensaje, BorderLayout.NORTH)

        panelGeneral.revalidate()
        panelGeneral.repaint()

        return JOptionPane.showOptionDialog(
            null,
            panelGeneral,
            nombreVentana,
            JOptionPane.YES_NO_OPTION,
            JOptionPane.PLAIN_MESSAGE,
            null,
            arrayOf("Eliminar", "Cancelar"),
            null
        )
    }


    fun retornarFilaSeleccionadaTabla():Int{
        return tabla.selectedRow
    }


    abstract fun obtenerDatosTabla():String
    abstract fun crearVentanaCrear():Array<JTextField?>

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

    }
}