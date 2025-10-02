package io.github.mayachen350.libchenviews.http

import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl

/** Cookie jar with enough to use as a cookie jar with Retrofit.
 *
 * The code might be questionable though. At least it was when I imported it from my school project.*/
open class SimpleCookieJar : CookieJar {
    protected open var cookies: ArrayList<Cookie>? = ArrayList()

    override fun saveFromResponse(url: HttpUrl, cookies: MutableList<Cookie>) {
        this.cookies = ArrayList<Cookie>(cookies)
    }

    override fun loadForRequest(url: HttpUrl): MutableList<Cookie> =
        cookies?.filter { it.expiresAt() > System.currentTimeMillis() && it.matches(url) }
            ?.toMutableList() ?: mutableListOf<Cookie>()
}