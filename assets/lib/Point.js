/**
 * @author Markus Gallmetzer
 */
class Point {
  constructor(x,y) {
    this.x = x;
    this.y = y;
  }

  translate(by) {
    return new Point(
      this.x + by.x,
      this.y + by.y
    );
  }
}
