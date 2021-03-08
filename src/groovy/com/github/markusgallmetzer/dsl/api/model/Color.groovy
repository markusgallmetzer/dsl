package com.github.markusgallmetzer.dsl.api.model

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

/**
 * @author Markus Gallmetzer
 */
@ToString(includeFields = true, includeNames = true)
@EqualsAndHashCode
final class Color {
  final static String BLACK = "#000000";
  final static String WHITE = "#ffffff";

  private String hexString

  Color(String hexString) {
    this.hexString = hexString
  }

  String getHexString() {
    hexString
  }
}
