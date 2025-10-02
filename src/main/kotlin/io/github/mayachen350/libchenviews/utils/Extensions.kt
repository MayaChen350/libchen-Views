package ca.cegepmontpetit.cem6222816.tp3_4n6.utils

import android.view.View
import android.widget.DatePicker
import io.github.mayachen350.libchenviews.HasLoadingSnackbar
import org.apache.commons.lang3.time.DateUtils
import java.util.Date

fun DatePicker.date(): Date =
    DateUtils.setYears(
        DateUtils.setMonths(
            DateUtils.setDays(
                DateUtils.setHours(
                    DateUtils.setMinutes(
                        DateUtils.setSeconds(
                            DateUtils.setMilliseconds(
                                Date(), 0
                            ), 0
                        ), 0
                    ), 0
                ), this.dayOfMonth
            ), this.month
        ), this.year
    )

inline fun HasLoadingSnackbar.httpRequestWithLoading(
    contextView: View,
    request: (dismissSnackbarFun: () -> Unit) -> Unit
) {
    println("New http request!")
    val snackbar = getLoadingSnackbar(contextView).also { it.show() }
    println("Loading snackbar supposedly made!")
    request { snackbar.dismiss() }
    println("Request ended!")
}