package com.github.markusgallmetzer.dsl.api.model


import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

import static com.github.markusgallmetzer.dsl.builder.runClosure

/**
 * @author Markus Gallmetzer
 */
@ToString(includeFields = true, includeNames = true)
@EqualsAndHashCode
final class Grid {
  private UUID id
  private int padding
  private HorizontalAlignment alignment
  private List<Row> rows

  Grid() {
    id = UUID.randomUUID()
    padding = 20
    alignment = HorizontalAlignment.CENTER
    rows = new LinkedList<>()
  }

  Grid(UUID id, int padding, HorizontalAlignment alignment, List<Row> rows) {
    this.id = id
    this.padding = padding
    this.alignment = alignment
    this.rows = rows
  }

  UUID getId() {
    id
  }

  int getPadding() {
    padding
  }

  HorizontalAlignment getAlignment() {
    alignment
  }

  List<Row> getRows() {
    rows
  }

  Grid alignment(HorizontalAlignment alignment) {
    this.alignment = alignment
    this
  }

  Grid padding(int value) {
    this.padding = value
    this
  }

  Grid row(Closure<Row> definition) {
    Row newRow = new Row()
    runClosure(newRow, definition)
    this.rows.add(newRow)
    this
  }
}
