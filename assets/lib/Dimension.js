/**
 * @author Markus Gallmetzer
 */
class Dimension {
  constructor(width, height) {
    this.width = width;
    this.height = height;
  }

  padWidth(padding = 20) {
    return new Dimension(
      this.width + padding,
      this.height
    );
  }

  padHeight(padding = 20) {
    return new Dimension(
      this.width,
      this.height + padding
    );
  }

  pad(padding = 20) {
    return this.padWidth(padding).padHeight(padding);
  }
}
