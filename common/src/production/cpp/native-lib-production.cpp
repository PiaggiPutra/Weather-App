#include <jni.h>
#include <string>

extern "C"
JNIEXPORT jstring JNICALL
Java_id_android_codebase_common_utils_keys_EnvKeys_encryptedBaseApiUrl(JNIEnv *env, jobject self) {
    std::string url = "https://localhost/";
    return env->NewStringUTF(url.c_str());
}
