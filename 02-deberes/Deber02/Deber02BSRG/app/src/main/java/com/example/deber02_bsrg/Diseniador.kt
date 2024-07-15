package com.example.deber02_bsrg

import android.os.Parcel
import android.os.Parcelable

class Diseniador(
    var nombre: String,
    var valorMercado:Long,
    var numeroColecciones:Int,
    var creadorUnisex:Boolean,
    var ropa: Array<String>
):Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readLong(),
        parcel.readInt(),
        parcel.readByte() != 0.toByte(),
        parcel.createStringArray()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nombre)
        parcel.writeLong(valorMercado)
        parcel.writeInt(numeroColecciones)
        parcel.writeByte(if (creadorUnisex) 1 else 0)
        parcel.writeStringArray(ropa)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Diseniador> {
        override fun createFromParcel(parcel: Parcel): Diseniador {
            return Diseniador(parcel)
        }

        override fun newArray(size: Int): Array<Diseniador?> {
            return arrayOfNulls(size)
        }
    }


}