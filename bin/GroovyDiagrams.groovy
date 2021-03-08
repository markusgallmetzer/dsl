
@Grab('info.picocli:picocli-groovy:4.6.1')

import com.github.markusgallmetzer.dsl.Runner
import groovy.transform.Field
import groovy.transform.SourceURI
import picocli.CommandLine
import picocli.groovy.PicocliScript2

import java.nio.file.Paths

/**
 * @author Markus Gallmetzer
 */

// https://stackoverflow.com/a/50475957
@SourceURI
URI source

@CommandLine.Command(
  name = "run",
  mixinStandardHelpOptions = true,
  version = "1.0.0",
  description = "Runs the specified DSL model."
)
@PicocliScript2

@CommandLine.Parameters(index = '0', description = "The template.")
@Field File template

@CommandLine.Parameters(index = '1', description = "The output file.")
@Field File output

def runner = new Runner(Paths.get(source))
runner.apply(template, output)


