#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring JNICALL
Java_com_docotel_ikhsansyahrizal_second_security_Security_apiKey(JNIEnv *env, jobject ) {

   std::string api_key = "bbb38447c52844968dc22d49a2f9945b";

   return env->NewStringUTF(api_key.c_str());
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_docotel_ikhsansyahrizal_second_security_Security_baseUrl(JNIEnv *env, jobject ) {

   std::string baseUrl = "https://newsapi.org/v2/";

   return env->NewStringUTF(baseUrl.c_str());
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_docotel_ikhsansyahrizal_second_security_Security_country(JNIEnv *env, jobject ) {

   std::string country = "us";

   return env->NewStringUTF(country.c_str());
}
