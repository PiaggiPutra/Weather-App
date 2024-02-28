package id.android.codebase.common.utils.keys

import android.util.Base64.DEFAULT
import android.util.Base64.decode

object Keys {

    init {
        System.loadLibrary("native-lib")
    }

    private external fun encryptedSecretMessage(): String

    fun secretMessage(): String {
        return String(decode(encryptedSecretMessage(), DEFAULT))
    }

    fun baseApiUrl(): String {
        return EnvKeys.baseApiUrl()
    }

    const val BASE_URL_IMAGE = "http://openweathermap.org/img/wn/"

    const val FORMAT_IMAGE = "@2x.png"

}
