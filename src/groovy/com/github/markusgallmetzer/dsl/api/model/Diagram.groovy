package com.github.markusgallmetzer.dsl.api.model

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

import static com.github.markusgallmetzer.dsl.builder.runClosure

/**
 * @author Markus Gallmetzer
 */
@ToString(includeFields = true, includeNames = true)
@EqualsAndHashCode
final class Diagram {
  private UUID id
  private Grid grid

  Diagram() {
    this.id = UUID.randomUUID();
  }

  Diagram(UUID id, Grid grid) {
    this.id = id
    this.grid = grid
  }

  UUID getId() {
    id
  }

  Grid getGrid() {
    grid
  }

  Diagram grid(Closure<Grid> closure) {
    Grid newGrid = new Grid()
    runClosure(newGrid, closure)
    grid = newGrid
    this
  }
}
