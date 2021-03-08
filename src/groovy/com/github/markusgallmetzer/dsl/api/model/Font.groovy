package com.github.markusgallmetzer.dsl.api.model

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

/**
 * @author Markus Gallmetzer
 */
@ToString(includeFields = true, includeNames = true)
@EqualsAndHashCode
final class Font {
  private boolean familyModified
  private int fontSize
  private List<String> fontFamily

  Font() {
    familyModified = false
    fontSize = 14
    fontFamily = [
      "Helvetica Neue",
      "Helvetica",
      "Arial",
      "sans-serif"
    ]
  }

  Font(int fontSize, List<String> fontFamily) {
    this.fontSize = fontSize
    this.fontFamily = fontFamily
  }

  int getFontSize() {
    fontSize
  }

  Set<String> getFontFamily() {
    fontFamily
  }

  Font size(int value) {
    this.fontSize = value
    this
  }

  Font family(String... family) {
    if (!familyModified) {
      fontFamily = new LinkedList<>()
      familyModified = true
    }
    fontFamily.addAll(family)
    this
  }
}
