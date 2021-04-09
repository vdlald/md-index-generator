package com.vladislav.mdindexgenerator.components.serializers;

public interface TableOfContentsSerializeStream {

  boolean hasNext();

  String nextLine();
}
