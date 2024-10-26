package com.ifgoiano.feira.setup

import io.mockk.clearAllMocks
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
open class UnitTests {

    @BeforeEach
    fun setUp() {
        clearAllMocks()
    }
}