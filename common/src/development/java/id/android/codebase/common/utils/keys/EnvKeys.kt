package id.android.codebase.common.utils.keys

import android.util.Base64.DEFAULT
import android.util.Base64.decode

object EnvKeys {

    init {
        System.loadLibrary("native-lib-development")
    }

    private external fun encryptedBaseApiUrl(): String

    fun baseApiUrl(): String {
        return String(decode(encryptedBaseApiUrl(), DEFAULT))
    }

}
