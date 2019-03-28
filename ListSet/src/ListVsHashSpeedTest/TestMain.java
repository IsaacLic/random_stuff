//Isaac Lichter
package ListVsHashSpeedTest;

import java.util.HashSet;

public class TestMain {

    public static String randomString() {
        String randomString = "";

        int randomSize = (int) (Math.random() * 6) + 5;

        for (int i = 0; i <= randomSize; i++) {
            int randomNumber = (int) (Math.random() * 62);
            randomString += (char) (randomNumber < 10 ? randomNumber + 48
                    : (randomNumber < 36 ? randomNumber + 65 : randomNumber + 97));
        }

        return randomString;
    }

    public static void main(String[] args) {

        HashSet hashSet = new HashSet();
        ListSet listSet = new ListSet();

        for (int i = 0; i < 1000000; i++) {
            hashSet.add(randomString());
            listSet.add(randomString());
            if (i % 1000 == 0)
                System.out.println(i / 1000);
        }
        System.out.println("Test prepared. Testing:");
        
        SpeedTest test = new SpeedTest();
        test.start();
        for (int i = 0; i < 1000000; i++){
            hashSet.contains(randomString());
            if (i % 10000 == 0)
                System.out.println(i / 10000);
        }
        System.out.println("HashSet time: " + test.stop());
        
        test.start();
        for (int i = 0; i < 1000000; i++){
            listSet.contains(randomString());
            if (i % 10000 == 0)
                System.out.println(i / 10000);
        }
        System.out.println("ListSet time: " + test.stop());
        
    }

}
