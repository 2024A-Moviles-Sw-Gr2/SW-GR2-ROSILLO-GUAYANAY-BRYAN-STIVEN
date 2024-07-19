package com.example.deber02_bsrg

import android.os.Parcel
import android.os.Parcelable
import java.time.LocalDate

class Ropa(
    var nombre:String,
    var precio:Float,
    var tendencia:Boolean,
    var lanzamiento: LocalDate,
    var aniosVidaUtil: Int
):Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readFloat(),
        parcel.readByte() != 0.toByte(),
        LocalDate.ofEpochDay(parcel.readLong()),
        parcel.readInt()
    ) {
    }



    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nombre)
        parcel.writeFloat(precio)
        parcel.writeByte(if (tendencia) 1 else 0)
        parcel.writeLong(lanzamiento.toEpochDay())
        parcel.writeInt(aniosVidaUtil)
    }



    override fun describeContents(): Int {
        return 0
    }

    override fun toString(): String {
        val toStringRopa = "$nombre\nPrecio: $precio$ | ¿En tendencia?: $tendencia\nLanzamiento: ${lanzamiento.toString()} | Años vida útil: $aniosVidaUtil"
        return toStringRopa
    }

    companion object CREATOR : Parcelable.Creator<Ropa> {
        override fun createFromParcel(parcel: Parcel): Ropa {
            return Ropa(parcel)
        }

        override fun newArray(size: Int): Array<Ropa?> {
            return arrayOfNulls(size)
        }
    }


}