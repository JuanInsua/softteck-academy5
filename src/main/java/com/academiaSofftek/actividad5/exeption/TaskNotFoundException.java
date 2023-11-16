package com.academiaSofftek.actividad5.exeption;

/**
 * Custom exception class representing an exception thrown when a task is not found.
 * Extends {@link CustomedHandler}, a custom exception handler class.
 */
public class TaskNotFoundException extends CustomedHandler {

    /**
     * Constructs a new TaskNotFoundException with the specified error message.
     *
     * @param message The detail message explaining the reason for the exception.
     */
    public TaskNotFoundException(String message) {
        super(message);
    }

    /**
     * Returns a string representation of this TaskNotFoundException.
     * Overrides the toString method to include the error message and the date of occurrence.
     *
     * @return A string representation of this TaskNotFoundException, including error message and date.
     */
    @Override
    public String toString() {
        return super.getMessage() + " " + super.getDate();
    }
}

