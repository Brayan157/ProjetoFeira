package com.ifgoiano.feira.setup

import org.springframework.context.ConfigurableApplicationContext

interface IntegrationTestsConfiguration {
    fun configure(applicationContext: ConfigurableApplicationContext)
}