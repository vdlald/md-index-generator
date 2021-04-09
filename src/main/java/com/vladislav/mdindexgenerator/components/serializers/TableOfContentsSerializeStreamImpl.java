package com.vladislav.mdindexgenerator.components.serializers;

import com.vladislav.mdindexgenerator.pojo.HeadElement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TableOfContentsSerializeStreamImpl implements TableOfContentsSerializeStream {

  private final Iterator<HeadElement> headers;

  private short lastLevel = 1;
  private final String tab;
  private final Map<String, Integer> links = new HashMap<>();
  private final Map<Short, Integer> levelsCount = new HashMap<>(6) {{
    put((short) 1, 1);
    put((short) 2, 1);
    put((short) 3, 1);
    put((short) 4, 1);
    put((short) 5, 1);
    put((short) 6, 1);
  }};

  @Override
  public boolean hasNext() {
    return headers.hasNext();
  }

  @Override
  public String nextLine() {
    final HeadElement headElement = headers.next();

    // prepare
    final String line = headElement.getLine();
    final String content = line.substring(headElement.getLevel()).trim();
    final short level = headElement.getLevel();
    final String link = headElement.getLink();

    // logic
    final String tabs = tab.repeat(level - 1);

    String duplicateId = "";

    // check duplicate headers
    final Integer count = links.get(link);
    if (count == null) {
      links.put(link, 1);
    } else {
      links.put(link, count + 1);
      duplicateId = "-" + count;
    }

    // reset level
    if (Integer.compare(level, lastLevel) == -1) {
      levelsCount.put(lastLevel, 1);
    }

    // add number to header
    final Integer levelCount = levelsCount.get(level);
    levelsCount.put(level, levelCount + 1);

    lastLevel = level;

    return String.format("%s%d. [%s](%s%s)", tabs, levelCount, content, link, duplicateId);
  }
}
