package ru.vsu.logic;

import ru.vsu.entity.Student;
import ru.vsu.entity.Subject;
import ru.vsu.entity.Teacher;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TeacherService {

    /**
     * Возвращает имя и фамилию преподавателей, преподающих только один предмет
     */
    public List<String> getSingleSubjectLecturerFio(Collection<Teacher> teachers) {
        return teachers.stream()
                .filter(teacher -> teacher.getSubjects().stream().count() == 1)
                .map(teacher -> teacher.getFullName())
                .collect(Collectors.toList());
    }

    /**
     * Возвращает мапу, где ключ - имя преподавателя, а значение - студенты, для которых он является наставником
     */
    public Map<String, List<Student>> getTeacherNameToSupervisedStudentsMap(Collection<Student> students) {
        return students.stream()
                .filter(student -> student.getSupervisor() != null)
                .collect(Collectors.groupingBy(
                        student -> student.getSupervisor().getFullName(),
                        Collectors.toList()
                ));
    }

    /**
     * Возвращает сумму зарплат всех преподавателей
     */
    public BigDecimal getTeachersSalarySum(Collection<Teacher> teachers) {
        return teachers.stream()
                .map(Teacher::getSalary)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        /**
         Начальное значение: BigDecimal.ZERO
         Операция: BigDecimal::add (сложение)
         */
    }

    /**
     * Возвращает имя и фамилию преподавателя, который может вести заданный предмет
     */
    public String findTeacherBySubject(Collection<Teacher> teachers, Subject subject) {
        return teachers.stream()
                .filter(teacher -> teacher.getSubjects().contains(subject))
                .map(teacher -> teacher.getFullName())
                .findAny()
                .orElse(null);
    }
}
