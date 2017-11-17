package com.github.hackyouroffice.foodle;

public class RandomLunchProposer implements LunchProposer {

  @Override
  public Proposal propose() {

    return new Proposal("Essen!", "Iss mal wieder Pizza!");
  }
}
