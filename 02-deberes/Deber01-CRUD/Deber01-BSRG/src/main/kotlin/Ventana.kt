import java.awt.BorderLayout
import java.awt.Dimension
import javax.swing.*

abstract class Ventana {

    var tabla: JTable = JTable()
    var panelGeneral = JPanel(BorderLayout())
    var columnas: Array<String>

    constructor(nombresColumnas:Array<String>){
        this.columnas = nombresColumnas
    }


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