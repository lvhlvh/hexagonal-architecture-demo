package com.example.bootstrap.archunit;

import static com.example.bootstrap.archunit.common.ArchUnitConfig.*;
import static com.example.bootstrap.archunit.common.ArchUnitUtil.matchAllClassesInPackage;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.conditions.ArchConditions;

@AnalyzeClasses(
    packages = {BASE_PACKAGE},
    importOptions = {ImportOption.DoNotIncludeTests.class, ImportOption.DoNotIncludeJars.class})
public class NamingConventionTest {

  // application layer
  @ArchTest
  public static ArchRule service_later_class_name_should_end_with_service =
      classes()
          .that()
          .resideInAPackage(matchAllClassesInPackage(APPLICATION_SERVICE_PACKAGE))
          .should()
          .haveSimpleNameEndingWith("Service")
          .allowEmptyShould(true);

  @ArchTest
  public static ArchRule
      incoming_port_layer_class_name_should_end_with_useCase_or_command_or_query_or_exception =
          classes()
              .that()
              .resideInAPackage(matchAllClassesInPackage(APPLICATION_IN_PORT_PACKAGE))
              .should()
              .haveNameMatching("^.*(UseCase|Command|Query|Exception)$")
              .allowEmptyShould(true);

  @ArchTest
  public static ArchRule
      outgoing_port_layer_class_name_should_end_with_port_or_command_or_query_or_exception =
          classes()
              .that()
              .resideInAPackage(matchAllClassesInPackage(APPLICATION_OUT_PORT_PACKAGE))
              .should()
              .haveNameMatching("^.*(Port|Command|Query|Exception)$")
              .allowEmptyShould(true);

  // adapter layer
  @ArchTest
  public static ArchRule incoming_rest_adapter_layer_name_should_end_with_controller_or_converter =
      classes()
          .that()
          .resideInAPackage(matchAllClassesInPackage(IN_ADAPTER_PACKAGE + ".rest"))
          .should(
              ArchConditions.or(
                  ArchConditions.haveSimpleNameEndingWith("Controller"),
                  ArchConditions.haveNameMatching(".*Converter(Impl(\\$\\d+)?)?")))
          .allowEmptyShould(true);

  @ArchTest
  public static ArchRule
      incoming_rpc_adapter_layer_name_should_end_with_ThriftServiceAdapter_or_converter =
          classes()
              .that()
              .resideInAPackage(matchAllClassesInPackage(IN_ADAPTER_PACKAGE + ".rpc"))
              .should(
                  ArchConditions.or(
                      ArchConditions.haveSimpleNameEndingWith("ThriftServiceAdapter"),
                      ArchConditions.haveNameMatching(".*Converter(Impl(\\$\\d+)?)?")))
              .allowEmptyShould(true);

  @ArchTest
  public static ArchRule outgoing_rpc_adapter_layer_name_should_end_with_gateway_or_converter =
      classes()
          .that()
          .resideInAPackage(matchAllClassesInPackage(OUT_ADAPTER_PACKAGE + ".rpc"))
          .should(
              ArchConditions.or(
                  ArchConditions.haveSimpleNameEndingWith("Gateway"),
                  ArchConditions.haveNameMatching(".*Converter(Impl(\\$\\d+)?)?")))
          .allowEmptyShould(true);
}
