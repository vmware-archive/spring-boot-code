package io.pivotal.workshop.directory.annotation;


import io.pivotal.workshop.directory.utils.DirectoryWebClientUtilsConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(DirectoryWebClientUtilsConfiguration.class)
public @interface EnableDirectoryWebClientUtils {
    Algorithm algorithm() default Algorithm.BCRYPT;
}
