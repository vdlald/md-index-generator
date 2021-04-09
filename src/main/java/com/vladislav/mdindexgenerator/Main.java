package com.vladislav.mdindexgenerator;

import com.vladislav.mdindexgenerator.components.generateindex.GenerateIndex;
import com.vladislav.mdindexgenerator.components.identifiers.MdElementTypeIdentifier;
import com.vladislav.mdindexgenerator.components.identifiers.MdElementTypeIdentifierImpl;
import com.vladislav.mdindexgenerator.components.mdparser.TableOfContentsParser;
import com.vladislav.mdindexgenerator.components.serializers.TableOfContentsSerializeStreamFactory;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.function.Consumer;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.SneakyThrows;

@RequiredArgsConstructor
public class Main implements Runnable {

  private final String[] args;

  @Setter  // for testing
  private MdElementTypeIdentifier identifier = new MdElementTypeIdentifierImpl();
  @Setter  // for testing
  private TableOfContentsParser tableOfContentsParser = new TableOfContentsParser(identifier);
  @Setter  // for testing
  private TableOfContentsSerializeStreamFactory streamFactory = new TableOfContentsSerializeStreamFactory();
  @Setter
  private Consumer<String> out = System.out::println;

  public static void main(String[] args) {
    new Main(args).run();
  }

  @Override
  @SneakyThrows
  public void run() {
    // get path to file
    if (args.length == 0) {
      System.out.println("You need to specify the path to the md file.");
      return;
    }
    final String pathToMdFile = args[0];

    // create reader
    BufferedReader bufferedReader;
    Reader fileReader;
    try {
      fileReader = new FileReader(pathToMdFile);
      bufferedReader = new BufferedReader(fileReader);
    } catch (FileNotFoundException e) {
      out.accept("File not found.");
      return;
    }

    // create app
    new GenerateIndex(
        bufferedReader,
        out,
        tableOfContentsParser,
        streamFactory
    ).run();

    bufferedReader.close();
    out.accept("");  // new line

    // recreate readers
    fileReader = new FileReader(pathToMdFile);
    bufferedReader = new BufferedReader(fileReader);

    // print file
    String line;
    while ((line = bufferedReader.readLine()) != null) {
      out.accept(line);
    }

    bufferedReader.close();
  }
}
