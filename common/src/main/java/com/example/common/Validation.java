package com.example.common;

import static jakarta.validation.Validation.buildDefaultValidatorFactory;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import java.util.Set;

/**
 * 用于对入端口的参数校验(即校验command/query的各个字段是否合法) <a
 * href="https://github.com/thombergs/buckpal/blob/master/src/main/java/io/reflectoring/buckpal/common/validation/Validation.java">buckpal</a>
 */
public class Validation {

  // Your IDE may complain that the ValidatorFactory needs to be closed, but if we do that here,
  // we break the contract of ValidatorFactory#close.
  private static final Validator validator = buildDefaultValidatorFactory().getValidator();

  /** Evaluates all Bean Validation annotations on the subject. */
  public static <T> void validate(T subject) {
    Set<ConstraintViolation<T>> violations = validator.validate(subject);
    if (!violations.isEmpty()) {
      throw new ConstraintViolationException(violations);
    }
  }
}
