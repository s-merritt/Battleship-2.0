package objects;

public class Coordinate {
  public enum NeighboringDirection {
    VERTICAL, HORIZONTAL, NONE
  }

  // game column (e.g. A-J)
  private char gameCol;
  // game row (e.g. 1-10)
  private int gameRow;

  // 2D grid row
  private int gridRow;
  // 2D grid column
  private int gridCol;

  /**
   * Constructs Coordinate using coordinates from the game grid.
   * 
   * A guess is typically in column-row order (e.g. "B3"), which must be reversed
   * to place it in the 2D array (i.e. from the Grid class)
   * 
   * @param gameCol game grid column, ex. "D"
   * @param gameRow game grid row, ex. "5"
   * 
   * TODO(merritt) move character conversion to uppercase here
   */
  public Coordinate(char gameCol, int gameRow) {
    // convert game col letter to uppercase
    gameCol = Character.toUpperCase(gameCol);

    this.gameCol = gameCol;
    this.gameRow = gameRow;
    TranslateGameToGrid(gameCol, gameRow);
  }

  /**
   * Constructs Coordinate using coordinates from the 2D array
   * 
   * Translates into the equivalent game-board coordinate
   * 
   * @param gridRow 2D grid row
   * @param gridCol 2D grid column
   */
  public Coordinate(int gridRow, int gridCol) {
    this.gridRow = gridRow;
    this.gridCol = gridCol;
    TranslateGridToGame(gridRow, gridCol);
  }

  /**
   * Helper function to translate the game grid's coordinates into what those
   * coordinates would be on a 2D array
   * 
   * @param gameCol game grid column, ex. "D"
   * @param gameRow game grid row, ex. "5"
   */
  public void TranslateGameToGrid(char gameCol, int gameRow) {
    this.gridRow = gameRow - 1;
    this.gridCol = (int) gameCol - 65;
  }

  /**
   * Helper function to translate the 2D array coordinates into the equivalent
   * game board coordinate
   * 
   * @param gridRow 2D grid row
   * @param gridCol 2D grid column
   */
  public void TranslateGridToGame(int gridRow, int gridCol) {
    this.gameRow = gridRow + 1;
    this.gameCol = (char) (gridCol + 65);
  }

  /**
   * Helper function to see if the given coordinate is a neighbors of this
   * coordinate.
   * 
   * NOTE: assumes that both coordinates are valid
   * 
   * @param c potential neighborsing coordinate
   * @return NeighboringDirection
   */
  public NeighboringDirection neighbors(Coordinate c) {
    int diffRow = this.gridRow - c.getGridRow();
    int diffCol = this.gridCol - c.getGridCol();

    if (diffRow == 0 && Math.abs(diffCol) <= 1) {
      return NeighboringDirection.HORIZONTAL;
    } else if (diffCol == 0 && Math.abs(diffRow) <= 1) {
      return NeighboringDirection.VERTICAL;
    }

    return NeighboringDirection.NONE;

  }

  /**
   * Gets column letter from Game coordinate
   * 
   * @return gameColumn
   */
  public char getGameCol() {
    return this.gameCol;
  }

  /**
   * Gets row number from Game coordinate
   * 
   * @return gameRow
   */
  public int getGameRow() {
    return this.gameRow;
  }

  /**
   * Gets 2D array row equivalent to Game coordinate
   * 
   * @return gridRow
   */
  public int getGridRow() {
    return this.gridRow;
  }

  /**
   * Gets 2D array column equivalent to Game coordinate
   * 
   * @return
   */
  public int getGridCol() {
    return this.gridCol;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null)
      return false;

    if (obj instanceof Coordinate) {
      Coordinate c = (Coordinate) obj;
      return this.gameCol == c.getGameCol() 
          && this.gameRow == c.getGameRow() 
          && this.gridRow == c.getGridRow()
          && this.gridCol == c.getGridCol();
    } else
      return false;
  }

  @Override
  public String toString() {
    return (("" + this.gameCol) + (this.gameRow + ""));
  }
}