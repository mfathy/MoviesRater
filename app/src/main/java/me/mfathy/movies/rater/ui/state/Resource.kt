package me.mfathy.movies.rater.ui.state


/**
 * Resource callback to handle different resource states.
 */
class Resource<out T> constructor(
    val status: ResourceState,
    val data: T?,
    val error: Throwable?
) {

    fun <T> success(data: T): Resource<T> {
        return Resource(ResourceState.SUCCESS, data, null)
    }

    fun <T> error(message: Throwable): Resource<T> {
        return Resource(ResourceState.ERROR, null, message)
    }

    fun <T> loading(): Resource<T> {
        return Resource(ResourceState.LOADING, null, null)
    }

}