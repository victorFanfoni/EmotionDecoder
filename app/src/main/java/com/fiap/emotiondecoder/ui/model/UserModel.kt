package com.fiap.emotiondecoder.ui.model

import android.os.Parcel
import android.os.Parcelable

class UserModel(var email: String = "", var password: String = "") : Parcelable {

    fun isValid(): Boolean {
        // Verifica se o email e a senha são válidos
        return email.isNotBlank() && password.isNotBlank()
    }

    // Parcelable Implementation
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(email)
        parcel.writeString(password)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UserModel> {
        override fun createFromParcel(parcel: Parcel): UserModel {
            return UserModel(parcel)
        }

        override fun newArray(size: Int): Array<UserModel?> {
            return arrayOfNulls(size)
        }
    }

    private constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )
}
