package de.giphy_androidcleanarchitecture

import io.uniflow.android.test.SimpleObserver
import io.uniflow.android.test.TestViewObserver
import io.uniflow.androidx.flow.AndroidDataFlow
import io.uniflow.core.flow.data.Event
import io.uniflow.core.flow.data.UIData
import io.uniflow.core.flow.data.UIEvent
import io.uniflow.core.flow.data.UIState

class FixedTestViewObserver {
    val values = arrayListOf<UIData>()
    val states = SimpleObserver<UIState> { values.add(it) }
    val events = SimpleObserver<Event<UIEvent>> { values.add(it.peek()) }

    val lastStateOrNull: UIState?
        get() = states.values.lastOrNull()
    val statesCount
        get() = states.values.count()
    val eventsCount
        get() = events.values.count()
    val lastEventOrNull
        get() = events.values.lastOrNull()
    val lastValueOrNull
        get() = values.lastOrNull()

    fun assertReceived(vararg any: UIData) {
        assert(this.values == any.toList()) { "Wrong values\nshould have [${any.toList()}]\nbut was [${values}]" }
    }

    fun assertReceived(vararg states: UIState) {
        assert(this.states.values == states.toList()) { "Wrong values\nshould have [${states.toList()}]\nbut was [${this.states.values}]" }
    }

    fun assertReceived(vararg events: UIEvent) {
        assert(this.events.values == events.toList()) { "Wrong values\nshould have [${events.toList()}]\nbut was [${this.events.values}]" }
    }
}

fun AndroidDataFlow.createTestObserver(): FixedTestViewObserver {
    val tester = FixedTestViewObserver()
    dataPublisher.states.observeForever(tester.states)
    dataPublisher.events.observeForever(tester.events)
    return tester
}