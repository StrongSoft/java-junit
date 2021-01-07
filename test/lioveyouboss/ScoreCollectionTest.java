package lioveyouboss;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class ScoreCollectionTest {
    /**
     * 실패 케이스
     * 1. 정수가 아닌경우
     * 2. total 의 값이 int mxa value 넘어선 경우
     */
    @Test
    public void answersArithmeticMeanOfTwoNumbers() {
        // 준비
        ScoreCollection collection = new ScoreCollection();
        collection.add(() -> 5);
        collection.add(() -> 7);

        // 실행
        int actualResult = collection.arithmeticMean();

        // 단언
        assertThat(actualResult, equalTo(6));
    }
}