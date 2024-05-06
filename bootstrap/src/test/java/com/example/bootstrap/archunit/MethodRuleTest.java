package com.example.bootstrap.archunit;

import static com.example.bootstrap.archunit.common.ArchUnitConfig.BASE_PACKAGE;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

import com.example.bootstrap.archunit.common.condition.HasOnlyOneMethodCondition;
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
public class MethodRuleTest {

  // application layer
  @ArchTest
  public static ArchRule ports_should_have_only_one_method =
      classes()
          .that()
          .haveNameMatching("^.*(UseCase|Port)$")
          .should(new HasOnlyOneMethodCondition())
          .allowEmptyShould(true);
}
