package com.thezayin.common.neumorphic

/**
 * Describes the outward protruding features of an object or curved surface
 */

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
annotation class EnumConvexityClass

@EnumConvexityClass
enum class Convexity {
    CONCAVE,
    CONVEX,
}