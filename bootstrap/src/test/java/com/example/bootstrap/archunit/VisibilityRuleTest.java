package com.example.bootstrap.archunit;

import static com.example.bootstrap.archunit.common.ArchUnitConfig.*;
import static com.example.bootstrap.archunit.common.ArchUnitUtil.matchAllClassesInPackage;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

/**
 * @author lvhao09
 * @date 2024/4/30
 */
@AnalyzeClasses(
    packages = {BASE_PACKAGE},
    importOptions = {ImportOption.DoNotIncludeTests.class, ImportOption.DoNotIncludeJars.class})
public class VisibilityRuleTest {

  // application layer
  @ArchTest
  public static final ArchRule service_layer_classes_should_be_package_private =
      classes()
          .that()
          .resideInAPackage(matchAllClassesInPackage(APPLICATION_SERVICE_PACKAGE))
          .should()
          .bePackagePrivate()
          .allowEmptyShould(true);

  // adapter layer
  @ArchTest
  public static final ArchRule adapter_layer_classes_should_be_package_private =
      classes()
          .that()
          .resideInAPackage(matchAllClassesInPackage(ADAPTER_PACKAGE))
          .should()
          .bePackagePrivate()
          .allowEmptyShould(true);
}
