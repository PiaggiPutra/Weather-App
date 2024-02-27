package id.android.codebase.utils.keys

import android.util.Base64.DEFAULT
import android.util.Base64.decode

object DevelopmentKeys {

    init {
        System.loadLibrary("native-lib-development")
    }

    private external fun encryptedOneSignalAppID(): String

    private external fun encryptedAppsFlyerBrandedLink(): String

    private external fun encryptedFacebookAppId(): String

    fun oneSignalAppID(): String {
        return String(decode(encryptedOneSignalAppID(), DEFAULT))
    }

    fun getAppsFlyerBrandedLink(): String {
        return String(decode(encryptedAppsFlyerBrandedLink(), DEFAULT))
    }

    fun getFacebookAppId(): String {
        return String(decode(encryptedFacebookAppId(), DEFAULT))
    }
}
