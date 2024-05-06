package com.example.bootstrap.archunit.common.condition;

import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ConditionEvents;
import com.tngtech.archunit.lang.SimpleConditionEvent;

public class ImplementOnlyOneInterfaceCondition extends ArchCondition<JavaClass> {
  public ImplementOnlyOneInterfaceCondition() {
    super("should implement only one interface");
  }

  @Override
  public void check(JavaClass item, ConditionEvents events) {
    int interfaceCount = item.getInterfaces().size();

    if (interfaceCount != 1) {
      String message =
          String.format(
              "%s should implement only one interface, but actually %d",
              item.getName(), interfaceCount);
      events.add(SimpleConditionEvent.violated(item, message));
    }
  }
}
