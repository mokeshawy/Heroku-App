# Sets the minimum version of CMake required to build the native library.

cmake_minimum_required(VERSION 4.1.2)

# Creates and names a library, sets it as either STATIC
# or SHARED, and provides the relative paths to its source code.
# You can define multiple libraries, and CMake builds them for you.
# Gradle automatically packages shared libraries with your APK.


if(CMAKE_BUILD_TYPE STREQUAL "Debug")
    add_definitions(-DDEBUG_BUILD)
endif()

if(DEFINED APP_FLAVOR)
    add_definitions(-DAPP_FLAVOR_STR="${APP_FLAVOR}")
endif()

add_library( # Sets the name of the library.
        base-url-lib

        # Sets the library as a shared library.
        SHARED

        # Provides a relative path to your source file(s).
        base-url-lib.cpp)