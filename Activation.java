/**
 * Activation
 */
public class Activation {

  public static float sigmoid(float x) {
    return 1 / (1 + (float)Math.exp(-x));
  }

  public static float tanh(float x) {
    return (float)Math.tanh(x);
  }

  public static float relu(float x) {
    return x < 0 ? 0 : x;
  }
}