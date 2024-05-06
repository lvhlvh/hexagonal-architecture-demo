package com.example.bootstrap.archunit;

import static com.example.bootstrap.archunit.common.ArchUnitConfig.*;
import static com.example.bootstrap.archunit.common.ArchUnitUtil.matchAllClassesInPackage;
import static com.example.bootstrap.archunit.common.ArchUnitUtil.matchAllClassesInPackages;
import static com.tngtech.archunit.base.DescribedPredicate.and;
import static com.tngtech.archunit.base.DescribedPredicate.or;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.properties.HasName;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

@AnalyzeClasses(
    packages = {BASE_PACKAGE},
    importOptions = {ImportOption.DoNotIncludeTests.class, ImportOption.DoNotIncludeJars.class})
public class DependencyRuleTest {

  // domain layer
  @ArchTest
  public static final ArchRule domain_model_should_not_depend_on_outside =
      noClasses()
          .that()
          .resideInAPackage(matchAllClassesInPackage(DOMAIN_PACKAGE))
          .should()
          .dependOnClassesThat()
          .resideOutsideOfPackages(
              matchAllClassesInPackages(PACKAGES_THAT_ANY_CLASS_CAN_DEPEND_ON)
                  .toArray(new String[0]))
          .allowEmptyShould(true);

  // application layer
  @ArchTest
  public static final ArchRule
      port_should_only_depend_on_command_or_query_or_exception_or_common_classes =
          classes()
              .that()
              .resideInAnyPackage(matchAllClassesInPackage(APPLICATION_PORT_PACKAGE))
              .should()
              .onlyDependOnClassesThat(
                  or(
                      and(
                          JavaClass.Predicates.resideInAPackage(
                              matchAllClassesInPackage(APPLICATION_PORT_PACKAGE)),
                          HasName.Predicates.nameMatching("^.*(Command|Query|Exception)$")),
                      JavaClass.Predicates.resideInAnyPackage(
                          matchAllClassesInPackages(PACKAGES_THAT_ANY_CLASS_CAN_DEPEND_ON)
                              .toArray(new String[0]))))
              .allowEmptyShould(true);

  @ArchTest
  public static final ArchRule service_should_only_depend_on_port_layer_or_common_classes =
      classes()
          .that()
          .resideInAnyPackage(matchAllClassesInPackage(APPLICATION_PORT_PACKAGE))
          .should()
          .onlyDependOnClassesThat(
              or(
                  JavaClass.Predicates.resideInAPackage(
                      matchAllClassesInPackage(APPLICATION_PORT_PACKAGE)),
                  JavaClass.Predicates.resideInAnyPackage(
                      matchAllClassesInPackages(PACKAGES_THAT_ANY_CLASS_CAN_DEPEND_ON)
                          .toArray(new String[0]))))
          .allowEmptyShould(true);

  // adapter layer
  @ArchTest
  public static final ArchRule adapter_layer_should_not_depend_on_bootstrap_layer =
      noClasses()
          .that()
          .resideInAnyPackage(matchAllClassesInPackage(ADAPTER_PACKAGE))
          .should()
          .dependOnClassesThat()
          .resideInAPackage(matchAllClassesInPackage(BOOTSTRAP_PACKAGE))
          .allowEmptyShould(true);

  // common layer
  @ArchTest
  public static final ArchRule common_layer_should_only_depend_on_self_or_common_classes =
      classes()
          .that()
          .resideInAPackage(matchAllClassesInPackage(COMMON_PACKAGE))
          .should()
          .onlyDependOnClassesThat(
              or(
                  JavaClass.Predicates.resideInAPackage(matchAllClassesInPackage(COMMON_PACKAGE)),
                  JavaClass.Predicates.resideInAnyPackage(
                      matchAllClassesInPackages(PACKAGES_THAT_ANY_CLASS_CAN_DEPEND_ON)
                          .toArray(new String[0]))))
          .allowEmptyShould(true);
}
