const textNodeDefaultOptions = {
  padding: 20,
  background: '#ffffff',
  border: {
    color: '#000000',
    width: 1.5
  },
  font: {
    size: 14,
    family: '\'Helvetica Neue\',Helvetica,Arial,sans-serif',
    color: '#000000'
  }
};

/**
 * @author Markus Gallmetzer
 */
class TextNodeComponent {
  constructor(border, wrapper, text, options = textNodeDefaultOptions) {
    this.options = Object.assign(textNodeDefaultOptions, options);
    this.geometry = new Box(
      new Point(0, 0),
      text.getDimensions()
        .pad(this.options.padding)
        .pad(this.options.border.width)
    );

    this.border = border;
    this.wrapper = wrapper;
    this.text = text;

    this.applyStyles();
    this.updateDimensions();
    this.updateCoordinates();
    this.setCoordinates(this.geometry.origin);
  }


  getDimensions() {
    return this.geometry.dimension;
  }

  setCoordinates(point) {
    this.geometry.origin = point;
    this.wrapper.setCoordinates(point);
    this.updateCoordinates();
  }

  applyStyles() {
    // border
    this.border.element.setAttribute('stroke', this.options.border.color);
    this.border.element.setAttribute('stroke-width', this.options.border.width);
    this.border.element.setAttribute('fill', this.options.background);

    // text node
    this.text.element.setAttribute('style', `font-family: ${this.options.font.family};`);
    this.text.element.setAttribute('font-size', this.options.font.size);
    this.text.element.setAttribute('fill', this.options.font.color);
  }

  /**
   * Calculate this components dimensions based upon the dimensions of its wrapped components, i.e. the text node, the
   * border rectangle and the wrapper svg element. The dimensions contain the configured padding.
   */
  updateDimensions() {
    const textDimensions = this.text.getDimensions();
    [this.wrapper, this.border].forEach(element =>
      element.setDimensions(textDimensions.pad(this.options.padding))
    );
  }

  /**
   * Updates the coordinates of the component and its wrapped components.
   *
   * Please note: This method needs to be called _after_ the parent has calculated the anchor coordinates based upon
   * this components dimensions with respect to the other components and the parent alignment configuration.
   */
  updateCoordinates() {
    const container = this.geometry.dimension;
    const text = this.text.getDimensions();

    this.text.setCoordinates(
      new Point(
        this.options.padding / 2,
        (container.height + text.height) / 2
      )
    );
  }
}
