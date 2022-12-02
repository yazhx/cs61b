package gh2;

import deque.ArrayDeque;
import edu.princeton.cs.algs4.StdAudio;
import edu.princeton.cs.algs4.StdDraw;

public class GuitarHero {
    public static void main(String[] args) {
        String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
        ArrayDeque<GuitarString> ad = new ArrayDeque<>();
        for (char ch: keyboard.toCharArray()) {
            double frequency = 440 * Math.pow(2, (keyboard.indexOf(ch) - 24) / 12.0);
            ad.addLast(new GuitarString(frequency));
        }

        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                if (keyboard.indexOf(key) != -1) {
                    ad.get(keyboard.indexOf(key)).pluck();
                }
            }

            double sample = 0.0;
            for (char ch: keyboard.toCharArray()) {
                sample += ad.get(keyboard.indexOf(ch)).sample();
            }

            StdAudio.play(sample);

            for (int i = 0; i < ad.size(); i++) {
                ad.get(i).tic();
            }
        }

    }
}
