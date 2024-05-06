package com.example.bootstrap.archunit.common;

import com.tngtech.archunit.thirdparty.com.google.common.collect.ImmutableList;
import java.util.List;

public class ArchUnitConfig {

  public static final String BASE_PACKAGE = "com.example";

  // domain
  public static final String DOMAIN_PACKAGE = ArchUnitUtil.fullQualifiedPackage("domain");

  // application
  public static final String APPLICATION_PACKAGE = ArchUnitUtil.fullQualifiedPackage("application");
  public static final String APPLICATION_PORT_PACKAGE =
      ArchUnitUtil.fullQualifiedPackage("application.port");
  public static final String APPLICATION_IN_PORT_PACKAGE =
      ArchUnitUtil.fullQualifiedPackage("application.port.in");
  public static final String APPLICATION_OUT_PORT_PACKAGE =
      ArchUnitUtil.fullQualifiedPackage("application.port.out");
  public static final String APPLICATION_SERVICE_PACKAGE =
      ArchUnitUtil.fullQualifiedPackage("application.service");

  // adapter
  public static final String ADAPTER_PACKAGE = ArchUnitUtil.fullQualifiedPackage("adapter");
  public static final String IN_ADAPTER_PACKAGE = ArchUnitUtil.fullQualifiedPackage("adapter.in");
  public static final String OUT_ADAPTER_PACKAGE = ArchUnitUtil.fullQualifiedPackage("adapter.out");

  // bootstrap
  public static final String BOOTSTRAP_PACKAGE = ArchUnitUtil.fullQualifiedPackage("bootstrap");

  // common
  public static final String COMMON_PACKAGE = ArchUnitUtil.fullQualifiedPackage("common");

  // common packages
  public static final List<String> PACKAGES_THAT_ANY_CLASS_CAN_DEPEND_ON =
      ImmutableList.of(
          "java",
          "javax",
          "jakarta.validation",
          "lombok",
          ArchUnitUtil.fullQualifiedPackage("common"),
          ArchUnitUtil.fullQualifiedPackage("domain"));
}
