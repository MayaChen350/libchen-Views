package io.github.mayachen350.libchenviews

import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl

object SessionCookieJar : CookieJar {

    private var cookies: MutableList<Cookie>? = mutableListOf()

    override fun saveFromResponse(url: HttpUrl, cookies: MutableList<Cookie>) {
        this.cookies = ArrayList<Cookie>(cookies)
    }

    override fun loadForRequest(url: HttpUrl): MutableList<Cookie> =
        cookies?.filter { it.expiresAt() > System.currentTimeMillis() && it.matches(url) }
            ?.toMutableList() ?: mutableListOf<Cookie>()
}