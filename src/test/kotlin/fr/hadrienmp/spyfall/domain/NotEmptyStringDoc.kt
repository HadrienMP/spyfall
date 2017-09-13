package fr.hadrienmp.spyfall.domain

import fr.hadrienmp.spyfall.lib.notEmpty
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.Test

class NotEmptyStringDoc {
    @Test
    fun `should throw an exception when given an empty string`() {
        assertThatThrownBy { "".notEmpty() }
                .isInstanceOf(IllegalArgumentException::class.java)
                .hasMessage("Cannot be empty")
    }

    @Test
    fun `should throw an exception when given a blank string`() {
        assertThatThrownBy { "   ".notEmpty() }
                .isInstanceOf(IllegalArgumentException::class.java)
                .hasMessage("Cannot be empty")
    }
}