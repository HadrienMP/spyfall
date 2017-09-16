package fr.hadrienmp.spyfall.domain

import fr.hadrienmp.spyfall.lib.notEmpty
import fr.hadrienmp.testutils.Doc
import org.assertj.core.api.Assertions.assertThatThrownBy

class NotEmptyStringDoc {
    @Doc
    fun `should throw an exception when given an empty string`() {
        assertThatThrownBy { "".notEmpty() }
                .isInstanceOf(IllegalArgumentException::class.java)
                .hasMessage("Cannot be empty")
    }

    @Doc
    fun `should throw an exception when given a blank string`() {
        assertThatThrownBy { "   ".notEmpty() }
                .isInstanceOf(IllegalArgumentException::class.java)
                .hasMessage("Cannot be empty")
    }
}