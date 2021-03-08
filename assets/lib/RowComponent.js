const rowDefaultOptions = {
  padding: 20,
  alignment: 'MIDDLE'
};

/**
 * @author Markus Gallmetzer
 */
class RowComponent {
  constructor(wrapper, cells, options = rowDefaultOptions) {
    this.options = Object.assign(rowDefaultOptions, options);
    this.geometry = new Box(
      new Point(0, 0),
      new Dimension(0, 0)
    );
    this.wrapper = wrapper;
    this.components = cells;

    this.updateDimensions();
    this.updateCoordinates();
    this.setCoordinates(this.geometry.origin);
  }

  updateDimensions() {
    const dimensions = this.components.map(component => component.geometry.dimension);

    const unpaddedWidth = this.accumulate(dimensions, dimension => dimension.width);
    const unpaddedHeight = this.max(dimensions, dimension => dimension.height);

    const newDimensions =  new Dimension(
      unpaddedWidth + (this.components.length - 1) * (this.options.padding / 2) + this.options.padding,
      unpaddedHeight + this.options.padding
    );

    this.wrapper.setDimensions(newDimensions)
    this.geometry.dimension = newDimensions;
  }

  getDimensions() {
    return this.geometry.dimension;
  }

  setCoordinates(point) {
    this.geometry.origin = point;
    this.wrapper.setCoordinates(point);
    this.updateCoordinates();
  }

  updateCoordinates() {
    const dimensions = this.getDimensions();

    let nextX = this.options.padding / 2;
    for (let index = 0; index < this.components.length; index++) {
      const cellDimension = this.components[index].getDimensions();

      const nextY = this.calculateYCoordinate(dimensions.height, cellDimension.height);

      let next = new Point(nextX, nextY); // nextY should be... 10
      this.components[index].setCoordinates(next);

      nextX = nextX + cellDimension.width + (this.options.padding / 2);
    }
  }

  accumulate(dimensions, projection, padding = 20) {
    return dimensions.map(projection).reduce((previous, current) => previous + current + padding);
  }

  max(dimensions, projection) {
    return Math.max(...dimensions.map(dimension => projection(dimension)));
  }

  calculateYCoordinate(rowHeight, cellHeight) {
    if (this.isTopAligned()) {
      return cellHeight + (this.options.padding / 2);
    } else if (this.isMiddleAligned()) {
      return (rowHeight - cellHeight) / 2;
    } else if (this.isBottomAligned()) {
      return rowHeight - (this.options.padding / 2);
    }
  }

  isTopAligned() {
    return this.isAligned('TOP');
  }

  isBottomAligned() {
    return this.isAligned('BOTTOM');
  }

  isMiddleAligned() {
    return this.isAligned('MIDDLE');
  }

  isAligned(value) {
    return this.options.alignment === value;
  }
}
