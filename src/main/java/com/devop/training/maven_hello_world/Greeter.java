package com.devop.training.maven_hello_world;

/**
 * This is a class.
 */
public class Greeter {

  /**
   * This is a constructor.
   */
  public Greeter() {

  }

  /**
   * @param someone of the person
   * @return Hello message
   */
  public final String greet(final String someone) {
    return String.format("Hello, %s!", someone);
  }
}
