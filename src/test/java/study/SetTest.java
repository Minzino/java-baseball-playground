package study;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.offset;

public class SetTest {
    Scanner scanner;
    private Set<Integer> numbers;

//Set Collection에 대한 학습 테스트


    @BeforeEach
    void setup() {
        numbers = new HashSet<>();
        numbers.add(1);
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
    }
    //set은 중복을 허락하지않음

    // Test Case 구현

//요구사항 1
//Set의 size()메소드를 활용해 Set의 크기를 확인하는 학습테스트를 구현한다.


    @DisplayName("set 사이즈 확인")
    @Test
    void set_Size() {
        int size_test = numbers.size();
        assertThat(size_test).isEqualTo(3);
    }

//요구사항2
//Set의 contains()메소드를 활용해 1,2,3의 값이 존재하는지를 확인하는 학습테스트를 구현하려한다.
//구현하고 보니 다음과 같이 중복 코드가 계속해서 발생한다.
//Junit의 ParameterizedTest를 활용해 중복 코드를 제거해 본다.

    /**
     * 1.Simple Value
     * ValueSource
     * <p>
     * - 해당 annotation에 지정한 배열을 parameter값으로 순서대로 넘겨준다.
     * - Test method 실행 당 하나의 인자(argument)만을 전달할 때 사용할 수 있다.
     * - 리터럴 값의 배열을 테스트 메서드에 전달한다.
     */

    @Test
    void contains() {
        assertThat(numbers.contains(1)).isTrue();
        assertThat(numbers.contains(2)).isTrue();
        assertThat(numbers.contains(3)).isTrue();
    }//붕족이 있는 코드

    /**
     * 위처럼 중복이 있는 코드를
     * ParameterizedTest를 통해
     * 아래와 같이 간단한게 나타낼 수 있다.
     */

    @DisplayName("중복을 제거한 valueSource")
    @ParameterizedTest //중복을 효과적으로 나타내줌 여기서는 다시 @Test를 써줄 필요는 없다.
    @ValueSource(ints = {1, 2, 3})
    void contains_parameter(int number) {
        assertThat(numbers.contains(number)).isTrue();
    }//여기서는 int number하나의 인자만을 전달함

    /**
     * csvSource
     * - CsvSource의 value 속성으로 다음과 같이 Parameter를 던질 수 있다.
     * - 이렇게 문자열로 구분자 콤마(,)를 기준으로 값을 잘라서 parameter에 담아줄 것이다.
     * - 모든 문자는 String으로 주의하도록 한다.
     * - @CsvSource annotation에 delimiter를 직접 정의함으로써 구분자를 지정할 수 있디.
     */

    @DisplayName("중복있는 Csvsource")
    @Test
    void contains_para_not_csv() {
        assertThat(numbers.contains(1)).isEqualTo(true);
        assertThat(numbers.contains(2)).isEqualTo(true);
        assertThat(numbers.contains(3)).isEqualTo(true);
        assertThat(numbers.contains(4)).isEqualTo(false);
        assertThat(numbers.contains(5)).isEqualTo(false);
    }// 중복이 있는 코드

    /**
     * 위의 중복이 있는 코드를
     * 아래와 같이 중복이 없이 입력 값에 따라 결과 값이 다른 경우에 대한
     * 테스트도 가능하도록 구현했다.
     */

    @DisplayName("중복을 제거한 csvSource")
    @ParameterizedTest
    @CsvSource(value = {"1 : true", "2 : true", "3 : true", "4 : false", "5 : false"}, delimiter = ':')
    void contains_par_csv(int element, boolean expected) {
        assertThat(numbers.contains(element)).isEqualTo(expected);
    }//여기서는 인자가 int element와 boolean expected로 2개이다.
    //delimiter를 이용해 ,를 썼던 구분자를 delimiter = ':'를 통해 커스터마이징도 가능하다.


    @Test
    @DisplayName("메소드 연쇄호출")
    void method_chain() {
        String hello = "Hello World";
        assertThat(hello)
                .isNotEmpty()//비어있지 않고
                .contains("Hello")//"Hello"를 포함하고
                .doesNotContain("zino")//"zino"는 포함하지 않으며
                .startsWith("Hello")//"Hello"로 시작하고
                .endsWith("World")//"World"로 끝나고
                .isEqualTo("Hello World");//"Hello World"와 같다
    }

    /**
     *  assertThat(테스트 타깃). 메서드 1(). 메서드 2(). 메서드 3()'
     *  이런 포맷으로 AssertJ의 여러 메서드들을 연쇄적으로 호출해 코드를 작성할 수 있다.
     *  (메서드 체이닝)
     */


    @Test
    @DisplayName("숫자 메소드 연쇄호출")
    void math_mathod_chain() {
        double pi = 3.14;
        assertThat(pi)
                .isPositive()//양수이고
                .isGreaterThan(3)//3보다 크며
                .isLessThan(4)//4보다 작고
                .isEqualTo(3, offset(1d))//오프셋 1 기준으로 3과 같고(상수 첮번째와)
                .isEqualTo(3.1, offset(0.1d))//오프셋 0.1기준으로 3.1과 같으며(소수점1째자리와)
                .isEqualTo(3.14);//오프셋 없이는 3.14와 같다
    }

    @Test
    void String_Calculator(){
        String value = scanner.nextLine();
        String[] values = value.split(" ");
        int number = Integer.parseInt(value);
    }


}
