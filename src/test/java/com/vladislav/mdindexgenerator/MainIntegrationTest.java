package com.vladislav.mdindexgenerator;

import org.junit.Before;
import org.junit.Test;

public class MainIntegrationTest {

  private static final String path = MainIntegrationTest.class.getResource("/TestInput.md")
      .getPath();

  private String[] args;

  @Before
  public void setUp() {
    args = new String[]{path};
  }

  @Test
  public void test() {
    Main.main(args);
  }
}
