package fr.hadrienmp.spyfall.ui.web.testutils

import fr.hadrienmp.spyfall.ui.web.App
import fr.hadrienmp.spyfall.ui.web.Game
import fr.hadrienmp.spyfall.ui.web.Port
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class ServerRule: TestRule {
    override fun apply(base: Statement, description: Description): Statement {
        return object: Statement() {
            override fun evaluate() {
                val app = App(Port.default(), Game())
                app.start()
                base.evaluate()
                app.stop()
            }
        }
    }
}