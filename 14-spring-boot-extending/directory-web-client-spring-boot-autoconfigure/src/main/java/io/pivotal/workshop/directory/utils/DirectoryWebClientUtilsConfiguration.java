package io.pivotal.workshop.directory.utils;


import io.pivotal.workshop.directory.annotation.Algorithm;
import io.pivotal.workshop.directory.annotation.EnableDirectoryWebClientUtils;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

public class DirectoryWebClientUtilsConfiguration implements ImportSelector {

    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        AnnotationAttributes attributes =
                AnnotationAttributes.fromMap(
                        annotationMetadata.getAnnotationAttributes(EnableDirectoryWebClientUtils.class.getName(), false));
        Algorithm algorithm = attributes.getEnum("algorithm");
        switch(algorithm){
            case PBKDF2:
                return new String[] {"io.pivotal.workshop.directory.utils.Pbkdf2Encoder"};
            case BCRYPT:
                default:
                return new String[] {"io.pivotal.workshop.directory.utils.BcryptEncoder"};
        }
    }
}
