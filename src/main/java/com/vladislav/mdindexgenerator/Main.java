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
import lombok.SneakyThrows;

public class Main {

  @SneakyThrows
  public static void main(String[] args) {

    // get path to file
    if (args.length == 0) {
      System.out.println("You need to specify the path to the md file.");
      return;
    }
    final String pathToMdFile = args[0];

    // create dependencies
    final var streamFactory = new TableOfContentsSerializeStreamFactory();
    final MdElementTypeIdentifier identifier = new MdElementTypeIdentifierImpl();
    final var tableOfContentsParser = new TableOfContentsParser(identifier);

    // create reader
    BufferedReader bufferedReader;
    Reader fileReader;
    try {
      fileReader = new FileReader(pathToMdFile);
      bufferedReader = new BufferedReader(fileReader);
    } catch (FileNotFoundException e) {
      System.out.println("File not found.");
      return;
    }

    // create app
    new GenerateIndex(
        bufferedReader,
        System.out::println,
        tableOfContentsParser,
        streamFactory
    ).run();

    bufferedReader.close();
    System.out.println();

    // recreate readers
    fileReader = new FileReader(pathToMdFile);
    bufferedReader = new BufferedReader(fileReader);

    // print file
    String line;
    while ((line = bufferedReader.readLine()) != null) {
      System.out.println(line);
    }

    bufferedReader.close();
  }
}
