package com.example.adapter.in.rest.common;

/**
 * An error entity with a status and message returned via REST API in case of an error.
 *
 * @author Sven Woltmann
 */
record ErrorEntity(int httpStatus, String errorMessage) {}
