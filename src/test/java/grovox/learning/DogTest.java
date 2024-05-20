package grovox.learning;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DogTest {
    private static Dog dogStatic;
    private Dog dog;
    @BeforeAll // Выполняется один раз перед всеми тестами
    static void prepareDate(){
        dogStatic = new Dog("Bob", 3);
    }

    @BeforeEach // Выполняется перед каждым тестом
    void prepareDateRepeat(){
        dog = new Dog("Bob", 3);
    }
    @Test // Указывает что данный метод, это тестовый метод
    void getName() {
        assertEquals("Bob", dog.getName());
    }

    @Test
    void setName() {
        dog.setName("Roman");
        assertEquals("Roman", dog.getName());
    }

    @Test
    void getAge() {
    }

    @Test
    void setAge() {
    }
}