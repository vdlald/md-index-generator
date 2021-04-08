package com.vladislav.mdindexgenerator;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class App implements Runnable {

  private final String pathToMdFile;
  private final MdElementTypeIdentifier elementTypeIdentifier;

  public static void main(String[] args) {

    // get path to file
    if (args.length == 0) {
      System.out.println("You need to specify the path to the md file.");
      return;
    }
    final String pathToMdFile = args[0];

    // create dependencies
    final MdElementTypeIdentifier identifierStub = new MdElementTypeIdentifierImpl();

    // create app
    new App(
        pathToMdFile,
        identifierStub
    ).run();
  }

  @Override
  public void run() {
    // create reader
    final BufferedReader bufferedReader;
    try {
      final FileReader fileReader = new FileReader(pathToMdFile);
      bufferedReader = new BufferedReader(fileReader);
    } catch (FileNotFoundException e) {
      System.out.println("File not found.");
      return;
    }

    final TableOfContents tableOfContents = new TableOfContents();

    // parse file
    String line;
    try {
      while ((line = bufferedReader.readLine()) != null) {
        line = line.trim();
        final MdElementType type = elementTypeIdentifier.identify(line);

        if (type != MdElementType.HEADER) {
          continue;
        }

        final HeadElement headElement = new HeadElement(line);
        tableOfContents.addHeader(headElement);
      }

      bufferedReader.close();
    } catch (IOException e) {
      e.printStackTrace();
      return;
    }

    final Iterator<HeadElement> iterator = tableOfContents.getHeaders().iterator();
    final var serializeStream = new TableOfContentsSerializeStream(iterator);

    while (serializeStream.hasNext()) {
      System.out.println(serializeStream.nextLine());
    }
  }
}
