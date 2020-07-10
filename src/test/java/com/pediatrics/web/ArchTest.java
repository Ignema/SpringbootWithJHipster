package com.pediatrics.web;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.pediatrics.web");

        noClasses()
            .that()
            .resideInAnyPackage("com.pediatrics.web.service..")
            .or()
            .resideInAnyPackage("com.pediatrics.web.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..com.pediatrics.web.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
