#include <jni.h>
#include <string>


extern "C" jstring Java_com_heroku_1app_core_network_base_1url_1config_NdkBaseUrlModule_getHerokuAppBaseUrl(
        JNIEnv *env,
        jobject) {

    std::string flavor = APP_FLAVOR_STR;
    std::string baseUrl;

    if (flavor == "dev") {
        baseUrl = "https://apollo-fullstack-tutorial.herokuapp.com/graphql";
    } else if (flavor == "live") {
        baseUrl = "https://apollo-fullstack-tutorial.herokuapp.com/graphql";
    }
    return env->NewStringUTF(baseUrl.c_str());
}







