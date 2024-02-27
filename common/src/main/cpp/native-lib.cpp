#include <jni.h>
#include <string>

extern "C"
JNIEXPORT jstring JNICALL
Java_id_android_codebase_common_utils_keys_Keys_encryptedSecretMessage(JNIEnv *env, jobject self) {
    std::string message = "Hello World";
    return env->NewStringUTF(message.c_str());
}
