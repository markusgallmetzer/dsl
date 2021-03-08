package com.github.markusgallmetzer.dsl.api

/**
 * @author Markus Gallmetzer
 */
class AssetService {
  private final File assetsBase

  AssetService(File assetsBase) {
    this.assetsBase = assetsBase
  }

  String resolve(File output, String asset) {
    File assetFile = new File(assetsBase, asset);

    if (!assetFile.exists()) {
      throw new IllegalArgumentException("Asset " + asset + " not found.")
    }

    output.parentFile.relativePath(assetFile)
  }

  File resolve(String asset) {
    File assetFile = new File(assetsBase, asset);
    if (!assetFile.exists()) {
      throw new IllegalArgumentException("Asset " + asset + " not found.")
    }
    assetFile
  }
}
