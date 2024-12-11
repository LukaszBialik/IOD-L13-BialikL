package put.io.testing.audiobooks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AudiobookPriceCalculatorTest {

    @Test
    public void Subscriber() {
        Customer subscriber = new Customer("Andrzej", Customer.LoyaltyLevel.STANDARD, true);
        Audiobook audiobook = new Audiobook("audiobook", 20);
        AudiobookPriceCalculator calculator = new AudiobookPriceCalculator();
        double price = calculator.calculate(subscriber, audiobook);

        assertEquals(0.0, price);
    }

    @Test
    public void Silver() {
        Customer silver = new Customer("Pawel", Customer.LoyaltyLevel.SILVER, false);
        Audiobook audiobook = new Audiobook("audiobook", 20);
        AudiobookPriceCalculator calculator = new AudiobookPriceCalculator();
        double price = calculator.calculate(silver, audiobook);

        assertEquals(0.9*20, price);
    }

    @Test
    public void Gold() {
        Customer gold = new Customer("Janek", Customer.LoyaltyLevel.GOLD, false);
        Audiobook audiobook = new Audiobook("audiobook", 20);
        AudiobookPriceCalculator calculator = new AudiobookPriceCalculator();
        double price = calculator.calculate(gold, audiobook);

        assertEquals(0.8*20, price);
    }

    @Test
    public void Standard() {
        Customer standard = new Customer("Jaca", Customer.LoyaltyLevel.STANDARD, false);
        Audiobook audiobook = new Audiobook("audiobook", 20);
        AudiobookPriceCalculator calculator = new AudiobookPriceCalculator();
        double price = calculator.calculate(standard, audiobook);

        assertEquals(20, price);
    }
}
