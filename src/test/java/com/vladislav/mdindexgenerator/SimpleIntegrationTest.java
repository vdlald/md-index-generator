package com.vladislav.mdindexgenerator;

import static org.junit.Assert.assertEquals;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SimpleIntegrationTest {

  private final Main main;
  private final String expected;
  private final StringBuffer out;

  public void test() {
    main.run();
    final String result = out.toString();

    assertEquals(expected, result);
  }
}
