/**
 * Main
 */
public class Main {

  public static void main(String[] args) {
    NeuralNetwork nn = new NeuralNetwork(new int[]{3, 4, 2, 1, 2});
    Matrix output = nn.feedforward(new float[] {1, 1, 2});
    System.out.println(output);
  }
}