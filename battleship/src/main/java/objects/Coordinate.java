package objects;

public class Coordinate {
  //game column (e.g. A-J)
  private char gameColumn;
  //game row (e.g. 1-10)
  private int gameRow;

  //2D grid row
  private int gridRow;
  //2D grid column
  private int gridCol;

  /**
   * Constructs Coordinate using coordinates from the game grid.
   * 
   * A guess is typically in column-row order (e.g. "B3"), which must be reversed
   * to place it in the 2D array (i.e. from the Grid class)
   * 
   * @param gameCol game grid column, ex. "D"
   * @param gameRow game grid row, ex. "5"
   */
  public Coordinate(char gameCol, int gameRow) {
    this.gameColumn = gameCol;
    this.gameRow = gameRow;
    Translate(gameCol, gameRow);
  }

  /**
   * Helper function to translate the game grid's coordinates into what those
   * coordinates would be on a 2D array
   * 
   * @param gameCol game grid column, ex. "D"
   * @param gameRow game grid row, ex. "5"
   */
  public void Translate(char gameCol, int gameRow) {
    this.gridRow = gameRow - 1;
    this.gridCol = (int) gameCol - 65;
  }

  /**
   * Gets column letter from Game coordinate
   * 
   * @return gameColumn
   */
  public char getGameCol() {
    return this.gameColumn;
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
}