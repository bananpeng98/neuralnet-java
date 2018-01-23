/**
 * NeuralNetwork
 */
public class NeuralNetwork {

  private int inputNodes; 
  private int[] layers;
  private int outputNodes;

  private Matrix[] weights;

  private Matrix[] bias;

  public NeuralNetwork(int[] layers) {
    this.inputNodes = layers[0];
    this.layers = layers;
    this.outputNodes = layers[layers.length - 1];
    weights = new Matrix[layers.length - 1];
    bias = new Matrix[layers.length - 1];

    for (int i = 1; i < layers.length; i++) {
      weights[i - 1] = new Matrix(layers[i], layers[i - 1]).random();
      bias[i - 1] = new Matrix(layers[i], 1).random();
    }
  }

  public Matrix feedforward(float[] input_array) {
    Matrix inputs = new Matrix(input_array);
    for (int i = 0; i < layers.length - 1; i++) {
      inputs = feed(inputs, weights[i], bias[i]);
    }
    return inputs;
  }

  private Matrix feed(Matrix inputs, Matrix layer, Matrix bias) {
    return layer.mul(inputs).add(bias).map((v, i, j) -> Activation.sigmoid(v));
  }
}