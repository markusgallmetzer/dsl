package com.github.markusgallmetzer.dsl

import com.github.markusgallmetzer.dsl.api.AssetService
import com.github.markusgallmetzer.dsl.api.DiagramVisitor
import com.github.markusgallmetzer.dsl.api.model.Diagram
import groovy.text.Template
import groovy.text.markup.MarkupTemplateEngine
import groovy.text.markup.TemplateConfiguration

/**
 * @author Markus Gallmetzer
 */
class OutputTemplateService {
  private final AssetService assetService
  private final File base
  private final DiagramVisitor svgVisitor
  private final DiagramVisitor codeVisitor
  private final File htmlTemplate

  OutputTemplateService(AssetService assetService, File base) {
    this.assetService = assetService
    this.base = base
    svgVisitor = new SVGDiagramVisitor()
    codeVisitor = new InitialisationScriptVisitor()
    htmlTemplate = assetService.resolve("diagram.tpl")
  }

  def applyTemplate(File output, Diagram diagram) {
    def model = [
      scripts : [
        "lib/Point.js",
        "lib/Dimension.js",
        "lib/Box.js",
        "lib/SVGComponent.js",
        "lib/TextNodeComponent.js",
        "lib/RowComponent.js",
        "lib/GridComponent.js"
      ].collect {
        assetService.resolve(output, it)
      },
      renderedSvg: svgVisitor.visit(diagram),
      genscript: codeVisitor.visit(diagram)
    ]

    TemplateConfiguration config = new TemplateConfiguration()
    config.useDoubleQuotes = true
    config.autoIndent = true
    config.autoNewLine = true
    config.expandEmptyElements = true

    MarkupTemplateEngine engine = new MarkupTemplateEngine(config)

    Template template = engine.createTemplate(htmlTemplate)

    Writable templateOutput = template.make(model)
    def writer = new StringWriter()
    templateOutput.writeTo(writer)

    output.write(writer.toString())
  }

}
