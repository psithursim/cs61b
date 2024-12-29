package gh2;

public class Drums extends GuitarString{
    public Drums(double frequency) {
        super(frequency);
    }

    @Override
    public void tic() {
        double first = buffer.removeFirst();
        double second = sample();
        double last = (first + second) / 2 * 1;
        double num = Math.random();
        if (num < 0.5) {
            last = 0 - last;
        }
        buffer.addLast(last);
    }
}
