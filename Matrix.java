import java.security.InvalidParameterException;

/**
 * Matrix
 */
public class Matrix {

  private float[][] elements;
  private int rows;
  private int cols;

  public Matrix() { }

  public Matrix(int rows, int cols) {
    create(rows, cols);
  }

  public Matrix(float[][] elements) {
    fromArray(elements);
  }

  public Matrix(float[] vector) {
    createVector(vector);
  }

  public Matrix clone() {
    return new Matrix(this.elements);
  }

  public Matrix fromArray(float[][] elements) {
    create(elements.length, elements[0].length);
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        this.elements[i][j] = elements[i][j];
      }
    }
    return this;
  }

  public Matrix createVector(float[] vector) {
    create(vector.length, 1);
    for (int i = 0; i < rows; i++) {
      this.elements[i][0] = vector[i];
    }
    return this;
  }

  public Matrix create(int rows, int cols) {
    this.rows = rows;
    this.cols = cols;
    elements = new float[rows][];
    for (int i = 0; i < rows; i++) {
      elements[i] = new float[cols];
    }
    return this;
  }

  public Matrix random() {
    return map((v, i, j) -> (float)Math.random() * 2f - 1f);
  }

  public Matrix map(Mapping mapMethod) {
    Matrix matrix = new Matrix(elements);
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        matrix.elements[i][j] = mapMethod.map(matrix.elements[i][j], i, j);
      }
    }
    return matrix;
  }

  public Matrix scale(float scalar) {
    return map((v, i, j) -> elements[i][j] * scalar);
  }

  public Matrix add(Matrix other) {
    return map((v, i, j) -> elements[i][j] + other.elements[i][j]);
  }

  public Matrix sub(Matrix other) {
    return map((v, i, j) -> elements[i][j] - other.elements[i][j]);
  }

  public Matrix elemMul(Matrix other) {
    return map((v, i, j) -> elements[i][j] * other.elements[i][j]);
  }

  public Matrix mul(Matrix other) {
    if (cols != other.rows) {
      throw new InvalidParameterException("Sizes must match!");
    }
    float sum = 0;
    Matrix result = new Matrix(rows, other.cols);
    for (int i = 0; i < result.rows; i++) {
      for (int j = 0; j < result.cols; j++) {
        sum = 0;
        for (int k = 0; k < cols; k++) {
          sum += elements[i][k] * other.elements[k][j];
        }
        result.elements[i][j] = sum;
      }
    }
    return result;
  }

  public float[] flatten() {
    float[] elements = new float[rows * cols];
    map((v, i, j) -> elements[j + i * cols] = v);
    return elements;
  }

  public String toString() {
    String s = "";
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        s += String.format("%6.2f", elements[i][j]);
      }
      s += "\n";
    }
    return s;
  }

  /**
   * Mapping interface
   */
  public interface Mapping {
    float map(float value, int i, int j);
  }
}