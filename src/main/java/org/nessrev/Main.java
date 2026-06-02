package org.nessrev;

import org.nessrev.entity.NumericArrayEntity;
import org.nessrev.factory.NumericArrayFactory;
import org.nessrev.service.calculation.CalculationService;
import org.nessrev.service.calculation.CalculationServiceImpl;
import org.nessrev.service.sort.SortService;
import org.nessrev.service.sort.SortServiceImpl;
import org.nessrev.service.parser.DoubleArrayParser;
import org.nessrev.service.parser.dispatcher.ParserDispatcher;
import org.nessrev.service.parser.IntegerArrayParser;
import org.nessrev.service.reader.FileReaderService;
import org.nessrev.service.reader.FileReaderServiceImpl;
import org.nessrev.service.validator.NumericArrayValidator;
import org.nessrev.service.validator.NumericArrayValidatorImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) throws IOException {
        final FileReaderService reader = new FileReaderServiceImpl();
        final NumericArrayValidator validator = new NumericArrayValidatorImpl();
        final NumericArrayFactory factory = new NumericArrayFactory();
        final CalculationService calculator = new CalculationServiceImpl();
        final SortService sort = new SortServiceImpl();

        ParserDispatcher dispatcher = new ParserDispatcher(List.of(
                new IntegerArrayParser(),
                new DoubleArrayParser()
        ));

        List<String> dataList = reader.readFile("input.txt");
        List<String> validList = validator.validateNumericArray(dataList);

        List<Number> numbers = validList.stream()
                .map(dispatcher::parse)
                .toList();

        List<Integer> listOfInteger = numbers.stream()
                .filter(n -> n instanceof Integer)
                .map(n -> (Integer) n)
                .toList();

        List<Double> listOfDouble = numbers.stream()
                .filter(n -> n instanceof Double)
                .map(n -> (Double) n)
                .toList();

        NumericArrayEntity<Integer> integerEntity =
                factory.createNumericArray(listOfInteger, Integer.class);

        NumericArrayEntity<Double> doubleEntity =
                factory.createNumericArray(listOfDouble, Double.class);

        Optional<Double> minInt = calculator.min(integerEntity);
        Optional<Double> maxInt = calculator.max(integerEntity);
        Optional<Double> sumInt = calculator.sum(integerEntity);
        Optional<Double> avgInt = calculator.avg(integerEntity);

        Optional<Double> minDouble = calculator.min(doubleEntity);
        Optional<Double> maxDouble = calculator.max(doubleEntity);
        Optional<Double> sumDouble = calculator.sum(doubleEntity);
        Optional<Double> avgDouble = calculator.avg(doubleEntity);

        integerEntity = sort.mergeSort(integerEntity);
        doubleEntity = sort.quickSort(doubleEntity);

        Path path = Path.of("data/outputInt.txt");
        Path path2 = Path.of("data/outputDouble.txt");

        Files.write(
                path,
                Arrays.stream(integerEntity.getNumericArray())
                        .map(String::valueOf)
                        .toList()
        );

        Files.write(
                path2,
                Arrays.stream(doubleEntity.getNumericArray())
                        .map(String::valueOf)
                        .toList()
        );
    }
}