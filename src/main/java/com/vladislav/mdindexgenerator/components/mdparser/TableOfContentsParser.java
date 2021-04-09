package com.vladislav.mdindexgenerator.components.mdparser;

import com.vladislav.mdindexgenerator.components.identifiers.MdElementType;
import com.vladislav.mdindexgenerator.components.identifiers.MdElementTypeIdentifier;
import com.vladislav.mdindexgenerator.pojo.HeadElement;
import com.vladislav.mdindexgenerator.pojo.TableOfContents;
import java.io.BufferedReader;
import java.io.IOException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TableOfContentsParser {

  private final MdElementTypeIdentifier elementTypeIdentifier;

  public TableOfContents parse(BufferedReader in) throws IOException {
    final TableOfContents tableOfContents = new TableOfContents();
    String line;
    boolean skip = false;
    while ((line = in.readLine()) != null) {
      line = line.trim();
      final MdElementType type = elementTypeIdentifier.identify(line);

      switch (type) {
        case CODE_BLOCK_START -> {
          skip = true;
          continue;
        }
        case CODE_BLOCK_END -> {
          skip = false;
          continue;
        }
      }

      if (type != MdElementType.HEADER || skip) {
        continue;
      }

      final HeadElement headElement = new HeadElement(line);
      tableOfContents.addHeader(headElement);
    }
    return tableOfContents;
  }
}
