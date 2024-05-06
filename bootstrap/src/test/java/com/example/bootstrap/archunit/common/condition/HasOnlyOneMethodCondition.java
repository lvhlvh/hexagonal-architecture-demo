package com.example.bootstrap.archunit.common.condition;

import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ConditionEvents;
import com.tngtech.archunit.lang.SimpleConditionEvent;

public class HasOnlyOneMethodCondition extends ArchCondition<JavaClass> {

  public HasOnlyOneMethodCondition() {
    super("should have only one method");
  }

  @Override
  public void check(JavaClass item, ConditionEvents events) {
    int methodCount = item.getMethods().size();

    if (methodCount > 1) {
      String message =
          String.format(
              "%s should only have one method, but actually %d", item.getName(), methodCount);
      events.add(SimpleConditionEvent.violated(item, message));
    }
  }
}
