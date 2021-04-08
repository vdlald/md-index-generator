package com.vladislav.mdindexgenerator;

import java.nio.charset.StandardCharsets;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString
@RequiredArgsConstructor
public class HeadElement {

  private static final byte SYMBOL = '#';

  @Getter
  private final String line;

  public short getLevel() {
    short level = 0;
    final byte[] bytes = line.getBytes(StandardCharsets.UTF_8);

    for (int i = 0; i < 6; i++) {
      final byte aByte = bytes[i];
      if (aByte == SYMBOL) {
        level++;
      } else {
        break;
      }
    }

    return level;
  }

  public String getLink() {
    // TODO: 4/8/21 implement
    return line;
  }
}
