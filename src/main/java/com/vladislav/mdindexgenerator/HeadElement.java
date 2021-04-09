package com.vladislav.mdindexgenerator;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString
@RequiredArgsConstructor
public class HeadElement {

  private static final byte SYMBOL = '#';
  private static final Set<Integer> allowedChars = "abcdefghijklmnopqrstuvwxyz123456789 ".chars()
      .boxed()
      .collect(Collectors.toUnmodifiableSet());

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
    final StringBuilder result = new StringBuilder().append('#');

    final String lowerCase = line.substring(getLevel()).trim().toLowerCase();

    final Character[] filtered = lowerCase.chars()
        .filter(allowedChars::contains)
        .mapToObj(value -> (char) value)
        .toArray(Character[]::new);

    char lastChar = '\0';
    for (Character value : filtered) {
      Character character = value;

      if (character == ' ')
        character = '-';

      if (lastChar == character && lastChar == '-') {
        continue;
      } else {
        lastChar = character;
        result.append(character);
      }
    }

    // TODO: 4/8/21 implement
    return result.toString();
  }
}
