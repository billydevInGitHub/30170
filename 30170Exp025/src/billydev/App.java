package billydev;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App {
    public static void main(String[] args) {
        ArrayList<String> arrayList = new ArrayList<>(Arrays.asList("abc","def","ghijk"));
        arrayList.stream().forEach(System.out::println);

        System.out.println("=====2");
        arrayList.stream().forEach((e)-> System.out.println(e+"test"));

//        ArrayList<Integer> integerArrayList= new ArrayList<>(List.of(1,3,5));
        System.out.println("=====3");
        Stream<String> stream = Stream.of("123", "sfad", "ewrte", "caw", "45df", "addasf","caw","caw","caW");

        stream.filter((a)->a.length()>2)
                .flatMap(new App()::mappingTest)    //flatMap return must be stream
//                .flatMap((a)->Stream.of(a+"abc"))
                .distinct()           //keep only one "caw"
                .forEach(System.out::println);



        System.out.println("=====4");
//        List<String> vowels = List.of("a", "e", "i", "o", "u");
        List<String> vowels = Arrays.asList("a", "e", "i", "o", "u");

        //from: https://www.journaldev.com/32457/java-stream-collect-method-examples
        // sequential stream - nothing to combine
        StringBuilder result = vowels.stream().collect(StringBuilder::new, (x, y) -> x.append(y),
                (a, b) -> a.append(",").append(b));
        System.out.println(result.toString());

        // parallel stream - combiner is combining partial results <---this is the key !!
        StringBuilder result1 = vowels.parallelStream().collect(StringBuilder::new, (x, y) -> x.append(y),
                (a, b) -> a.append(",").append(b));
        System.out.println(result1.toString());


        System.out.println("=====4");
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);

        Set<Integer> oddNumbers = numbers.parallelStream().filter(x -> x % 2 != 0).collect(Collectors.toSet());
        System.out.println(oddNumbers); // [1, 3, 5]

        System.out.println(numbers.parallelStream().filter(x -> x % 3 != 0).collect(Collectors.toList()));

        Map<Integer, String> mapOddNumbers = numbers.parallelStream().filter(x -> x % 2 != 0)
                .collect(Collectors.toMap(Function.identity(), x -> String.valueOf(x)));
        System.out.println(mapOddNumbers); // {1=1, 3=3, 5=5}


        System.out.println(Stream.of("a", "b", "c").collect(Collectors.joining()));
        System.out.println( Stream.of("a", "b", "c").collect(Collectors.joining(",")));
        System.out.println( Stream.of("a", "b", "c").collect(Collectors.joining(",", "{", "}")));
        System.out.println( Stream.of("1", new StringBuffer("2"), new StringBuilder("3")).collect(Collectors.joining()));
    }
    private Stream mappingTest(String input){
        return Stream.of(input + "abc");
    }
}
