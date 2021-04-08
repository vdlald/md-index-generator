package com.vladislav.mdindexgenerator;

import java.util.Iterator;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TableOfContentsSerializeStream {

  private final Iterator<HeadElement> headers;

  private final short currentDepth = 0;

  public boolean hasNext() {
    return headers.hasNext();
  }

  public String nextLine() {
    return headers.next().toString();
  }
}
