package com.fretron.config

class Context {
    companion object {
        private var config: Config? = null

        fun init(arguments: Array<String>): Context {
            if (config == null) {
                config = Config()
            }
            if (arguments.isEmpty()) {
                throw RuntimeException("Configuration file is not provided")
            }

            for (file in arguments) {
                try {
                    config?.load(file)
                } catch (e: Exception) {
                    println("Error loading config file: ${e.message}")
                }
            }

            return Context()
        }

        fun getConfig(): Config? {
            return config
        }
    }
}
