package io.github.mayachen350.libchenviews.utils

import android.widget.DatePicker
import org.apache.commons.lang3.time.DateUtils
import java.util.Date

/** Get the date in `java.util.Date` format from a DatePicker android view widget.
 *
 * It was made to be working on SDK 24+**/
fun DatePicker.date(): Date =
    DateUtils.setYears(
        DateUtils.setMonths(
            DateUtils.setDays(
                Date(0), this.dayOfMonth
            ), this.month
        ), this.year
    )

/** @see Date.getTime **/
fun DatePicker.timestamp(): Long =
    this.date().time