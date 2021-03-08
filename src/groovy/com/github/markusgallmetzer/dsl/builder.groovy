package com.github.markusgallmetzer.dsl

import com.github.markusgallmetzer.dsl.api.model.Diagram

static <T, V> V runClosure(T self, Closure<V> definition) {
  final Closure<V> cloned = definition.rehydrate(self, self, definition.thisObject)
  cloned.resolveStrategy = Closure.DELEGATE_ONLY
  return cloned()
}

static Diagram diagram(Closure<Diagram> definition) {
  Diagram result = new Diagram()
  runClosure(result, definition)

  return result
}
