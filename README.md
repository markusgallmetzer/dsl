# Domain Specific Languages

## Module

| Pfad            | Modul                       |
| ---             | ---                         |
| `presentation/` | Eine RevealJS Präsentation. |

## Groovy Diagramme

"Einfache" Groovy DSL.

### Anforderungen

| Software             | Version | URL                                     | Kommentar                      |
| ---                  | ---     | ---                                     | ---                            |
| Groovy SDK           | 3.0.7   | https://groovy.apache.org/download.html | Nicht nur das Binary; das SDK! |
| Java Development Kit | > 8     | https://adoptopenjdk.net/               |                                |

**Bitte beachten:** _IntelliJ_ hatte Probleme mit einem JDK, Version > 8. Auf der Kommandozeile funktioniert (bei mir)
auch JDK 15.

### Installation

Beide Archive entpacken und das `bin/` in den Pfad. Das Setup hat funktioniert, wenn:

```shell
$ java -version
openjdk version "15.0.2" 2021-01-19
OpenJDK Runtime Environment AdoptOpenJDK (build 15.0.2+7)
OpenJDK 64-Bit Server VM AdoptOpenJDK (build 15.0.2+7, mixed mode, sharing)

$ groovy -v                                                                                
Groovy Version: 3.0.7 JVM: 15.0.2 Vendor: AdoptOpenJDK OS: Mac OS X
```

### Verwendung

Aktuelles Arbeitsverzeichnis: `./`

```shell
groovy -cp ./src/groovy \
  ./bin/GroovyDiagrams.groovy \
  ./demo/test.tpl \
  ./output/test.html
```


