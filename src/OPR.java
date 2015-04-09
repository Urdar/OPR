/**
 * Created by jens on 11.03.15.
 */

import java.io.*;
import java.util.ArrayList;

class OPR {
    private ArrayList<String> reference = new ArrayList<String>();
    private ArrayList<String> pageFrame = new ArrayList<String>();

    BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        OPR runOPR = new OPR();
        runOPR.pageReplace();
    }

    public OPR() throws IOException {
        // TODO validere input
        System.out.println("Hei, og velkommen til OPR simulatoren :)\nVennligst skriv inn antall rammer du vil bruke");
        int frameAmount = Integer.parseInt(input.readLine());

        for (int x = 0; x < frameAmount; x++) {
            pageFrame.add(null);
        }

        System.out.println("\nTakk, skriv så inn referanse på formen '1,5,1,6,1,8'");
        String tempReference = input.readLine();

        // Legge reference inn i Array
        for (String f : tempReference.trim().split(",")) {
            reference.add(f);
        }

        System.out.println("\nBruker " + pageFrame.size() + " rammer og referansen " + tempReference + " i beregningene..\n");
    }

    public void pageReplace() {
        leggtil:
        for (int i = 0; i < reference.size(); i++) {

            //Sjekke om den ligger der fra før, hvis så continue;
            for (int y = 0; y < pageFrame.size(); y++) {
                if (reference.get(i).equals(pageFrame.get(y))) {
                    System.out.println(printFrames(i, true));
                    continue leggtil;
                }
            }

            // Sjekke om vi har plass, hvis så legge til
            for (int y = 0; y < pageFrame.size(); y++) {
                if (pageFrame.get(y) == null) {
                    pageFrame.set(y, reference.get(i));
                    System.out.println(printFrames(i, false));
                    continue leggtil;
                }
            }

            // Der var ikke plass, finne korrekt index i pageFrame å erstatte
            int chosenIndex = 0;
            for (int y = 0; y < pageFrame.size(); y++) {
                for (int z = i; z < reference.size(); z++) {
                    if (pageFrame.get(y).equals(reference.get(i)) && i > chosenIndex) {
                        // vi har funnet nærmeste avstand, ta ta index
                        chosenIndex = y;
                    }
                }
            }

            // Skal bytte pageFrame index chosenIndex med reference verdi index i
            pageFrame.set(chosenIndex, reference.get(i));

            System.out.println(printFrames(i, false));


        }
    }

    public String printFrames(int indexNumber, boolean noChange) {

        StringBuilder sb = new StringBuilder();
        sb.append((indexNumber + 1) + ": ");

        for (int y = 0; y < pageFrame.size(); y++) {
            if (pageFrame.get(y) == null) {
                sb.append("_");
            } else {
                sb.append(pageFrame.get(y));
            }
            sb.append(" ");
        }

        if (noChange) {
            sb.append(" Ingen endring");
        }

        String returnString = sb.toString();
        return returnString;
    }
}