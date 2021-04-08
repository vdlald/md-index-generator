package com.vladislav.mdindexgenerator;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class App implements Runnable {

  private final String pathToMdFile;

  public static void main(String[] args) {
    if (args.length == 0) {
      System.out.println("You need to specify the path to the md file.");
      return;
    }

    final String pathToMdFile = args[0];

    new App(pathToMdFile).run();
  }

  @Override
  public void run() {

  }
}
