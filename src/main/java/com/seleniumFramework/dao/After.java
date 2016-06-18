package com.seleniumFramework.dao;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)




public @interface After {
	public abstract String value() default "";
	public abstract long timeout() default 0L;

}
