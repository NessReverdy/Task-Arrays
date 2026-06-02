package org.nessrev.task;

import org.nessrev.task.entity.NumericArrayEntity;
import org.nessrev.task.factory.NumericArrayFactory;
import org.nessrev.task.service.calculation.CalculationService;
import org.nessrev.task.service.calculation.impl.CalculationServiceImpl;
import org.nessrev.task.service.sort.SortService;
import org.nessrev.task.service.sort.impl.SortServiceImpl;
import org.nessrev.task.parser.impl.DoubleArrayParser;
import org.nessrev.task.parser.dispatcher.ParserDispatcher;
import org.nessrev.task.parser.impl.IntegerArrayParser;
import org.nessrev.task.reader.FileReaderService;
import org.nessrev.task.reader.impl.FileReaderServiceImpl;
import org.nessrev.task.validator.NumericArrayValidator;
import org.nessrev.task.validator.impl.NumericArrayValidatorImpl;

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