const gridDefaultOptions = {
  padding: 20,
  alignment: 'CENTER'
};

/**
 * @author Markus Gallmetzer
 */
class GridComponent {
  constructor(wrapper,
              rows,
              options = gridDefaultOptions) {
    this.options = Object.assign(gridDefaultOptions, options);
    this.geometry = new Box(
      new Point(0, 0),
      new Dimension(0, 0)
    );
    this.wrapper = wrapper;
    this.components = rows;
    this.updateDimensions();
    this.updateCoordinates();
    this.setCoordinates(this.geometry.origin);
  }

  updateDimensions() {
    const dimensions = this.components.map(component => component.geometry.dimension);

    const unpaddedWidth = this.max(dimensions, dimension => dimension.width);
    const unpaddedHeight = this.accumulate(dimensions, dimension => dimension.height);

    const newDimensions = new Dimension(
      unpaddedWidth + (this.components.length - 1) * (this.options.padding / 2) + this.options.padding,
      unpaddedHeight + this.options.padding
    );

    this.wrapper.setDimensions(newDimensions);
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

    let nextY = this.options.padding / 2;

    for (let index = 0; index < this.components.length; index++) {
      const cellDimension = this.components[index].getDimensions();

      const nextX = this.calculateXCoordinate(dimensions.width, cellDimension.width);

      this.components[index].setCoordinates(new Point(nextX, nextY));

      nextY = nextY + cellDimension.height + (this.options.padding / 2);
    }
  }

  accumulate(dimensions, projection, padding = 20) {
    return dimensions.map(projection).reduce((previous, current) => previous + current + padding);
  }

  max(dimensions, projection) {
    return Math.max(...dimensions.map(dimension => projection(dimension)));
  }

  calculateXCoordinate(gridWidth, rowWidth) {
    if (this.isLeftAligned()) {
      return this.options.padding / 2;
    } else if (this.isCenterAligned()) {
      return (gridWidth - rowWidth) / 2
    } else if (this.isRightAligned()) {
      return gridWidth - rowWidth - (this.options.padding / 2);
    }
  }

  isLeftAligned() {
    return this.isAligned('LEFT');
  }

  isRightAligned() {
    return this.isAligned('RIGHT');
  }

  isCenterAligned() {
    return this.isAligned('CENTER');
  }

  isAligned(value) {
    return this.options.alignment === value;
  }

}
