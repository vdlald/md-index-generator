package com.vladislav.mdindexgenerator.components.identifiers;

public class MdElementTypeIdentifierImpl implements MdElementTypeIdentifier {

  @Override
  public MdElementType identify(String line) {
    if (line.isEmpty()) {
      return MdElementType.OTHER;
    }

    if (line.startsWith("#")) {
      return MdElementType.HEADER;
    } else if (line.startsWith("    ")) {
      return MdElementType.CODE_BLOCK;
    } else if (line.startsWith("```")) {
      if (line.length() > 3 && line.endsWith("```")) {
        return MdElementType.CODE_BLOCK;
      }
      final String substring = line.substring(3).trim();
      if (substring.isEmpty()) {
        return MdElementType.CODE_BLOCK_END;
      } else {
        return MdElementType.CODE_BLOCK_START;
      }
    } else {
      return MdElementType.OTHER;
    }
  }
}
