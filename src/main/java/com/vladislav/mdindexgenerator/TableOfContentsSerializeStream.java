package com.vladislav.mdindexgenerator;

import java.util.Iterator;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TableOfContentsSerializeStream {

  private final Iterator<HeadElement> headers;

  public boolean hasNext() {
    return headers.hasNext();
  }

  public String nextLine() {
    final HeadElement headElement = headers.next();

    final String line = headElement.getLine();

    final String content = line.substring(headElement.getLevel()).trim();
    final short level = headElement.getLevel();
    final String link = headElement.getLink();
    final String tabs = "\t".repeat(level);

    return String.format("%s[%s](%s)", tabs, content, link);
  }
}
