package com.thezayin.neumorphic

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

/**
 * Toggle switch status
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
annotation class EnumToggleClass

@EnumToggleClass
enum class Toggle {
    ON,//open
    OFF,//close
}


/**
 * orientation
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
annotation class EnumDirectionClass

@EnumDirectionClass
enum class Direction {
    Horizontal, 
    Vertical,
}