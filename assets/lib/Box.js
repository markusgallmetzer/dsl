/**
 * @author Markus Gallmetzer
 */
class Box {
  constructor(origin, dimension) {
    this.origin = origin;
    this.dimension = dimension;
  }

  padWidth(padding = 20) {
    return new Box(
      this.origin,
      this.dimension.padWidth(padding)
    );
  }

  padHeight(padding = 20) {
    return new Box(
      this.origin,
      this.dimension.padHeight(padding)
    );
  }

  pad(padding = 20) {
    return new Box(
      this.origin,
      this.dimension.pad(padding)
    );
  }
}
