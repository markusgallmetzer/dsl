package com.github.markusgallmetzer.dsl.api.model

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

/**
 * @author Markus Gallmetzer
 */
@ToString(includeFields = true, includeNames = true)
@EqualsAndHashCode
final class Border {
  private String color
  private float width

  Border() {
    this.color = Color.BLACK
    this.width = 1.5
  }

  Border(String color, float width) {
    this.color = color
    this.width = width
  }

  String getColor() {
    color
  }

  float getWidth() {
    width
  }

  Border color(String color) {
    this.color = color
    this
  }

  Border width(float width) {
    this.width = width
    this
  }
}
