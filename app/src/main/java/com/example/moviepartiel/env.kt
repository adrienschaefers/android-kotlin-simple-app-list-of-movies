package com.example.moviepartiel

class Env {
    companion object {
        enum class Environment {
            PRODUCTION,
            DEVELOPMENT,
            TEST
        }

        val currentEnvironment = Environment.PRODUCTION
    }
}