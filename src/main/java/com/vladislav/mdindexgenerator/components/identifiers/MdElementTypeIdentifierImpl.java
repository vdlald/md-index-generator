package com.vladislav.mdindexgenerator.components.identifiers;

public class MdElementTypeIdentifierImpl implements MdElementTypeIdentifier {

  @Override
  public MdElementType identify(String line) {
    if (line.isEmpty()) {
      return MdElementType.OTHER;
    }

    if (line.startsWith("#")) {
      final char[] chars = new char[7];
      line.getChars(0, 7, chars, 0);
      for (final char aChar : chars) {
        if (aChar == ' ') {
          return MdElementType.HEADER;
        } else if (aChar != '#') {
          return MdElementType.OTHER;
        }
      }
      return MdElementType.OTHER;
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
