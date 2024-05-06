package com.example.bootstrap.archunit;

import static com.example.bootstrap.archunit.common.ArchUnitConfig.APPLICATION_SERVICE_PACKAGE;
import static com.example.bootstrap.archunit.common.ArchUnitConfig.BASE_PACKAGE;
import static com.example.bootstrap.archunit.common.ArchUnitUtil.matchAllClassesInPackage;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

import com.example.bootstrap.archunit.common.condition.ImplementOnlyOneInterfaceCondition;
import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.conditions.ArchConditions;

/**
 * @author lvhao09
 * @date 2024/4/30
 */
@AnalyzeClasses(
    packages = {BASE_PACKAGE},
    importOptions = {ImportOption.DoNotIncludeTests.class, ImportOption.DoNotIncludeJars.class})
public class InheritanceRuleTest {

  // application layer
  @ArchTest
  public static ArchRule service_should_implement_only_one_use_cases =
      classes()
          .that()
          .resideInAPackage(matchAllClassesInPackage(APPLICATION_SERVICE_PACKAGE))
          .should(
              ArchConditions.and(
                  new ImplementOnlyOneInterfaceCondition(),
                  ArchConditions.implement(JavaClass.Predicates.simpleNameEndingWith("UseCase"))))
          .allowEmptyShould(true);
}
