package com.example.bootstrap.archunit.common;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import java.util.List;
import java.util.stream.Collectors;

public class ArchUnitUtil {

  public static String fullQualifiedPackage(String relativePackage) {
    return ArchUnitConfig.BASE_PACKAGE + "." + relativePackage;
  }

  public static String matchAllClassesInPackage(String packageName) {
    return packageName + "..";
  }

  public static List<String> matchAllClassesInPackages(List<String> packageNames) {
    return packageNames.stream()
        .map(ArchUnitUtil::matchAllClassesInPackage)
        .collect(Collectors.toList());
  }

  public static void denyDependency(
      String fromPackageName, String toPackageName, JavaClasses classes) {
    noClasses()
        .that()
        .resideInAPackage(matchAllClassesInPackage(fromPackageName))
        .should()
        .dependOnClassesThat()
        .resideInAnyPackage(matchAllClassesInPackage(toPackageName))
        .check(classes);
  }

  public static void denyAnyDependency(
      List<String> fromPackages, List<String> toPackages, JavaClasses classes) {
    for (String fromPackage : fromPackages) {
      for (String toPackage : toPackages) {
        denyDependency(fromPackage, toPackage, classes);
      }
    }
  }
}
