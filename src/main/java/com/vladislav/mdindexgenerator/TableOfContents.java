package com.vladislav.mdindexgenerator;

import java.util.ArrayList;
import java.util.List;

public class TableOfContents {

  private final List<HeadElement> headers = new ArrayList<>();

  public void addHeader(HeadElement headElement) {
    headers.add(headElement);
  }
}
