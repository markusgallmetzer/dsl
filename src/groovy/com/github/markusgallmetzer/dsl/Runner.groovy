package com.github.markusgallmetzer.dsl

import com.github.markusgallmetzer.dsl.api.AssetService
import com.github.markusgallmetzer.dsl.api.model.*
import groovy.json.JsonOutput
import groovy.json.JsonSlurper

import java.nio.file.Files
import java.nio.file.Path

/**
 * @author Markus Gallmetzer
 */
class Runner {
  private final Path path
  private final AssetService assetService
  private final OutputTemplateService outputTemplateService

  Runner(Path path) {
    this.path = getBase(path)
    assetService = new AssetService(this.path.resolve("assets").toFile())
    outputTemplateService = new OutputTemplateService(assetService, this.path.toFile())
  }

  def apply(File input, File output) {
    def absInput = makeAbsolute(input)
    def absOutput = makeAbsolute(output)

    if (!absInput.isFile()) {
      throw new IllegalArgumentException("Input model $absInput not found")
    }
    if (absOutput.exists()) {
      throw new IllegalArgumentException("Output file $absOutput exists.")
    }

    def model = loadModel(absInput)


    outputTemplateService.applyTemplate(absOutput, model)
  }

  private static Diagram loadModel(File input) {
    def tmp = Files.createTempDirectory("groovy-diagrams-")
    def binding = new Binding()
    def engine = new GroovyScriptEngine([tmp.toFile().toURI().toURL()] as URL[])

    // The engine - actually - returns an instance of the Diagram class.
    // (Big) But: Groovy is not able to cast the result to the required type - even though the classes (or class names)
    // match.
    // Eventually a class version/serialization issue which can be solved by configuration. Anyways...serializing to a
    // JSON output format and parsing the JSON back to a Diagram works...
    thereAndBackAgain(engine.run(input.toString(), binding))
  }

  /**
   *
   * 1. Model -> JSON String
   * 2. JSON String -> Map
   * 3. Map -> Diagram
   *
   * @param model the result of the script call.
   * @return the model.
   */
  private static Diagram thereAndBackAgain(model) {
    def parser = new JsonSlurper()
    def jsonModelString = JsonOutput.toJson(model)
    def mapModel = parser.parseText(jsonModelString)
    mapJson(mapModel)

  }

  private static Diagram mapJson(obj) {
    new Diagram(
      UUID.fromString(obj.id as String),
      mapGrid(obj.grid)
    )
  }

  private static Grid mapGrid(obj) {
    new Grid(
      UUID.fromString(obj.id as String),
      obj.padding as Integer,
      HorizontalAlignment.valueOf(obj.alignment as String),
      obj.rows.collect {
        mapRow(it)
      }
    )
  }

  private static Row mapRow(obj) {
    new Row(
      UUID.fromString(obj.id as String),
      obj.padding as Integer,
      VerticalAlignment.valueOf(obj.alignment as String),
      obj.columns.collect {
        mapText(it)
      }
    )
  }

  private static Text mapText(obj) {
    new Text(
      UUID.fromString(obj.id as String),
      obj.padding as Integer,
      mapBorder(obj.border),
      mapFont(obj.font),
      obj.color as String,
      obj.label as String
    )
  }

  private static Border mapBorder(obj) {
    new Border(
      obj.color as String,
      obj.width as Float
    )
  }

  private static Font mapFont(obj) {
    new Font(
      obj.fontSize as Integer,
      obj.fontFamily
    )
  }

  private static Path getBase(Path script) {
    script.parent.parent
  }

  private static File makeAbsolute(File value) {
    return value.absoluteFile.toPath().normalize().toFile()
  }

}
