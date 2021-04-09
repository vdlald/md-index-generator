package com.vladislav.mdindexgenerator.components.serializers;

import com.vladislav.mdindexgenerator.pojo.HeadElement;
import java.util.Iterator;

public class TableOfContentsSerializeStreamFactory {

  @SuppressWarnings("unused")
  public TableOfContentsSerializeStream createWithSpaceTab(Iterator<HeadElement> headers) {
    return create(headers, "    ");
  }

  @SuppressWarnings("unused")
  public TableOfContentsSerializeStream createWithTab(Iterator<HeadElement> headers) {
    return create(headers, "\t");
  }

  @SuppressWarnings("unsed")
  public TableOfContentsSerializeStream create(Iterator<HeadElement> headers, String tab) {
    return new TableOfContentsSerializeStreamImpl(headers, tab);
  }
}
