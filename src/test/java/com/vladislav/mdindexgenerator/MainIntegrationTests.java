package com.vladislav.mdindexgenerator;

import static org.junit.Assert.assertEquals;

import java.nio.file.Files;
import java.nio.file.Path;
import lombok.SneakyThrows;
import org.junit.Test;

/**
 * This is where you register your integration tests.
 */
public class MainIntegrationTests {

  private static final String integrationTestsFolderPath = "/integrationTestsData";

  @Test
  public void simpleTest() {
    final String expected = getOut();
    final String[] args = {getInPath()};
    test(expected, args);
  }

  @Test
  public void nestedMdBlockTest() {
    final String expected = getOut();
    final String[] args = {getInPath()};
    test(expected, args);
  }

  @Test
  public void worstFormat() {
    final String expected = getOut();
    final String[] args = {getInPath()};
    test(expected, args);
  }

  private void test(final String expected, final String[] args) {
    final StringBuffer out = new StringBuffer();

    Main main = new Main(args);
    main.setOut(s -> out.append(s).append('\n'));

    main.run();
    final String result = out.toString();

    assertEquals(expected, result);
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
        "%s/%s/out",
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
        "%s/%s/in",
        integrationTestsFolderPath,
        Thread.currentThread().getStackTrace()[2].getMethodName()
    );

    return MainIntegrationTests.class.getResource(path).getPath();
  }
}
