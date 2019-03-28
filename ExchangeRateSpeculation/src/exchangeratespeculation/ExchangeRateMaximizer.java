//Isaac Lichter
package exchangeratespeculation;

import java.util.Scanner;

public class ExchangeRateMaximizer {

    public ExchangeRateMaximizer() {

        System.out.println("Please enter how many days the crystal ball can see, followed by the exchange rates for each day.");
        System.out.println("You can write '0' for the number of days if you wish to terminate the program.");

        Scanner kb = new Scanner(System.in);
        int numberOfDays = kb.nextInt();

        while (numberOfDays > 0) {
            calulateMaxValue(numberOfDays);
            numberOfDays = kb.nextInt();
        }
    }

    private void calulateMaxValue(int numberOfDays) {

        Scanner kb = new Scanner(System.in);

        double[] rates = new double[numberOfDays];

        for (int c = 0; c < numberOfDays; c++) {
            rates[c] = kb.nextDouble();
        }

        double maximumValue = maxProfitAlgorithm(rates, numberOfDays);

        System.out.println(String.format("You can have a maximum amount of %.2f Canadian Dollars", maximumValue));
    }

    private double maxProfitAlgorithm(double[] rates, int numberOfDays) {

        double maximumValue = 1000;
        double localMinRate = rates[0];
        double localMaxRate = rates[0];
        boolean isProfitable = false;

        for (int c = 1; c < numberOfDays; c++) {

            if (maximumValue / localMaxRate * .97 > maximumValue * rates[c] * (1/.97) && isProfitable) {
                maximumValue = maximumValue / localMinRate * .97 * localMaxRate * .97;
                localMinRate = rates[c];
                localMaxRate = rates[c];
                isProfitable = false;
            } else {
                if (maximumValue / rates[c] * .97 > maximumValue * localMinRate * (1/.97)) {
                    isProfitable = true;
                }
                if (localMaxRate < rates[c]) {
                    localMaxRate = rates[c];
                }
                if (c == numberOfDays - 1 && isProfitable) {
                    maximumValue = maximumValue / localMinRate * .97 * localMaxRate * .97;
                };
                if (localMinRate > rates[c]) {
                    localMinRate = rates[c];
                    localMaxRate = rates[c];
                }

            }
        }

        return maximumValue;
    }

}
