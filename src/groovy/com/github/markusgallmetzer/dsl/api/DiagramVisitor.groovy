package com.github.markusgallmetzer.dsl.api

import com.github.markusgallmetzer.dsl.api.model.Diagram

/**
 * @author Markus Gallmetzer
 */
interface DiagramVisitor {
  def <T> T visit(Diagram diagram)
}
