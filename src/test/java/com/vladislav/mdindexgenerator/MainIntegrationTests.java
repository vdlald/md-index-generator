package com.vladislav.mdindexgenerator;

import java.nio.file.Files;
import java.nio.file.Path;
import lombok.SneakyThrows;
import org.junit.Test;

/**
 * This is where you register your integration tests.
 * <p>
 * DO NOT WRITE ACTUAL TESTS HERE! Only run them.
 */
public class MainIntegrationTests {

  private static final String integrationTestsFolderPath = "/integrationTestsData";

  @Test
  public void simpleTest() {
    final String expected = getOut();
    final String[] args = {getInPath()};
    final StringBuffer out = new StringBuffer();

    final Main main = setUpMain(args, out);

    new SimpleIntegrationTest(main, expected, out).test();
  }

  /**
   * Looks at the stacktrace to see which test triggered the method.
   * <p>
   * You must call this method at the beginning of the test, and the output file must be stored in
   * test/resource in a folder with the same name as the method
   *
   * @return expected out for specific test
   */
  @SneakyThrows
  private String getOut() {
    final String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();

    final String rawPath = MainIntegrationTests.class.getResource(String.format(
        "%s/%s/out.md",
        integrationTestsFolderPath,
        methodName
    )).getPath();

    final Path path = Path.of(rawPath);
    return new String(Files.readAllBytes(path));
  }

  /**
   * Looks at the stacktrace to see which test triggered the method.
   * <p>
   * You must call this method at the beginning of the test, and the input file must be stored in
   * test/resource in a folder with the same name as the method
   *
   * @return path to in file for specific test
   */
  @SneakyThrows
  private String getInPath() {
    final String path = String.format(
        "%s/%s/in.md",
        integrationTestsFolderPath,
        Thread.currentThread().getStackTrace()[2].getMethodName()
    );

    return MainIntegrationTests.class.getResource(path).getPath();
  }

  private Main setUpMain(String[] args, StringBuffer out) {
    Main main = new Main(args);
    main.setOut(s -> out.append(s).append('\n'));
    return main;
  }
}
