/**
 * @author Markus Gallmetzer
 */
class SVGComponent {
  constructor(domElement, options = {}) {
    this.element = domElement;

    let boundingBox = domElement.getBBox();

    this.geometry = new Box(
      new Point(0, 0),
      new Dimension(boundingBox.width, boundingBox.height)
    );
  }

  setCoordinates(point) {
    this.geometry.origin = point;
    this.updateSVGElement();
  }

  setDimensions(dimension) {
    this.geometry.dimension = dimension;
    this.updateSVGElement();
  }

  getDimensions() {
    return this.geometry.dimension;
  }

  updateSVGElement() {
    this.element.setAttribute('x', this.geometry.origin.x);
    this.element.setAttribute('y', this.geometry.origin.y);
    this.element.setAttribute('width', this.geometry.dimension.width);
    this.element.setAttribute('height', this.geometry.dimension.height);

  }
}
