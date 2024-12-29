package gh2;
import edu.princeton.cs.algs4.StdAudio;
import edu.princeton.cs.algs4.StdDraw;

/**
 * A client that uses the synthesizer package to replicate a plucked guitar string sound
 */
public class GuitarHero {
    public static final double START = 440.0;

    public static void main(String[] args) {
        /* create two guitar strings, for concert A and C */
        String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
        double concert = START;
        GuitarString[] strings = new GuitarString[37];
        for (int i = 0; i < 37; i++) {
            strings[i] = new GuitarString(concert);
            concert = START * Math.pow(2, (i - 24) / 12.0);
        }

        int cnt = 0;
        int pre = 0, now = 0;

        while (true) {


            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                for (int i = 0; i < 37; i++) {
                    if (key == keyboard.charAt(i)) {
                        strings[i].pluck();
                        if (cnt == 0) {
                            now = i;
                            cnt++;
                        } else {
                            pre = i;
                            cnt--;
                        }
                    }
                }
                /*
                if (key == 'a') {
                    stringA.pluck();
                } else if (key == 'c') {
                    stringC.pluck();
                }
                 */
            }

            /* compute the superposition of samples */
            double sample = strings[pre].sample() + strings[now].sample();

            /* play the sample on standard audio */
            StdAudio.play(sample);

            /* advance the simulation of each guitar string by one step */
            strings[pre].tic();
            strings[now].tic();
        }
    }
}

