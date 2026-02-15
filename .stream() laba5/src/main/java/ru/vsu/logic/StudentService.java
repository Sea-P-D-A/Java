package ru.vsu.logic;

import ru.vsu.entity.ExamResult;
import ru.vsu.entity.Student;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 .filter(Predicate)             Фильтрует элементы потока
 .map(Function)                 Преобразует каждый элемент потока список студентов в список фамилий студентов объекты
 .sorted()                      Сортирует элементы потока в естественном порядке
 .collect(Collectors.toList())  Собирает элементы потока обратно в коллекцию.
 .min(Comparator.comparing(Student::getAge)) Находит минимальный элемент в потоке согласно компаратору
 .orElse(null)                  Извлекает значение или возвращает null
 .mapToDouble(ToDoubleFunction) Преобразует поток объектов в поток double примитивы
 .average()                     Вычисляет среднее арифметическое всех элементов потока
 .collect(Collectors.groupingBy())
 .findAny()                     Возвращает любой найденый при паралельном запуске(первый найденный)
 .findFirst()                   Возвращает первый найденый при паралельном запуске(первый в списке)

 */
/**
 1.Фильтрация: .filter(), .distinct()
 2.Преобразование: .map(), .flatMap()
 3.Сортировка: .sorted()
 4.Ограничение: .limit(), .skip()
 5.Сбор: .collect(), .toList(), .toSet(), .toMap()
 6.Проверки: .anyMatch(), .allMatch(), .noneMatch()
 7.Поиск: .findFirst(), .findAny()
 8.Агрегация: .count(), .sum(), .average(), .min(), .max()
 */

public class StudentService {

    /**
     * Возвращает упорядоченные фамилии совершеннолетних студентов
     */
    public List<String> getAdultStudentsLastNameSorted(Collection<Student> students) {
        return students.stream()
                .filter(student -> student.getAge() >= 18) //Фильтер
                .map(Student::getLastName)
                .sorted()
                .collect(Collectors.toList());
    }

    /**
     * Возвращает стунентов-отличников
     */
    public Set<Student> getExcellentStudents(Collection<Student> students) {

        System.out.println(students.stream()
                        .map(Student::getExamResults)
                .collect(Collectors.toList()));

        return students.stream()
                .filter(student -> student.getExamResults().stream()
                        .allMatch(examResult -> examResult.getMark() == 5))
                .collect(Collectors.toSet());
    }

    /**
     * Возвращает среднюю оценку
     */
    public Double getAverageMark(Collection<Student> students) {
        return students.stream()
                .flatMap(student -> student.getExamResults().stream())
                .mapToDouble(ExamResult::getMark)
                .average()
                .orElse(0.0);
    }

    /**
     * Возвращает самого молодого стунендта
     */
    public Student findYoungestStudent(Collection<Student> students) {
        return students.stream()
                .min(Comparator.comparing(Student::getAge))
                .orElse(null);
    }

}
