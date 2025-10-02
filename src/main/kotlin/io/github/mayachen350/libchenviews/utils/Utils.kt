package io.github.mayachen350.libchenviews.utils

/** Utility function for when validating data.

Exemple:

```
fun addSubject(content: String) {
        // validation du contenu
        content
            .cantBe("Empty subject") { isEmpty() }
            .cantBe("Subject name too short, 5 min") { length < 5 }

        dao.sujetParContenu(contenu)
            .cantBe("Subject already existing") { isPresent }

        // Tout va bien, on peut ajouter le sujet dans la BD
        dao.addSubject(Sujet().apply {
            this.contenu = contenu
        })
    }
```
**/
inline fun <T> T.cantBe(errorMsg: String, condition: T.() -> Boolean): T {
    if (this.condition())
        throw IllegalArgumentException(errorMsg)
    return this
}