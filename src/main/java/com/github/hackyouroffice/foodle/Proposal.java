package com.github.hackyouroffice.foodle;

public class Proposal {

  private final String title;
  private final String text;

  public Proposal(String title, String text) {
    this.title = title;
    this.text = text;
  }

  public String getTitle() {
    return title;
  }

  public String getText() {
    return text;
  }
}
