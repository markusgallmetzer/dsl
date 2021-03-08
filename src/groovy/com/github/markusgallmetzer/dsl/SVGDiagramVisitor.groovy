package com.github.markusgallmetzer.dsl

import com.github.markusgallmetzer.dsl.api.DiagramVisitor
import com.github.markusgallmetzer.dsl.api.model.Diagram
import groovy.xml.MarkupBuilder

/**
 * @author Markus Gallmetzer
 */
class SVGDiagramVisitor implements DiagramVisitor {
  @Override
  String visit(Diagram diagram) {
    def writer = new StringWriter()
    def xml = new MarkupBuilder(writer)
    xml.setDoubleQuotes(true)

    xml.svg(id: diagram.getId(), xmlns: "http://www.w3.org/2000/svg") {
      svg(id: diagram.getGrid().getId()) {
        diagram.getGrid().getRows().forEach { row ->
          svg(id: row.getId()) {
            row.getColumns().forEach { txt ->
              svg(id: txt.getId()) {
                rect(id: "${txt.getId()}/border", height: 0, width: 0)
                text(id: "${txt.getId()}/text", 'dominant-baseline': "auto", txt.getLabel())
              }
            }
          }
        }
      }
    }

    return writer.toString()
  }
}
