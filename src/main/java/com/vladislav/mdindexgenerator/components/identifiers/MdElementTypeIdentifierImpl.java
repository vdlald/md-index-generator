package com.vladislav.mdindexgenerator.components.identifiers;

public class MdElementTypeIdentifierImpl implements MdElementTypeIdentifier {

  @Override
  public MdElementType identify(String line) {
    if (line.startsWith("#")) {
      return MdElementType.HEADER;
    } else {
      return MdElementType.OTHER;
    }
  }
}