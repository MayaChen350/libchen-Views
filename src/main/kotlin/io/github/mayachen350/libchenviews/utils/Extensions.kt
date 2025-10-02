package io.github.mayachen350.libchenviews.utils

import android.widget.DatePicker
import org.apache.commons.lang3.time.DateUtils
import java.util.Date

/** Get the date in `java.util.Date` format from a DatePicker android view widget. **/
fun DatePicker.date(): Date =
//    Date().let2(DateUtils::setMilliseconds, 0)
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

//internal inline fun <T> T.let2(func: (T, Any) -> T, param1: Any): T = func(this, param1)