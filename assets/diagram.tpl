yieldUnescaped '<!DOCTYPE html>'
html(lang:'en') {
  head {
    meta(charset: "UTF-8")
    meta(name:"viewport", content: "width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0")
    title("Diagram")
    scripts.forEach { path ->
      script(src: path)
    }
  }
  body {
    div(renderedSvg)
    script(type: "text/javascript", genscript)
  }
}
