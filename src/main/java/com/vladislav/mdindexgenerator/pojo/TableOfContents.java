package com.vladislav.mdindexgenerator.pojo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TableOfContents {

  private final List<HeadElement> headers = new LinkedList<>();

  public void addHeader(HeadElement headElement) {
    headers.add(headElement);
  }

  public List<HeadElement> getHeaders() {
    return new ArrayList<>(headers);
  }
}
