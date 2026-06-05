package org.nessrev.task;

import org.nessrev.task.entity.NumericArrayEntity;
import org.nessrev.task.exception.CustomException;
import org.nessrev.task.factory.NumericArrayFactory;
import org.nessrev.task.parser.dispatcher.ParserDispatcher;
import org.nessrev.task.parser.impl.DoubleArrayParser;
import org.nessrev.task.parser.impl.IntegerArrayParser;
import org.nessrev.task.reader.FileReaderService;
import org.nessrev.task.reader.impl.FileReaderServiceImpl;
import org.nessrev.task.repo.NumericArrayRepository;
import org.nessrev.task.repo.impl.NumericArrayRepositoryImpl;
import org.nessrev.task.service.calculation.CalculationService;
import org.nessrev.task.service.calculation.impl.CalculationServiceImpl;
import org.nessrev.task.service.sort.SortService;
import org.nessrev.task.service.sort.impl.SortServiceImpl;
import org.nessrev.task.util.Condition;
import org.nessrev.task.validator.NumericArrayValidator;
import org.nessrev.task.validator.impl.NumericArrayValidatorImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Main {
  public static void main(String[] args) throws IOException, CustomException {
    final FileReaderService reader = new FileReaderServiceImpl();
    final NumericArrayValidator validator = new NumericArrayValidatorImpl();
    final NumericArrayFactory factory = new NumericArrayFactory();
    final CalculationService calculator = new CalculationServiceImpl();
    final SortService sort = new SortServiceImpl();
    final NumericArrayRepository<Integer> repoInteger = new NumericArrayRepositoryImpl<>();
    final NumericArrayRepository<Double> repoDouble = new NumericArrayRepositoryImpl<>();
    ParserDispatcher dispatcher = new ParserDispatcher(List.of(
      new IntegerArrayParser(),
      new DoubleArrayParser()
    ));

    List<String> dataList = reader.readFile("input.txt");
    List<String> validList = validator.validateNumericArray(dataList);

    List<Number> numbers = validList.stream()
      .map(dispatcher::parse)
      .flatMap(Optional::stream)
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

    repoInteger.addNumericArrayEntity(integerEntity);
    repoDouble.addNumericArrayEntity(doubleEntity);
    repoInteger.addNumericArrayEntity(
      new NumericArrayEntity<>(new Integer[]{1, 6, 8, -6, 5, -3, 4, 8, 10}));
    repoInteger.addNumericArrayEntity(
      new NumericArrayEntity<>(new Integer[]{0, 5, 7, -4, -3, 2, 7, -8}));
    repoInteger.addNumericArrayEntity(
      new NumericArrayEntity<>(new Integer[]{1, 8, 4, 0, 3, 5, 8, 6}));

    NumericArrayEntity<Integer> integerEntityFromRepo = repoInteger.findById(integerEntity.getId());
    NumericArrayEntity<Double> doubleEntityFromRepo = repoDouble.findById(doubleEntity.getId());

    List<NumericArrayEntity<Integer>> listOfSomeEntities =
      calculator.searchArrayByPredicate(
        Condition.greaterThan(5),
        calculator::max,
        "max > 5",
        repoInteger);

    repoInteger.updateNumericArrayEntity(integerEntityFromRepo);
    repoDouble.updateNumericArrayEntity(doubleEntityFromRepo);

    Path path = Path.of("data/outputInt.txt");
    Path path2 = Path.of("data/outputDouble.txt");

    Files.write(
      path,
      Arrays.stream(integerEntityFromRepo.getNumericArray())
        .map(String::valueOf)
        .toList()
    );

    Files.write(
      path2,
      Arrays.stream(doubleEntityFromRepo.getNumericArray())
        .map(String::valueOf)
        .toList()
    );
  }
}