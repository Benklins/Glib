package com.spacexlab.annotations


@Target(
    AnnotationTarget.VALUE_PARAMETER,
    AnnotationTarget.FIELD,
    AnnotationTarget.FUNCTION, AnnotationTarget.LOCAL_VARIABLE
)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
annotation class Mapped()
