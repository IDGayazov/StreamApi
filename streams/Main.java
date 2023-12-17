package streams;

/**
 * 1. Найти все транзакции за 2011 год и отсортировать их по сумме (от меньшей к большей).
 * 2. Вывести список неповторяющихся городов, в которых работают трейдеры.
 * 3. Найти всех трейдеров из Кембриджа и отсортировать их по именам.
 * 4. Вернуть строку со всеми именами трейдеров, отсортированными в алфавитном порядке
 * 5. Выяснить, существует ли хоть один трейдер из Милана.
 * 6. Вывести суммы всех транзакций трейдеров из Кембриджа.
 * 7. Какова максимальная сумма среди всех транзакций?
 * 8. Найти транзакцию с минимальной суммой.
*/
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        PuttingIntoPractice.createTransactionsList();
        List<Transaction> transactions = PuttingIntoPractice.transactions();

        // Задание 1

        List<Transaction> transactionsAfter2011SortedBySum = transactions.stream()
                .filter(x -> x.getYear() == 2011)
                .sorted(Comparator.comparingInt(Transaction::getValue))
                .collect(Collectors.toList());

        System.out.println(transactionsAfter2011SortedBySum);

        // Задание 2
        List<String> nonRepeatingCities = transactions.stream()
                .map(Transaction::getTrader)
                .map(Trader::getCity)
                .distinct()
                .collect(Collectors.toList());

        System.out.println(nonRepeatingCities);

        // Задание 3
        List<String> tradersFromCambridgeSortedByName = transactions.stream()
                .filter(x -> x.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getTrader)
                .map(Trader::getName)
                .sorted()
                .distinct()
                .collect(Collectors.toList());
        System.out.println(tradersFromCambridgeSortedByName);

        // Задание 4
        String tradersSortedByName = transactions.stream()
                .map(x -> x.getTrader().getName())
                .sorted()
                .distinct()
                .collect(Collectors.joining(" "));
        System.out.println(tradersSortedByName);

        // Задание 5

        Boolean isTraderFromMilan = transactions.stream()
                .map(Transaction::getTrader)
                .anyMatch(x -> x.getCity().equals("Milan"));

        System.out.println(isTraderFromMilan);

        // Задание 6
        int sumOfTransactionsOfAllCambridgeTraders = transactions.stream()
                .filter(x -> x.getTrader().getCity().equals("Cambridge"))
                .mapToInt(Transaction::getValue)
                .sum();

        System.out.println(sumOfTransactionsOfAllCambridgeTraders);

        // Задание 7
        int maxTransaction = transactions.stream()
                .mapToInt(Transaction::getValue)
                .max()
                .getAsInt();

        System.out.println(maxTransaction);

        // Задание 8
        int minTransaction = transactions.stream()
                .mapToInt(Transaction::getValue)
                .min()
                .getAsInt();

        System.out.println(minTransaction);
    }
}
