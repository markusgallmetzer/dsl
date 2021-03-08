package com.github.markusgallmetzer.dsl

import com.github.markusgallmetzer.dsl.api.DiagramVisitor
import com.github.markusgallmetzer.dsl.api.model.Diagram
import com.github.markusgallmetzer.dsl.api.model.Grid
import com.github.markusgallmetzer.dsl.api.model.Row
import com.github.markusgallmetzer.dsl.api.model.Text

/**
 * @author Markus Gallmetzer
 */
class InitialisationScriptVisitor implements DiagramVisitor {
  @Override
  String visit(Diagram diagram) {
    generateForDiagram(diagram)
  }

  static String generateForDiagram(Diagram diagram) {
    """
    const diagram = ${svgComponentById(diagram.id.toString())};
    
    const grid = ${generateForGrid(diagram.grid)};

    diagram.setDimensions(grid.getDimensions());
    
    console.log(grid);
    """
  }

  static String generateForGrid(Grid grid) {
    def wrapperElement = svgComponentById(grid.id.toString())

    """new GridComponent($wrapperElement, [ ${grid.rows.collect {
      generateForRow(it)
    }.join(", ")} ])"""
  }

  static String generateForRow(Row row) {
    def wrapperElement = svgComponentById(row.id.toString())

    """new RowComponent($wrapperElement, [${row.columns.collect {
      generateForText(it)
    }.join(", ")}])"""
  }

  static String generateForText(Text text) {
    def wrapperElement = svgComponentById(text.id.toString())
    def borderElement = svgComponentById("${text.id}/border")
    def textElement = svgComponentById("${text.id}/text")

    "new TextNodeComponent(${borderElement}, ${wrapperElement}, ${textElement})"
  }

  private static String svgComponentById(String id) {
    return "new SVGComponent(document.getElementById(\"${id}\"))"
  }
}
