package id.android.codebase.common.utils

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData

/**
 * Used as a wrapper for data that is exposed via a LiveData that represents an event.
 * @see https://medium.com/androiddevelopers/livedata-with-snackbar-navigation-and-other-events-the-singleliveevent-case-ac2622673150
 */
open class Event<out T>(private val content: T) {

    private var hasBeenHandled = false

    /**
     * Returns the content and prevents its use again.
     */
    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }

    /**
     * Returns the content, even if it's already been handled.
     */
    fun peekContent(): T = content
}

inline fun <T> LiveData<Event<T>>.observeEvent(lifecycleOwner: LifecycleOwner, crossinline onChanged: (T) -> Unit) {
    this.observe(lifecycleOwner) { event ->
        event.getContentIfNotHandled()?.let { it -> onChanged.invoke(it) }
    }
}

fun <T> T.toEvent() = Event(this)
