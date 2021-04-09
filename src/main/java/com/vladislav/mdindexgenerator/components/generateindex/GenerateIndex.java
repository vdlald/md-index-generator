package com.vladislav.mdindexgenerator.components.generateindex;

import com.vladislav.mdindexgenerator.components.mdparser.TableOfContentsParser;
import com.vladislav.mdindexgenerator.components.serializers.TableOfContentsSerializeStreamFactory;
import com.vladislav.mdindexgenerator.pojo.HeadElement;
import com.vladislav.mdindexgenerator.pojo.TableOfContents;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.function.Consumer;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GenerateIndex implements Runnable {

  private final BufferedReader in;
  private final Consumer<String> out;
  private final TableOfContentsParser tableOfContentsParser;
  private final TableOfContentsSerializeStreamFactory streamFactory;

  @Override
  public void run() {
    final TableOfContents tableOfContents;

    // parse headers
    try {
      tableOfContents = tableOfContentsParser.parse(in);
    } catch (IOException e) {
      throw new GenerateIndexException(e);
    }

    // create serialize stream
    final Iterator<HeadElement> iterator = tableOfContents.getHeaders().iterator();
    final var serializeStream = streamFactory.createWithSpaceTab(iterator);

    // print index
    while (serializeStream.hasNext()) {
      out.accept(serializeStream.nextLine());
    }
  }
}
