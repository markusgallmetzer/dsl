package com.github.markusgallmetzer.dsl.api.model


import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

import static com.github.markusgallmetzer.dsl.builder.runClosure

/**
 * @author Markus Gallmetzer
 */
@ToString(includeFields = true, includeNames = true)
@EqualsAndHashCode
final class Row {
  private UUID id
  private int padding
  private VerticalAlignment alignment
  private List<Text> columns

  Row() {
    id = UUID.randomUUID()
    padding = 20
    alignment = VerticalAlignment.MIDDLE
    columns = new LinkedList<>()
  }

  Row(UUID id, int padding, VerticalAlignment alignment, List<Text> columns) {
    this.id = id
    this.padding = padding
    this.alignment = alignment
    this.columns = columns
  }

  UUID getId() {
    id
  }

  int getPadding() {
    padding
  }

  VerticalAlignment getAlignment() {
    alignment
  }

  List<Text> getColumns() {
    columns
  }

  Row padding(int value) {
    padding = value
    this
  }

  Row alignment(VerticalAlignment value) {
    alignment = value
    this
  }

  Row text(Closure<Text> definition) {
    Text newText = new Text()
    runClosure(newText, definition)
    this.columns.add(newText)
    this
  }
}
