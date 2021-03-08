package com.github.markusgallmetzer.dsl.api.model


import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

import static com.github.markusgallmetzer.dsl.builder.runClosure

/**
 * @author Markus Gallmetzer
 */
@ToString(includeFields = true, includeNames = true)
@EqualsAndHashCode
final class Text {
  private UUID id
  private int padding
  private Border border
  private Font font
  private String background
  private String label

  Text() {
    id = UUID.randomUUID()
    padding = 20
    background = Color.WHITE

    border = new Border()
    font = new Font()
  }

  Text(UUID id, int padding, Border border, Font font, String background, String label) {
    this.id = id
    this.padding = padding
    this.border = border
    this.font = font
    this.background = background
    this.label = label
  }

  UUID getId() {
    id
  }

  int getPadding() {
    padding
  }

  Border getBorder() {
    border
  }

  Font getFont() {
    font
  }

  String getBackground() {
    background
  }

  String getLabel() {
    label
  }

  Text padding(int padding) {
    this.padding = padding
    this
  }

  Text background(String color) {
    // validate...
    this.background = color
    this
  }

  Text label(String value) {
    this.label = value
    this
  }

  Text border(Closure<Border> definition) {
    Border newBorder = new Border()
    runClosure(newBorder, definition)
    border = newBorder
    this
  }

  Text font(Closure<Font> definition) {
    Font newFont = new Font()
    runClosure(newFont, definition)
    font = newFont
    this
  }
}
